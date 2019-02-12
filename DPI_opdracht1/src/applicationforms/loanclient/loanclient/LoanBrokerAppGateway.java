package applicationforms.loanclient.loanclient;

import mix.ChainCode.Recievers.MessageRecieverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.LoanSerializer;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

import javax.jms.Message;
import javax.jms.MessageListener;

public class LoanBrokerAppGateway {

    private MessageRecieverGateway reciever;
    private MessageSenderGateway sender;
    private LoanSerializer serializer;

    public LoanBrokerAppGateway()
    {
        sender = new MessageSenderGateway("LoanRequest");
        reciever = new MessageRecieverGateway("LoanReply");
        serializer = new LoanSerializer();
    }

    public void applyForLoan(LoanRequest request)
    {
        String object = serializer.requestToString(request);
        Message msg = sender.createTextmessage(object);

        //set listener here??
        MessageListener listener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                //return null;???

                //get message back

                onLoanReplyArrived();
            }
        };

        reciever.setListener(listener);
        sender.sendMessage(msg);
    }

    //how the fuck to recieve back request and reply
    public void onLoanReplyArrived(LoanRequest request, LoanReply reply)
    {



    }
}
