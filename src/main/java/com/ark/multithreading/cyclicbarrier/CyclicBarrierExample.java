package com.ark.multithreading.cyclicbarrier;

import java.util.concurrent.*;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier barrierWithTwoParties = new CyclicBarrier(2);
        Runnable task1 = () -> {
            initializeTask1(barrierWithTwoParties);

        };
        Runnable task2 = () -> {
            initializeTask2(barrierWithTwoParties);

        };
        //Start above threads at the same
        ExecutorService executor =  Executors.newFixedThreadPool(2);
        executor.execute(task1);
        executor.execute(task2);
       //When above thread2 calls await then barrier can be passed and task1 and task2 continue with the processing
        executor.shutdown();

    }

    private static void initializeTask2(CyclicBarrier barrierWithTwoParties) {
        System.out.println("Tasks 2 is waiting to start processing");
        try {
            barrierWithTwoParties.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Task 2 is started");
        for(int i =0; i< 100; i++) {
            System.out.println("Tasks 2 is processing data "+i);
        }
        System.out.println("Task 2 is completed");
    }

    private static void initializeTask1(CyclicBarrier barrierWithTwoParties) {
        System.out.println("Tasks 1 is waiting to start processing");
        try {
            barrierWithTwoParties.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Task 1 is Started");
        try {
            Thread.sleep(5000L); //Just sleep for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task 1 is Completed");
    }
}
