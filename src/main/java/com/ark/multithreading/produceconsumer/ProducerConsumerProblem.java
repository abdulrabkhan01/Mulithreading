package com.ark.multithreading.produceconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerProblem {
    public static void main(String[] args) {
        Queue<Integer> boundedQueue = new LinkedList<>();
        final int maxCapacity = 10;
        Producer producer = new Producer(boundedQueue, maxCapacity);
        Consumer consumer = new Consumer(boundedQueue, maxCapacity);
        Runnable producerRunnable = () -> producer.produce();
        Runnable consumerRunnable = () -> consumer.consume();
        Thread t1 = new Thread(producerRunnable);
        t1.start();
        Thread t2 = new Thread(consumerRunnable);
        t2.start();
    }

    static class Producer {
        private Queue<Integer> boundedQueue;
        private int maxCapacity;
        private int currentItem = 0;

        public Producer(Queue<Integer> boundedQueue, int maxCapacity) {
            this.boundedQueue = boundedQueue;
            this.maxCapacity = maxCapacity;
        }

        public void produce() {
            while (true) {
                synchronized (boundedQueue) {
                    while (boundedQueue.size() == maxCapacity) {
                        try {
                            System.out.println("Producer Thread Going To Wait as Queue is Full");
                            boundedQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Integer newItem = produceNewItem();
                    System.out.println("Producer Producing New Item " + newItem);
                    boundedQueue.add(newItem);
                    boundedQueue.notify();
                }
            }
        }

        private Integer produceNewItem() {
            return ++currentItem;
        }
    }

    static class Consumer {
        private Queue<Integer> boundedQueue;
        private int maxCapacity;

        public Consumer(Queue<Integer> boundedQueue, int maxCapacity) {
            this.boundedQueue = boundedQueue;
            this.maxCapacity = maxCapacity;
        }

        public void consume() {
            while (true) {
                synchronized (boundedQueue) {
                    while (boundedQueue.isEmpty()) {
                        System.out.println("Consumer Thread is Waiting as the Queue is Empty");
                        try {
                            boundedQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Integer element = boundedQueue.poll();
                    System.out.println("Consumer Thread is Consuming Element " + element);
                    boundedQueue.notify();
                }
            }
        }
    }
}
