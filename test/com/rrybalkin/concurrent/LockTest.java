package com.rrybalkin.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roman Rybalkin
 * 09.10.16
 */
public class LockTest {

    public static void main(String[] args) {
        final Lock lock = new ReentrantExclusiveLock();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread = " + Thread.currentThread().getName() + " started");
                    lock.lock();
                    System.out.println("Thread = " + Thread.currentThread().getName() + " lock");
                    TimeUnit.SECONDS.sleep(5);
                    lock.lock();
                    System.out.println("Thread = " + Thread.currentThread().getName() + " lock again");
                    lock.unlock();
                    TimeUnit.SECONDS.sleep(1);
                    lock.unlock();
                    System.out.println("Thread = " + Thread.currentThread().getName() + " unlock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Thread = " + Thread.currentThread().getName() + " started");
                    lock.lock();
                    System.out.println("Thread = " + Thread.currentThread().getName() + " lock");
                    TimeUnit.SECONDS.sleep(4);
                    lock.unlock();
                    System.out.println("Thread = " + Thread.currentThread().getName() + " unlock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
    }
}
