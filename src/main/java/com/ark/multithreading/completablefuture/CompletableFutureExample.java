package com.ark.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * <p> Example for Completable Future </p>
 *
 * @author AbdulR
 */
public class CompletableFutureExample {
    public static void main(String[] args)  {
        CompletableFuture<Void> future1 = completableFutureRunAsync();
        future1.join();
        System.out.println("Main Thread End");
    }

    private static CompletableFuture<Void> completableFutureRunAsync() {
        LongRunningTask task = new LongRunningTask();
        System.out.println("Scheduling Long Running Task");
        return CompletableFuture.runAsync(task::run)
                .thenRun(() -> System.out.println("Successfully completed!"));
    }
}

class LongRunningTask {
    public void run() {
        System.out.println(" Task running Thread " + Thread.currentThread().getName());
        for (int i = 0; i < 20; i++) {
            sleepForSomeTime(); //Adding delay in computation
            System.out.println(i);
        }
    }

    private void sleepForSomeTime() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
