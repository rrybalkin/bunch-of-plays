package com.rrybalkin.experiments;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class OutOfMemoryErrorFactory {

    public static void main(String[] args) {
        invokeOOMDueToNativeThreadsLimitExceeded();
        invokeOOMDueToArraySizeLimitExceeded();
    }

    private static void invokeOOMDueToArraySizeLimitExceeded() {
        new ArrayList<>(Integer.MAX_VALUE - 8);
        System.out.println("Should be still OK, but...");
        new ArrayList<>(Integer.MAX_VALUE - 7); // throws OOM
    }

    private static void invokeOOMDueToNativeThreadsLimitExceeded() {
        AtomicInteger number = new AtomicInteger(0);
        try {
            while (true) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            number.incrementAndGet();
                            Thread.sleep(10000000);
                        } catch (InterruptedException e) {
                            // never mind
                        }
                    }
                }).start();
            }
        } catch (Throwable e) {
            System.err.println("Error when " + number.get() + "th thread creating");
            e.printStackTrace();
        }
    }
}
