package org.example.Task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ArraysReader {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new ReaderR("Text2.txt"));
        Thread thread1 = new Thread(new ReaderR("Text1.txt"));
        Thread thread2 = new Thread(new ReaderR("Text3.txt"));

        thread.start();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        thread.join();


    }

    private static class ReaderR implements Runnable {
        private final String fileName;
        private int[] arr;

        public ReaderR(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                String line = bufferedReader.readLine();
                for (int i = 0; i <= Integer.parseInt(line); i++) {
                    if (i == 0) {
                        arr = new int[Integer.parseInt(line)];
                    } else {
                        arr[i-1] = Integer.parseInt(bufferedReader.readLine());
                    }
                }
                bufferedReader.close();
                System.out.println(Arrays.toString(arr));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
