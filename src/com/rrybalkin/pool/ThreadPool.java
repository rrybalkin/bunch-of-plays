package com.rrybalkin.pool;

public interface ThreadPool {

    void submit(Runnable task);

    void stop();
}
