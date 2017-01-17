package com.rrybalkin.concurrent;

public interface Lock {

    void lock() throws InterruptedException;

    void unlock();
}
