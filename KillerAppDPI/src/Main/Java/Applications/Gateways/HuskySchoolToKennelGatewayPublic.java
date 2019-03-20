package Applications.Gateways;

import Applications.Controllers.KennelController;
import Applications.Controllers.SchoolController;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.model.Husky;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskySchoolToKennelGatewayPublic {
    private SchoolController controller;

    private MessageReceiverGateway receiver;
    private MessageSenderGateway sender;
    private HuskySerializer serializer;

    private String senderGateway = "HuskyTestReply";
    private String receiverGateway = "HuskyTestRequest";

    public HuskySchoolToKennelGatewayPublic(SchoolController controller)
    {
        this.controller = controller;

        sender = new MessageSenderGateway(senderGateway);
        receiver = new MessageReceiverGateway(receiverGateway);
        serializer = new HuskySerializer();

        MessageListener listener = message -> {
            Husky husky = serializer.huskyFromString(message);
            onMessageArrived(husky);
        };
        receiver.setListener(listener);
    }

    public void sendMessage(Husky husky)
    {
        String object = serializer.requestToString(husky);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    public void onMessageArrived(Husky husky)
    {
        controller.receiveHuskyFromClient(husky);
    }
}
