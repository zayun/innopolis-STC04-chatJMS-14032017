package ru.innopolis.smoldyrev.chatJMS;

/**
 * Created by smoldyrev on 14.03.17.
 */
public class Main {
    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        producer.start();

        Thread cons = new Thread(new Consumer());
        cons.start();
    }
}
