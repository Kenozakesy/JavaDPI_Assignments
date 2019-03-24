package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.model.Requests.HuskyTestRequest;
import mix.model.Requests.HuskyTrainingRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class HuskyTrainRequestSerializer {

    private Gson gson;

    public HuskyTrainRequestSerializer()
    {
        this.gson = new Gson();
    }

    public String requestToString(HuskyTrainingRequest reply)
    {
        String object = gson.toJson(reply);
        return object;
    }

    public HuskyTrainingRequest huskyFromString(Message msg)
    {
        HuskyTrainingRequest reply = null;
        try {
            reply = gson.fromJson(((TextMessage) msg).getText(), HuskyTrainingRequest.class);

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return reply;
    }

    public String replyToString(HuskyTrainingRequest husky)
    {
        String object = gson.toJson(husky);
        return object;
    }

    public HuskyTrainingRequest replyFromString(String str)
    {
        HuskyTrainingRequest husky = gson.fromJson((str), HuskyTrainingRequest.class);
        return husky;
    }
}
