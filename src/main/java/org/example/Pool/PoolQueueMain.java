package org.example.Pool;

public class PoolQueueMain {
    public static void main(String[] args) throws Exception{
        ThreadPoolCustom tpcr = new ThreadPoolCustom(3, 10);

        for (int i = 0; i < 10; i++) {
            int taskNo = i;
            tpcr.execute(() -> {
                String msg = Thread.currentThread().getName() + ": Task " + taskNo;
                System.out.println(msg);

            });
        }
        tpcr.waitUntilStopped();
        tpcr.setStopped();
    }
}
