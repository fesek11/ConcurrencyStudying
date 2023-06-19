package org.example.Valotile;


public class VolatileImpl {

    private static volatile long count = 0;

    public boolean inc() {
        if (count == 10_000) {
            System.out.println("DONE!");
            return false;
        }
        count += 1;
        return true;
    }

    public static void getC() {
        System.out.println(count);
    }

}


class ThreadsProducer {
    public static void main(String[] args) throws InterruptedException {
        VolatileImpl v = new VolatileImpl();

        Thread t1 = new Thread(new RunnableImpl());
        Thread t2 = new Thread(new RunnableImpl());
        Thread t3 = new Thread(new RunnableImpl());

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(2000);

        VolatileImpl.getC();
    }
}

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        VolatileImpl v = new VolatileImpl();
        while (v.inc()) {
            v.inc();
            v.getC();
        }
    }
}

class ThreadL {
    ThreadLocal<String> tl = new ThreadLocal<>();

    public void setTl() {

    }

}