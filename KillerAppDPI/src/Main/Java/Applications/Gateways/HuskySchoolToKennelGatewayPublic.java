package Applications.Gateways;

import Applications.Controllers.SchoolController;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.ChainCode.Serializers.HuskyTestReplySerializer;
import mix.ChainCode.Serializers.HuskyTestRequestSerializer;
import mix.model.Husky;
import mix.model.HuskyTestReply;
import mix.model.HuskyTestRequest;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskySchoolToKennelGatewayPublic {
    private SchoolController controller;

    private MessageReceiverGateway receiver;
    private MessageSenderGateway sender;
    private HuskyTestRequestSerializer requestSerializer;
    private HuskyTestReplySerializer replySerializer;

    private String senderGateway = "HuskyTestReply";
    private String receiverGateway = "HuskyTestRequest";

    public HuskySchoolToKennelGatewayPublic(SchoolController controller)
    {
        this.controller = controller;

        sender = new MessageSenderGateway(senderGateway);
        receiver = new MessageReceiverGateway(receiverGateway);
        requestSerializer = new HuskyTestRequestSerializer();
        replySerializer = new HuskyTestReplySerializer();

        MessageListener listener = message -> {
            HuskyTestRequest request = requestSerializer.huskyFromString(message);
            onMessageArrived(request);
        };
        receiver.setListener(listener);
    }

    public void sendMessage(HuskyTestReply reply)
    {
        String object = replySerializer.requestToString(reply);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    public void onMessageArrived(HuskyTestRequest request)
    {
        controller.receiveTestRequestFromClient(request);
    }
}
