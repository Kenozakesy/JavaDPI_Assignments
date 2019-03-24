package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.model.Requests.HuskyTestRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class HuskyTestRequestSerializer {

    private Gson gson;

    public HuskyTestRequestSerializer()
    {
        this.gson = new Gson();
    }

    public String requestToString(HuskyTestRequest reply)
    {
        String object = gson.toJson(reply);
        return object;
    }

    public HuskyTestRequest huskyFromString(Message msg)
    {
        HuskyTestRequest reply = null;
        try {
            reply = gson.fromJson(((TextMessage) msg).getText(), HuskyTestRequest.class);

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return reply;
    }

    public String replyToString(HuskyTestRequest husky)
    {
        String object = gson.toJson(husky);
        return object;
    }

    public HuskyTestRequest replyFromString(String str)
    {
        HuskyTestRequest husky = gson.fromJson((str), HuskyTestRequest.class);
        return husky;
    }
}
