package mix;

import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class ReceiveMessages {

    public MessageConsumer consumer;

    public ReceiveMessages(String hostname, boolean topic, String queueName)
    {
        String listener = topic ? "topic." : "queue.";

        Connection connection; // to connect to the JMS
        Session session; // session for creating consumers

        Destination receiveDestination; // reference to a queue/topic destination

        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, hostname);

            // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or “topic.myFirstDestination”
            props.put((listener + queueName), queueName);

            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // connect to the receiver destination
            receiveDestination = (Destination) jndiContext.lookup(queueName);
            consumer = session.createConsumer(receiveDestination);

            connection.start(); // this is needed to start receiving messages

        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }


    }
}
