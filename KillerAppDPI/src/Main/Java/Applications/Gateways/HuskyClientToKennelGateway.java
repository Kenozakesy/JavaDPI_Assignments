package Applications.Gateways;

import Applications.Controllers.HuskyClientController;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.model.Husky;
import mix.model.Owner;

import javax.jms.Message;
import javax.jms.MessageListener;

public class HuskyClientToKennelGateway {

    private HuskyClientController controller;

    private MessageReceiverGateway receiver;
    private MessageSenderGateway sender;
    private HuskySerializer serializer;

    private String senderGateway = "HuskyTrainRequest";
    private String receiverGateway = "HuskyReply";

    public HuskyClientToKennelGateway(HuskyClientController controller)
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

    public void sendHuskyToKennel(Husky husky)
    {
        String object = serializer.requestToString(husky);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    public void onMessageArrived(Husky husky)
    {
        controller.receiveTrainedHusky(husky);
    }
}
