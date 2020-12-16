package com.ark.multithreading.threadlocal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p> Example to illustrate the use of Thread Local. </p>
 */
public class ThreadLocalExample {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        Runnable r1 = () -> sharedObject.doStuff();
        Thread t1 = new Thread(r1,"T1");
        Thread t2 = new Thread(r1, "T2");
        Thread t3 = new Thread(r1, "T3");
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * <p> Object shared among multiple threads.</p>
 */
class SharedObject {
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public void doStuff() {
        threadLocal.set(0);
        System.out.println("doStuff() called for Thread " + Thread.currentThread().getName());
        Integer value = threadLocal.get();
        value++;
        threadLocal.set(value);
        System.out.println("Initilize Thread Local by 1 by Thread " + Thread.currentThread().getName());
        doSomeMoreStuff();
    }

    private void doSomeMoreStuff() {
        System.out.println("doStuff() called for Thread " + Thread.currentThread().getName());
        System.out.println("Value for thread local for thread " + Thread.currentThread().getName() + " = " + threadLocal.get());

    }

}
