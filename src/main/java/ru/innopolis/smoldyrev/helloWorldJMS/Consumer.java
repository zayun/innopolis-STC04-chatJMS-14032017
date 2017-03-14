package ru.innopolis.smoldyrev.helloWorldJMS;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by smoldyrev on 14.03.17.
 */
public class Consumer implements Runnable{
    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("destName");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            Message message = messageConsumer.receive(10000);

            System.out.println(((TextMessage) message).getText());
            session.close();
            myConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
