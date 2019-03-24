package Applications.Gateways;

import Applications.Controllers.KennelController;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskyTestReplySerializer;
import mix.ChainCode.Serializers.HuskyTestRequestSerializer;
import mix.model.Replies.HuskyTestReply;
import mix.model.Requests.HuskyTestRequest;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskyKennelToSchoolGatewayPublic {

    private KennelController controller;

    private MessageReceiverGateway receiver;
    private MessageSenderGateway sender;
    private HuskyTestRequestSerializer requestSerializer;
    private HuskyTestReplySerializer testSerializer;

    private String senderGateway = "HuskyTestRequest";
    private String receiverGateway = "HuskyTestReply";

    public HuskyKennelToSchoolGatewayPublic(KennelController controller)
    {
        this.controller = controller;

        sender = new MessageSenderGateway(senderGateway);
        receiver = new MessageReceiverGateway(receiverGateway);
        requestSerializer = new HuskyTestRequestSerializer();
        testSerializer = new HuskyTestReplySerializer();

        MessageListener listener = message -> {
            HuskyTestReply reply = testSerializer.huskyFromString(message);
            onMessageArrived(reply);
        };
        receiver.setListener(listener);
    }

    public void sendMessage(HuskyTestRequest testRequest)
    {
        String object = requestSerializer.requestToString(testRequest);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    public void onMessageArrived(HuskyTestReply reply)
    {
        controller.receiveHuskyTestReply(reply);
    }
}
