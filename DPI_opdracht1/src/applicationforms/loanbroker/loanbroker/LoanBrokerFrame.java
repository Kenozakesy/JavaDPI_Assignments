package applicationforms.loanbroker.loanbroker;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import applicationforms.loanbroker.Gateways.BankAppGateway;

import applicationforms.loanbroker.Gateways.LoanClientAppGateway;
import applicationforms.loanbroker.loanbroker.Aggregator.Aggregator;
import applicationforms.loanbroker.loanbroker.Aggregator.BankCheckSending;
import mix.Messages;
import mix.model.bank.BankInterestReply;
import mix.model.bank.BankInterestRequest;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;


public class LoanBrokerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static JScrollPane scrollPane;

    private List<Aggregator> AggregatorList = new ArrayList<>();
	private BankAppGateway bankGateway = new BankAppGateway(this);
	private LoanClientAppGateway loanClientGateway = new LoanClientAppGateway(this);

	public static Broker broker;
	private static Messages messangerToClient;
	
	public static void main(String[] args) {
		broker = new Broker();
		LoanBrokerFrame frame = new LoanBrokerFrame();
		frame.setVisible(true);
	}

	//----->
	public synchronized void passRequestToBank(BankInterestRequest request)
	{
		//check if sending is applicable
		BankCheckSending check = new BankCheckSending();
		check.CheckSendAllowed(request);

		//logisch hier nieuwe aggredation ID aanmaken
		Aggregator aggregator = new Aggregator(request, check.getNumberToSend());
		AggregatorList.add(aggregator);

		request.setAggregationID(aggregator.getAggregationID());

		bankGateway.sendBankRequest(request, check);

}

	//<-----
	public synchronized void passReplyToClient(BankInterestRequest request, BankInterestReply reply)
	{
		BankInterestReply finalReply = null;
		Aggregator aggr = null;
		for (Aggregator A : AggregatorList)
		{
			if(A.getAggregationID().getID() == request.getAggregationID().getID())
			{
				finalReply = A.addReply(reply);
				if(finalReply != null)
				{
					aggr = A;
				}
				break;
			}
		}

		if(finalReply != null) {
			AggregatorList.remove(aggr);
			broker.add(request, finalReply);
			LoanReply loanReply = new LoanReply(finalReply.getInterest(), finalReply.getQuoteId());
			JListLine jlistLine = broker.getRequestReply(request);
			LoanRequest loanRequest = jlistLine.getLoanRequest();
			loanClientGateway.sendLoanReply(loanRequest, loanReply);
		}
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
