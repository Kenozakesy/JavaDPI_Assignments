package applicationforms.loanclient.loanclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mix.ChainCode.Recievers.MessageRecieverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.LoanSerializer;
import mix.messaging.requestreply.RequestReply;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.*;

public class LoanBrokerAppGateway {

    private MessageRecieverGateway receiver;
    private MessageSenderGateway sender;
    private LoanSerializer serializer;

    public LoanBrokerAppGateway()
    {
        sender = new MessageSenderGateway("LoanRequest");
        receiver = new MessageRecieverGateway("LoanReply");
        serializer = new LoanSerializer();
    }

    public void applyForLoan(LoanRequest request)
    {
        String object = serializer.requestToString(request);
        Message msg = sender.createTextmessage(object);

        MessageListener listener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    //convert to message reply
                    java.lang.reflect.Type type = new TypeToken<RequestReply<LoanRequest, LoanReply>>(){}.getType();
                    RequestReply<LoanRequest, LoanReply> requestReply = new Gson().fromJson(((TextMessage) message).getText(), type);
                    LoanRequest loanRequest = requestReply.getRequest();
                    LoanReply loanReply = requestReply.getReply();

                    //say that message arrived
                    onLoanReplyArrived(loanRequest, loanReply);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };
        receiver.setListener(listener);
        sender.sendMessage(msg);
    }

    //how the fuck to receive back request and reply and give to GUI
    public void onLoanReplyArrived(LoanRequest request, LoanReply reply)
    {
        System.out.println(request.getSsn());
        System.out.println(reply.getInterest());
        System.out.println("test this");
    }
}
