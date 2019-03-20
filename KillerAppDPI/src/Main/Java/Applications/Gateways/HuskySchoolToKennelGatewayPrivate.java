package Applications.Gateways;

import Applications.Controllers.KennelController;
import Applications.Controllers.SchoolController;
import mix.ChainCode.Recievers.MessageReceiverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.model.Husky;

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
    private HuskySerializer serializer;

    private String senderGateway = "HuskyTestRequest";
    private String receiverGateway = "HuskyTestReply";

    public HuskySchoolToKennelGatewayPrivate(SchoolController controller)
    {
        this.controller = controller;

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        sender = new MessageSenderGateway(senderGateway);
        receiver = new MessageReceiverGateway(generatedString);
        serializer = new HuskySerializer();

        MessageListener listener = message -> {
            Husky husky = serializer.huskyFromString(message);
            onMessageArrived(husky);
        };
        receiver.setListener(listener);
    }

    public String getSenderGateway() {
        return senderGateway;
    }
    public String getReceiverGateway() {
        return receiverGateway;
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
