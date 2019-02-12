package applicationforms.loanclient.loanclient.Gateway;

import applicationforms.loanclient.loanclient.Gateway.Recievers.MessageRecieverGateway;
import applicationforms.loanclient.loanclient.Gateway.Recievers.MessageSenderGateway;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

public class LoanBrokerAppGateway {

    public MessageRecieverGateway reciever;
    public MessageSenderGateway sender;
    public LoanSerializer serializer;

    public LoanBrokerAppGateway()
    {

    }

    public void applyForLoan(LoanRequest request)
    {

    }

    public void onLoanReplyArrived(LoanRequest request, LoanReply reply)
    {

    }
}
