package org.example.Pool;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolCustomRunnable implements Runnable {
    private boolean isStopped = false;
    private BlockingQueue blockingTaskQueue = null;
    private Thread thread = null;

    public ThreadPoolCustomRunnable(BlockingQueue bq) {
        this.blockingTaskQueue = bq;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped()) {
            try {
                System.out.println(thread.getName() + " is running");
                Runnable runnable = (Runnable) blockingTaskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void stop() {
        isStopped = true;
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return this.isStopped;
    }
}
