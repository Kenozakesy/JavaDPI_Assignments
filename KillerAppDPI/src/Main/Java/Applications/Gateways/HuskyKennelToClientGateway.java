package Applications.Gateways;

import Applications.Controllers.HuskyClientController;
import Applications.Controllers.KennelController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.messaging.requestreply.RequestReply;
import mix.model.Husky;
import mix.model.Kennel;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Gebruiker on 12-3-2019.
 */
public class HuskyKennelToClientGateway {

    private KennelController controller;

    private MessageReceiverGateway receiver;
    private MessageSenderGateway sender;
    private HuskySerializer serializer;

    private String senderGateway = "HuskyReply";
    private String receiverGateway = "HuskyTrainRequest";

    public HuskyKennelToClientGateway(KennelController controller)
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
