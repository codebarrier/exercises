package com.learning.threading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerMultiple {

    static Queue<String> sharedQueue = new LinkedList<>();

    public static void main(String args[]) {

        Producer producer = new Producer("Producer1");
        Consumer consumer1 = new Consumer("Consumer1");
        Consumer consumer2 = new Consumer("Consumer2");
        Consumer consumer3 = new Consumer("Consumer3");
        Consumer consumer4 = new Consumer("Consumer4");
        Consumer consumer5 = new Consumer("Consumer5");
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();
        new Thread(consumer4).start();
        new Thread(consumer5).start();
    }

    public static class Producer implements Runnable {
        String producerName;
        AtomicInteger itemNameCounter = new AtomicInteger(0);

        public Producer(String producerName) {
            this.producerName = producerName;
        }

        public void produce() throws InterruptedException {
            synchronized (sharedQueue) {
                System.out.println(producerName + " has produced item" + itemNameCounter.incrementAndGet());
                sharedQueue.add("item" + itemNameCounter.get());//adding the product to the queue
                sharedQueue.notifyAll();
                sharedQueue.wait();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        String consumerName;
        static AtomicInteger totalConsumersCount = new AtomicInteger(0);
        static Integer consumptionCountForItem = 0;

        public Consumer(String consumerName) {
            this.consumerName = consumerName;
        }

        public void consume() throws InterruptedException {

            synchronized (sharedQueue) {
                if (sharedQueue.size() <= 0) {
                    sharedQueue.wait();
                }

                System.out.println(consumerName + " has " + "consumed " + sharedQueue.peek());// Consumer is consuming the item.

                if (++consumptionCountForItem == totalConsumersCount.get()) {
                    sharedQueue.remove();
                    sharedQueue.notifyAll();
                    consumptionCountForItem = 0;
                }
                sharedQueue.wait();
            }
        }

        @Override
        public void run() {
            totalConsumersCount.incrementAndGet();
            while (true) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    totalConsumersCount.decrementAndGet();
                }
            }
        }
    }
}
