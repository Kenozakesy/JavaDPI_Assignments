package applicationforms.abnamro.bank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mix.ChainCode.Recievers.MessageRecieverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.BankSerializer;
import mix.ChainCode.Serializers.LoanSerializer;
import mix.messaging.requestreply.RequestReply;
import mix.model.bank.BankInterestReply;
import mix.model.bank.BankInterestRequest;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class LoanBrokerAppGateway {

    private MessageRecieverGateway receiver;
    private MessageSenderGateway sender;
    private BankSerializer serializer;

    private JMSBankFrame frame;

    public LoanBrokerAppGateway(JMSBankFrame frame)
    {
        this.frame = frame;
        sender = new MessageSenderGateway("BankInterestReply");
        receiver = new MessageRecieverGateway("BankInterestRequest");
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

    //how the fuck to receive back request and reply and give to GUI
    public void onBankInterestRequestArrived(BankInterestRequest request)
    {
        frame.listModel.addElement(new RequestReply<>(request, null));
        frame.list.repaint();
    }
}
