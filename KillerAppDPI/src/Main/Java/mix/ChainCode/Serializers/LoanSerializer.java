package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.messaging.requestreply.RequestReply;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;
import sun.plugin2.message.Message;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class LoanSerializer {

    private Gson gson;

    public LoanSerializer()
    {
        gson = new Gson();
    }

    //converts loanRequest to string
    public String requestToString(LoanRequest request)
    {
        String object = gson.toJson(request);
        return object;
    }

    //converts string to loanRequest
    public LoanRequest requestFromString(Message msg)
    {
        LoanRequest request = null;
        try {
             request = gson.fromJson(((TextMessage) msg).getText(), LoanRequest.class);

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return request;
    }

    //convert reply to string
    public String replyToString(LoanReply reply)
    {
        String object = gson.toJson(reply);
        return object;
    }

    //converts string to loanReply
    public LoanReply replyFromString(String str)
    {
        LoanReply reply = gson.fromJson((str), LoanReply.class);
        return reply;
    }

    public String replyRequestToString(RequestReply<LoanRequest, LoanReply> requestReply)
    {
        String object = gson.toJson(requestReply);
        return object;
    }



}
