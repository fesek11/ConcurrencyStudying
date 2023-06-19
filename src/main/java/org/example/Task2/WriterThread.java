package org.example.Task2;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Arrays {


    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedWriter br = new BufferedWriter(new FileWriter("Text.txt"));
        int[] arr1 = {1, 2, 23, 4, 5, 21, 101, 2, 23, 4, 5, 21, 1, 2, 23, 4, 5, 21, 101, 2, 23, 4, 5, 21, 10};
        int[] arr2 = {2, 523, 101, 2, 23, 4, 5, 21, 47, 95, 721};
        int[] arr3 = {15, 52, 523, 47, 95, 721, 0, 15, 52, 523, 47, 95, 721, 1, 5, 52, 523, 47, 95, 721, 0};


        Thread t1 = new Thread(new WriterThread(arr1, br));
        Thread t2 = new Thread(new WriterThread(arr2, br));
        Thread t3 = new Thread(new WriterThread(arr3, br));
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        br.close();


    }

}


class WriterThread implements Runnable {
    private BufferedWriter bufferedWriter;
    private int[] arr;

    public WriterThread(int[] arr, BufferedWriter bufferedWriter) throws IOException {
        this.arr = arr;
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        try {
            bufferedWriter.write(Thread.currentThread() +" "+java.util.Arrays.toString(arr) + "\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
