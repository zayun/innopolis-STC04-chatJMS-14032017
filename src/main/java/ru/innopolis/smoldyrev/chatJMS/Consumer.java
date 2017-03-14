package ru.innopolis.smoldyrev.chatJMS;

import com.sun.security.ntlm.Client;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * Created by smoldyrev on 14.03.17.
 */
public class Consumer implements Runnable {
    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.240.17.94:61616");
//            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://188.130.155.154:61616");
            Connection myConnection = factory.createConnection();
            myConnection.start();
//            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Session session = myConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Destination destination = session.createTopic("destName");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            while (true) {
                Message message = messageConsumer.receive(10000);

                if (message!=null) {
                    System.out.print(new Date(((TextMessage) message).getJMSTimestamp())+": ");
                    System.out.println(((TextMessage) message).getText());
                    message.acknowledge();
                    if (message.equals("/q")) break;
                }
            }
            session.close();
            myConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
