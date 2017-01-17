package com.rrybalkin.concurrent;

public class ExclusiveLock implements Lock {
    private boolean isLocked = false;
    private Thread lockedBy;

    @Override
    public synchronized void lock() throws InterruptedException {
        while (isLocked && !isLockOwner()) {
            wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (!isLocked || !isLockOwner()) {
            throw new IllegalStateException("Thread is not lock owner");
        }
        isLocked = false;
        lockedBy = null;
        notify();
    }

    private boolean isLockOwner() {
        return lockedBy != null && Thread.currentThread().equals(lockedBy);
    }
}
