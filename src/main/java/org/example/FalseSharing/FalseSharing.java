package org.example.FalseSharing;

public class FalseSharing {
    public static void main(String[] args) {
        Counter c = new Counter();
//        Counter c2 = new Counter();
        Counter c2 = c;
        long iterations = 1_000_000_00;

        Thread thread1 = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < iterations; i++) {
                c.firstC++;
            }
            long finish = System.currentTimeMillis();
            System.out.println("Time of T1: " + (finish-start));
        });
        Thread thread2 = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < iterations; i++) {
                c2.secondC++;
            }
            long finish = System.currentTimeMillis();
            System.out.println("Time of T2: " + (finish-start));
        });

        thread1.start();
        thread2.start();
    }
}
class Counter{
    public volatile long firstC = 0;
    public volatile long secondC = 0;
}
/*
 Time of T1: 42560
 Time of T2: 42617

 Time of T1: 42689
Time of T2: 42711

Time of T1: 6174
Time of T2: 6172

Time of T2: 42577
Time of T1: 42609

  * */