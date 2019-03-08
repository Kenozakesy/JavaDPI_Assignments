package mix;

import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class Messages {

    private Session session;
    private MessageProducer producer;

    //Establish the connection
    public Messages(String hostname, boolean topic, String queueName)
    {
        String listener = topic ? "topic." : "queue.";
        Connection connection; // to connect to the ActiveMQ
        Destination sendDestination; // reference to a queue/topic destination

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

            // connect to the sender destination
            sendDestination = (Destination) jndiContext.lookup(queueName);
            producer = session.createProducer(sendDestination);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    //Creates and sends a text message
    public void SendMessage(String message)
    {
        try
        {
            Message msg = session.createTextMessage(message);
            // print all message attributes; but JMSDestination is null
            // session makes the message via MctiveMQ. AtiveMQ assigns unique JMSMessageID
            // to each message.
            System.out.println(msg);
            producer.send(msg);
            //print all message attributes; but JMSDestination is senderDestination name
            System.out.println(msg);
                //print only the attributes you want to see
                System.out.println("JMSMessageID=" + msg.getJMSMessageID()
                        + "    JMSDestination=" + msg.getJMSDestination()
                        + "    Text=" + ((TextMessage) msg).getText());
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }
}
