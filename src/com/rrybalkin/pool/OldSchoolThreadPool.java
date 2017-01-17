package com.rrybalkin.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OldSchoolThreadPool implements ThreadPool {
    private final Queue<Runnable> tasks;
    private List<PooledThread> pool;
    private boolean isStopped;

    public OldSchoolThreadPool(int size) {
        this.tasks = new LinkedList<>();
        pool = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            PooledThread thread = new PooledThread(tasks);
            thread.start();
            pool.add(thread);
        }
    }

    @Override
    public synchronized void submit(Runnable task) {
        if (isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }
        tasks.add(task);
        tasks.notify();
    }

    @Override
    public synchronized void stop() {
        this.isStopped = true;
        for (PooledThread t : pool) {
            t.shutdown();
        }
    }
}

class PooledThread extends Thread {
    private final Queue<Runnable> taskQueue;
    private boolean isStopped;

    public PooledThread(Queue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isStopped) {
            synchronized (taskQueue) {
                Runnable nextTask = taskQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                } else {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void shutdown() {
        isStopped = true;
        this.interrupt();
    }

    public boolean isStopped() {
        return isStopped;
    }
}
