package org.example.Pool;

public class Main {
    public static void main(String[] args) {
        Object myObj = new Object();
        RunImpl ri = new RunImpl(myObj);
        Thread thread1 = new Thread(ri);
        Thread thread2 = new Thread(ri);

        thread1.start();
        thread2.start();
    }
}

class RunImpl implements Runnable {
    private Object object;

    public RunImpl(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println(object + " class obj");
        Object object2 = new Object();

        System.out.println(object2 + " local obj");

    }
}