package org.example.Task1;

import java.io.*;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class MyQueue {
    private final Queue<String> queue;
    private final Consumer[] consumer;

    public MyQueue(int consumerCount) {
        consumer = new Consumer[consumerCount];
        queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < consumerCount; i++) {
            consumer[i] = new Consumer(i);
            consumer[i].start();
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(2);
        Thread producerThread = new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                File file = new File("Text.txt");
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                while (true) {
                    System.out.print("Enter an item to add to array (or '00' to quit): ");
                    int input = scanner.nextInt();
                    if (input == 00) {
                        break;
                    }
                    bufferedWriter.write(input + " ");
                    myQueue.addItem(String.valueOf(input));
                }
                bufferedWriter.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        producerThread.start();

        try {
            producerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Producer thread completed.");

        ReaderC readerC = new ReaderC();
        readerC.start();



    }

    public void addItem(String obj) {
        queue.add(obj);
    }

    private class Consumer extends Thread {
        private final int id;

        public Consumer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                String item = queue.poll();
                if (item != null) {
                    System.out.println("Consumer " + id + " processed item: " + item);
                }
            }

        }


    }



}
 class ReaderC extends Thread {
    @Override
    public void run() {
        try {
            File file = new File("Text.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String lineRex = "\\w+";
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);
                // Создание объекта Pattern
                Pattern pattern = Pattern.compile(lineRex);

                // Создание объекта Matcher и привязка к входной строке
                Matcher matcher = pattern.matcher(line);

                int max = 0;
                int min = 0;
                while (matcher.find()) {
                    int next = Integer.parseInt(matcher.group());
                    if ( next > max) {
                        max = next;
                    }
                    if (next < min) {
                        min = next;
                    }

                }
                System.out.println(min + " MIN");
                System.out.println(max + " MAX");
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}