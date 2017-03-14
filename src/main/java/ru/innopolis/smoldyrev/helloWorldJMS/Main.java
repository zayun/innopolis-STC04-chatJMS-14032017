package ru.innopolis.smoldyrev.helloWorldJMS;

/**
 * Created by smoldyrev on 14.03.17.
 */
public class Main {
    public static void main(String[] args) {
        Thread consumer = new Thread(new Consumer());
        consumer.start();

        Thread producer = new Thread(new Producer());
        producer.start();

    }
}

