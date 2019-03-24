package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.model.Replies.HuskyTrainReply;
import mix.model.Requests.HuskyTrainingRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class HuskyTrainReplySerializer {

    private Gson gson;

    public HuskyTrainReplySerializer()
    {
        this.gson = new Gson();
    }

    public String requestToString(HuskyTrainReply reply)
    {
        String object = gson.toJson(reply);
        return object;
    }

    public HuskyTrainReply huskyFromString(Message msg)
    {
        HuskyTrainReply reply = null;
        try {
            reply = gson.fromJson(((TextMessage) msg).getText(), HuskyTrainReply.class);

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return reply;
    }

    public String replyToString(HuskyTrainReply husky)
    {
        String object = gson.toJson(husky);
        return object;
    }

    public HuskyTrainReply replyFromString(String str)
    {
        HuskyTrainReply husky = gson.fromJson((str), HuskyTrainReply.class);
        return husky;
    }
}
