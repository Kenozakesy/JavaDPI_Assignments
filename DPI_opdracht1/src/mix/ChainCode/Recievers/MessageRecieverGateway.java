package mix.ChainCode.Recievers;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class MessageRecieverGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;

    private String hostname;

    public MessageRecieverGateway(String channelName)
    {
        hostname = "tcp://localhost:61616";
        String listener = false ? "topic." : "queue.";

        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, hostname);

            // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or “topic.myFirstDestination”
            props.put((listener + channelName), channelName);

            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // connect to the receiver destination
            destination = (Destination) jndiContext.lookup(channelName);
            consumer = session.createConsumer(destination);

            connection.start(); // this is needed to start receiving messages

        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    public void setListener(MessageListener ml) {

        try {
            consumer.setMessageListener(ml);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
