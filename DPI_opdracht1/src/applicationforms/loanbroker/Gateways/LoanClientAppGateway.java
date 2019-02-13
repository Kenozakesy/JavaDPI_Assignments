package applicationforms.loanbroker.Gateways;

import applicationforms.loanbroker.loanbroker.JListLine;
import applicationforms.loanbroker.loanbroker.LoanBrokerFrame;
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

/**
 * Created by Gebruiker on 13-2-2019.
 */
public class LoanClientAppGateway {

    private MessageRecieverGateway receiver;
    private MessageSenderGateway sender;
    private LoanSerializer serializer;

    private LoanBrokerFrame frame;
    public LoanClientAppGateway(LoanBrokerFrame frame)
    {
        this.frame = frame;
        sender = new MessageSenderGateway("LoanReply");
        receiver = new MessageRecieverGateway("LoanRequest");
        serializer = new LoanSerializer();

        MessageListener listener = msg -> {
            LoanRequest request = serializer.requestFromString(msg);
            onLoanRequestArrived(request);
        };
        receiver.setListener(listener);
    }

    public void sendLoanReply(LoanRequest request, LoanReply reply)
    {
        RequestReply<LoanRequest, LoanReply> loanRequestReply = new RequestReply(request, reply);
        String object = serializer.replyRequestToString(loanRequestReply);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    //how the fuck to receive back request and reply and give to GUI
    public void onLoanRequestArrived(LoanRequest request)
    {
        frame.broker.add(request);
        BankInterestRequest bankRequest = new BankInterestRequest(request.getAmount(), request.getTime());
        frame.passRequestToBank(bankRequest);
    }
}
