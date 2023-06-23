package org.example.Task4;

import java.util.Arrays;

public class SortingThreadStarter {
    public static void main(String[] args) {
        String[] a = {"cc", "d", "0", "a", "b","cc", "d", "0", "a", "b"};
        Thread t1 = new Thread(new BubbleSortThread(a));
        Thread t2 = new Thread(new InsertionSorting(a));
        Thread t3 = new Thread(new SelectionSort(a));

        t1.start();
        t2.start();
        t3.start();

    }
}

class SelectionSort implements Runnable {
    private final String[] arr;

    SelectionSort(String[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int min = i;

            for (int j = 1 + i; j < n; j++) {
                if (arr[j].compareTo(arr[i]) < 0) {
                    min = j;
                }
            }
            String temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;

        }
        System.out.println(Arrays.toString(arr) + Thread.currentThread());

    }
}

class InsertionSorting implements Runnable {
    private final String[] arr;

    public InsertionSorting(String[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            String el = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(el) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = el;

        }
        System.out.println(Arrays.toString(arr) + Thread.currentThread());

    }
}


class BubbleSortThread implements Runnable {

    private final String[] arr;

    public BubbleSortThread(String[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                String temp;
                String keyI = arr[j];
                String keyJ = arr[j + 1];
                if (keyI.compareTo(keyJ) > 0) {
                    temp = keyI;
                    arr[j] = keyJ;
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr)+ Thread.currentThread());

    }

}