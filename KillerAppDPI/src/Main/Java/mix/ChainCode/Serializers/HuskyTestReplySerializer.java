package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.model.Husky;
import mix.model.HuskyTestReply;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class HuskyTestReplySerializer {

    private Gson gson;

    public HuskyTestReplySerializer()
    {
        this.gson = new Gson();
    }

    public String requestToString(HuskyTestReply reply)
    {
        String object = gson.toJson(reply);
        return object;
    }

    public HuskyTestReply huskyFromString(Message msg)
    {
        HuskyTestReply reply = null;
        try {
            reply = gson.fromJson(((TextMessage) msg).getText(), HuskyTestReply.class);

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return reply;
    }

    public String replyToString(Husky husky)
    {
        String object = gson.toJson(husky);
        return object;
    }

    public HuskyTestReply replyFromString(String str)
    {
        HuskyTestReply husky = gson.fromJson((str), HuskyTestReply.class);
        return husky;
    }
}
