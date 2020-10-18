package com.ark.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        Runnable task1 = () -> { latch.countDown();
            System.out.println("Task 1 is completed");
        };
        Runnable task2 = () -> {
        for(int i =0; i< 100; i++) {
            System.out.println("Tasks 2 is processing data "+i);
        }
            System.out.println("Task 2 is completed");
            latch.countDown();
        };
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
        try {
            System.out.println("Main thread is waiting for all tasks to be completed");
            latch.await();
            System.out.println("All threads are processed..Now Main Thread can finish waiting");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
