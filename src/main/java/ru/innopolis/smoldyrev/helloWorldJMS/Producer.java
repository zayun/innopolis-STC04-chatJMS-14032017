package ru.innopolis.smoldyrev.helloWorldJMS;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * Created by smoldyrev on 14.03.17.
 */
public class Producer implements Runnable {

    @Override
    public void run() {


        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("destName");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage("Some text here");
            producer.send(message);
            session.close();
            myConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
