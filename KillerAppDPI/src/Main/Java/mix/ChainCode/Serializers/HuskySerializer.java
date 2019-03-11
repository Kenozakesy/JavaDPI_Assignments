package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.messaging.requestreply.RequestReply;
import mix.model.Husky;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;
import sun.plugin2.message.Message;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class HuskySerializer {

    private Gson gson;

    public HuskySerializer()
    {
        gson = new Gson();
    }

    public String requestToString(Husky husky)
    {
        String object = gson.toJson(husky);
        return object;
    }

    public Husky huskyFromString(Message msg)
    {
        Husky husky = null;
        try {
             husky = gson.fromJson(((TextMessage) msg).getText(), Husky.class);

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return husky;
    }

    public String replyToString(Husky husky)
    {
        String object = gson.toJson(husky);
        return object;
    }

    public Husky replyFromString(String str)
    {
        Husky husky = gson.fromJson((str), Husky.class);
        return husky;
    }

//    public String replyRequestToString(RequestReply<LoanRequest, LoanReply> requestReply)
//    {
//        String object = gson.toJson(requestReply);
//        return object;
//    }



}
