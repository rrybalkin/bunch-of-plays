package com.rrybalkin.concurrent;

public class ReentrantExclusiveLock implements Lock {
    private boolean isLocked;
    private Thread lockedBy;
    private int lockCount;

    @Override
    public synchronized void lock() throws InterruptedException {
        while (isLocked && !isLockOwner()) {
            wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        lockCount++;
    }

    @Override
    public synchronized void unlock() {
        if (!isLocked || !isLockOwner()) {
            throw new IllegalStateException("Thread is not lock owner");
        }
        lockCount--;
        if (lockCount == 0) {
            isLocked = false;
            lockedBy = null;
            notify();
        }
    }

    private boolean isLockOwner() {
        return lockedBy != null && Thread.currentThread().equals(lockedBy);
    }
}
