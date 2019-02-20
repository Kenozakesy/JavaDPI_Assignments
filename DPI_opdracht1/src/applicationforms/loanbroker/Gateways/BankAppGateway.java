package applicationforms.loanbroker.Gateways;

import applicationforms.loanbroker.loanbroker.Aggregator.BankCheckSending;
import applicationforms.loanbroker.loanbroker.LoanBrokerFrame;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

/**
 * Created by Gebruiker on 13-2-2019.
 */
public class BankAppGateway {

    private MessageRecieverGateway receiver;
    private MessageSenderGateway senderAMRO;
    private MessageSenderGateway senderING;
    private MessageSenderGateway senderRabobank;
    private BankSerializer serializer;

    private LoanBrokerFrame frame;
    public BankAppGateway(LoanBrokerFrame frame)
    {
        this.frame = frame;

        senderAMRO = new MessageSenderGateway("BankInterestRequestABNAmro");
        senderING = new MessageSenderGateway("BankInterestRequestING");
        senderRabobank = new MessageSenderGateway("BankInterestRequestRabobank");

        receiver = new MessageRecieverGateway("BankInterestReply");
        serializer = new BankSerializer();

        MessageListener listener = msg -> {
            try {
                java.lang.reflect.Type type = new TypeToken<RequestReply<BankInterestRequest, BankInterestReply>>(){}.getType();
                RequestReply<BankInterestRequest, BankInterestReply> requestReply = new Gson().fromJson(((TextMessage) msg).getText(), type);
                BankInterestRequest request = requestReply.getRequest();
                BankInterestReply reply = requestReply.getReply();
                onBankReplyArrived(request, reply);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        };
        receiver.setListener(listener);
    }

    public void sendBankRequest(BankInterestRequest request, BankCheckSending check)
    {
        String object = serializer.requestToString(request);
        if(check.isRabobank())
        {
            Message msg = senderRabobank.createTextmessage(object);
            senderRabobank.sendMessage(msg);
        }
        if(check.isING())
        {
            Message msg = senderING.createTextmessage(object);
            senderING.sendMessage(msg);
        }
        if(check.isABNAmro())
        {
            Message msg = senderAMRO.createTextmessage(object);
            senderAMRO.sendMessage(msg);
        }
    }

    public void onBankReplyArrived(BankInterestRequest request, BankInterestReply reply)
    {
        frame.passReplyToClient(request, reply);
    }
}
