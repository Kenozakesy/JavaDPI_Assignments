package Applications.Gateways;

import Applications.Controllers.KennelController;
import Applications.Controllers.SchoolController;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.ChainCode.Serializers.HuskyTrainReplySerializer;
import mix.ChainCode.Serializers.HuskyTrainRequestSerializer;
import mix.model.Husky;
import mix.model.Replies.HuskyTrainReply;
import mix.model.Requests.HuskyTrainingRequest;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskyKennelToSchoolGatewayPrivate {

    private KennelController controller;

    private MessageReceiverGateway receiver;
    private MessageSenderGateway sender;
    private HuskyTrainRequestSerializer requestSerializer;
    private HuskyTrainReplySerializer replySerializer;

    private String senderGateway = "test";
    private String receiverGateway = "HuskyTrainReply";

    public HuskyKennelToSchoolGatewayPrivate(KennelController controller)
    {
        this.controller = controller;

        sender = new MessageSenderGateway(senderGateway); //kind of useless
        receiver = new MessageReceiverGateway(receiverGateway);
        requestSerializer = new HuskyTrainRequestSerializer();
        replySerializer = new HuskyTrainReplySerializer();

        MessageListener listener = message -> {
            HuskyTrainReply reply = replySerializer.huskyFromString(message);
            onMessageArrived(reply);
        };
        receiver.setListener(listener);
    }

    public synchronized void sendMessage(HuskyTrainingRequest request)
    {
        //create new sender for ones
        sender = new MessageSenderGateway(request.getSchoolAddress());

        String object = requestSerializer.requestToString(request);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    public void onMessageArrived(HuskyTrainReply reply)
    {
        controller.receiveHuskyTrainReply(reply);
    }
}
