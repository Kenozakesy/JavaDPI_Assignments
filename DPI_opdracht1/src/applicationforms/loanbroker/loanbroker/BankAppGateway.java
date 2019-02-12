package applicationforms.loanbroker.loanbroker;

import mix.ChainCode.Recievers.MessageRecieverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.LoanSerializer;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

import javax.jms.Message;
import javax.jms.MessageListener;

public class BankAppGateway {

    private MessageRecieverGateway reciever;
    private MessageSenderGateway sender;
    private LoanSerializer serializer;

    public BankAppGateway()
    {
        sender = new MessageSenderGateway("LoanRequest");
        reciever = new MessageRecieverGateway("LoanReply");
        serializer = new LoanSerializer();
    }

    public void applyForLoan(LoanRequest request)
    {
        String object = serializer.requestToString(request);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
        //set listener here??

    }

    public void onLoanReplyArrived(LoanRequest request, LoanReply reply)
    {
        MessageListener listener = new MessageListener() {
            @Override
            public void onMessage(Message message) {

            }
        };
        reciever.setListener(listener);

    }
}
