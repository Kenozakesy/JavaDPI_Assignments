package mix.ChainCode.Recievers;


import mix.Messages;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class MessageSenderGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    //temporary field
    private String hostname;

    public MessageSenderGateway(String channelName)
    {
        hostname = "tcp://localhost:61616";
        String listener = false ? "topic." : "queue.";

        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, hostname);

            // queue or topic: “queue.myFirstDestination” or “topic.myFirstDestination”
            props.put((listener + channelName), channelName);

            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // connect to the sender destination
            destination = (Destination) jndiContext.lookup(channelName);
            producer = session.createProducer(destination);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    public Message createTextmessage(String body)
    {
        Message msg = null;
        try {
            msg = session.createTextMessage(body);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void sendMessage(Message msg)
    {
        try
        {
            System.out.println(msg);
            producer.send(msg);
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }









}
