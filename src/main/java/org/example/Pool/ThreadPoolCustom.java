package org.example.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPoolCustom {
    private List<ThreadPoolCustomRunnable> poolCustomRunnables = new ArrayList<>();
    private BlockingQueue blockingQueue;
    private boolean isStopped = false;


    public ThreadPoolCustom(int maxThreads, int maxTasks) {
        blockingQueue = new ArrayBlockingQueue<>(maxTasks);

        for (int i = 0; i < maxThreads; i++) {
            ThreadPoolCustomRunnable tp = new ThreadPoolCustomRunnable(blockingQueue);
            poolCustomRunnables.add(tp);
        }
        for (ThreadPoolCustomRunnable tp : poolCustomRunnables) {
            new Thread(tp).start();
        }
    }

    public synchronized void execute(Runnable task) throws Exception{
        if (this.isStopped) {
            throw new IllegalStateException("Thread is stopped");
        }
        blockingQueue.offer(task);
    }


    public synchronized void setStopped() {
        isStopped = true;
        for (ThreadPoolCustomRunnable tp : poolCustomRunnables) {
            tp.stop();
        }
    }

    public synchronized void waitUntilStopped() {
        while (this.blockingQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
