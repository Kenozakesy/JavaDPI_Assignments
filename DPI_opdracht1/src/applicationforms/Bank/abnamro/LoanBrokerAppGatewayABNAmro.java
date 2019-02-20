package applicationforms.Bank.abnamro;

import com.google.gson.Gson;
import mix.ChainCode.Recievers.MessageRecieverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.BankSerializer;
import mix.messaging.requestreply.RequestReply;
import mix.model.bank.BankInterestReply;
import mix.model.bank.BankInterestRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class LoanBrokerAppGatewayABNAmro {

    private MessageRecieverGateway receiver;
    private MessageSenderGateway sender;
    private BankSerializer serializer;

    private JMSBankFrame frame;

    public LoanBrokerAppGatewayABNAmro(JMSBankFrame frame)
    {
        this.frame = frame;
        sender = new MessageSenderGateway("BankInterestReply");
        receiver = new MessageRecieverGateway("BankInterestRequestABNAmro");
        serializer = new BankSerializer();

        MessageListener listener = msg -> {
            try {
                BankInterestRequest request = new Gson().fromJson(((TextMessage) msg).getText(), BankInterestRequest.class);
                onBankInterestRequestArrived(request);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        };
        receiver.setListener(listener);
    }

    public void replyToLoan(RequestReply<BankInterestRequest, BankInterestReply> requestReply)
    {
        String object = serializer.replyRequestToString(requestReply);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    public void onBankInterestRequestArrived(BankInterestRequest request)
    {
        frame.listModel.addElement(new RequestReply<>(request, null));
        frame.list.repaint();
    }
}
