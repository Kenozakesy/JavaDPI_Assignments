package applicationforms.loanclient.loanclient.Gateway.Recievers;

import javax.jms.*;

public class MessageRecieverGateway {


    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    public MessageRecieverGateway(String channelName)
    {

    }

    public void setListener(MessageListener ml)
    {

    }

}
