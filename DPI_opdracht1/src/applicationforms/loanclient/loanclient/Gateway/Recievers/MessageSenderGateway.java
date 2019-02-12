package applicationforms.loanclient.loanclient.Gateway.Recievers;


import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class MessageSenderGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    public MessageSenderGateway(String channelName)
    {

    }

    public Message createtextmessage(String Body)
    {
        return null;
    }

    public void sendmessage(Message msg)
    {

    }









}
