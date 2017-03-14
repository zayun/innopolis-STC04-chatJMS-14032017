package ru.innopolis.smoldyrev.chatJMS;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;


/**
 * Created by smoldyrev on 14.03.17.
 */
public class Producer implements Runnable {

    @Override
    public void run() {

        try {


            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.240.17.94:61616");

            Connection myConnection = factory.createConnection();
            myConnection.start();
//            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Session session = myConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Destination destination = session.createTopic("destName");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            Scanner scanner = new Scanner(System.in);
            String msg = null;
            while (!((msg = scanner.nextLine()).equals("/q"))) {
                TextMessage message = session.createTextMessage(msg);
                producer.send(message);
            }
            session.close();
            myConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
