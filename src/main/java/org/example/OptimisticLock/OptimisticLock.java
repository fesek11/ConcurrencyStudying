package org.example.OptimisticLock;

import java.util.concurrent.atomic.AtomicLong;

class Run {
    public static void main(String[] args) {
        OptimisticLock optimisticLock = new OptimisticLock();
        long iterations = 100;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                optimisticLock.inc();
            }
            optimisticLock.read();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                optimisticLock.inc();
            }
            optimisticLock.read();
        });
        t1.start();
        t2.start();
    }
}

public class OptimisticLock {
    private final AtomicLong count = new AtomicLong(0);

    public void inc() {
        boolean changed = false;
        while (!changed) {
            System.out.println("Before " + count.get());
            long old = count.get();
            changed = count.compareAndSet(old, old + 1);
        }
    }

    public void read() {
        count.get();
    }
}

class SynchronizedCounter {
    long count = 0;

    public void inc() {
        synchronized (this) {
            count++;
        }
    }

    public long count() {
        synchronized (this) {
            return this.count;
        }
    }
}