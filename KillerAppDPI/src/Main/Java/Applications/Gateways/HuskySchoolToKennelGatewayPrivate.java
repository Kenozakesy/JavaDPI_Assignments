package Applications.Gateways;

import Applications.Controllers.KennelController;
import Applications.Controllers.SchoolController;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.ChainCode.Serializers.HuskyTestRequestSerializer;
import mix.ChainCode.Serializers.HuskyTrainReplySerializer;
import mix.ChainCode.Serializers.HuskyTrainRequestSerializer;
import mix.model.Husky;
import mix.model.Replies.HuskyTrainReply;
import mix.model.Requests.HuskyTestRequest;
import mix.model.Requests.HuskyTrainingRequest;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskySchoolToKennelGatewayPrivate {

    private SchoolController controller;

    private MessageReceiverGateway receiver;
    private MessageSenderGateway sender;
    private HuskyTrainRequestSerializer requestSerializer;
    private HuskyTrainReplySerializer replySerializer;

    private String senderGateway = "HuskyTrainReply";
    private String receiverGateway = "HuskyTestReply";

    public HuskySchoolToKennelGatewayPrivate(SchoolController controller)
    {
        this.controller = controller;

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        this.receiverGateway = generatedString;

        sender = new MessageSenderGateway(senderGateway);
        receiver = new MessageReceiverGateway(generatedString);
        replySerializer = new HuskyTrainReplySerializer();
        requestSerializer = new HuskyTrainRequestSerializer();

        MessageListener listener = message -> {
            HuskyTrainingRequest request = requestSerializer.huskyFromString(message);
            onMessageArrived(request);
        };
        receiver.setListener(listener);
    }

    public String getSenderGateway() {
        return senderGateway;
    }
    public String getReceiverGateway() {
        return receiverGateway;
    }

    public void sendMessage(HuskyTrainReply reply)
    {
        String object = replySerializer.requestToString(reply);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    public void onMessageArrived(HuskyTrainingRequest request)
    {
        controller.receiveTrainRequestFromClient(request);
    }




}
