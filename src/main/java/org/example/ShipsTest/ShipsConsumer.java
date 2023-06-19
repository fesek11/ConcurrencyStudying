package org.example.ShipsTest;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class Run {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        Thread pr = new Thread(new Producer(queue));
        Thread co = new Thread(new Consumer(queue));

        pr.start();
        co.start();

    }
}


class MyQueue<T> {
    boolean valueSet = false;
    private Queue<T> n = new ConcurrentLinkedQueue<>();


    public synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Пoлyчeнo: " + n + "от" + Thread.currentThread().getName());
        valueSet = false;
        notify();
        return n.size();
    }

    public synchronized void put(T n) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        valueSet = true;
        this.n.add(n);
        System.out.println("Oтпpaвлeнo: " + n + Thread.currentThread().getName());
        notify();
    }
}

class Producer implements Runnable {
    private MyQueue myQueue;

    public Producer(MyQueue myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            myQueue.put(i);
            i++;
        }
    }
}

class Consumer implements Runnable {
    private MyQueue myQueue;

    public Consumer(MyQueue myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        while (true) {
            myQueue.get();
        }
    }
}