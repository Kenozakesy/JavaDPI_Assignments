package applicationforms.loanbroker.loanbroker;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import mix.Messages;
import mix.ReceiveMessages;
import mix.messaging.requestreply.RequestReply;
import mix.model.bank.BankInterestReply;
import mix.model.bank.BankInterestRequest;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;


public class LoanBrokerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static JScrollPane scrollPane;
	private static Broker broker;
	private static Messages messangerToBank;
	private static Messages messangerToClient;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// init new que
                    messangerToBank = new Messages("tcp://localhost:61616", false, "BankInterestRequest");
					messangerToClient = new Messages("tcp://localhost:61616", false, "LoanReply");
                    broker = new Broker();

                    //init GUI
					LoanBrokerFrame frame = new LoanBrokerFrame();
					frame.setVisible(true);

					//init listener
					ReceiveMessages receiveMessagesFromCustomer = new ReceiveMessages("tcp://localhost:61616", false, "LoanRequest");
                    ReceiveMessages receiveMessagesFromBanks = new ReceiveMessages("tcp://localhost:61616", false, "BankInterestReply");

                    //region consumer_setup
					try {
						if(receiveMessagesFromCustomer.consumer != null)
						{
							receiveMessagesFromCustomer.consumer.setMessageListener(new MessageListener() {

								@Override
								public void onMessage(Message msg) {
									System.out.println("received message: " + msg);
									try {
										BankInterestRequest bankInterestRequest = broker.add(new Gson().fromJson(((TextMessage) msg).getText(), LoanRequest.class));
										//send request to the bank
										messangerToBank.SendMessage(new Gson().toJson(bankInterestRequest));
									} catch (JMSException e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (JMSException e) {
						e.printStackTrace();
					}
					//endregion

					//region consumer_setup
					try {
						if(receiveMessagesFromBanks.consumer != null)
						{
							receiveMessagesFromBanks.consumer.setMessageListener(new MessageListener() {

								@Override
								public void onMessage(Message msg) {
									//System.out.println("received message: " + msg);
									try {
										java.lang.reflect.Type type = new TypeToken<RequestReply<BankInterestRequest, BankInterestReply>>(){}.getType();
										RequestReply<BankInterestRequest, BankInterestReply> requestReply = new Gson().fromJson(((TextMessage) msg).getText(), type);

										BankInterestRequest bankInterestRequest = requestReply.getRequest();
										BankInterestReply bankInterestReply = requestReply.getReply();

										//this still has to work
										broker.add(bankInterestRequest, bankInterestReply);


										//send message to client
										LoanReply loanReply = new LoanReply(bankInterestReply.getInterest(), bankInterestReply.getQuoteId());
										//getLoanrequest
										JListLine jlistLine = broker.getRequestReply(bankInterestRequest);
										LoanRequest loanRequest = jlistLine.getLoanRequest();
										System.out.println("loan request " + loanRequest.getAmount());

										RequestReply<BankInterestRequest, BankInterestReply> loanRequestReply = new RequestReply(loanRequest, loanReply);
										messangerToClient.SendMessage(new Gson().toJson(loanRequestReply));
										System.out.println("message send");

									} catch (JMSException e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (JMSException e) {
						e.printStackTrace();
					}
					//endregion
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoanBrokerFrame() {
		setTitle("Loan Broker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{46, 31, 86, 30, 89, 0};
		gbl_contentPane.rowHeights = new int[]{233, 23, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		scrollPane.setViewportView(broker.list);
	}
	

}
