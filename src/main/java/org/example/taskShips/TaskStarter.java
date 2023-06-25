package org.example.taskShips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

enum ShipsType {
    First, Second, Third
}

public class TaskStarter {
    public static void main(String[] args) {
        Generator generator = new Generator();
        Thread starter = new Thread(new Generator());
        Tunnel tunnel = new Tunnel(generator);
        starter.start();
        tunnel.run();
    }
}

class Ship {

    private int capacity;
    private ShipsType type;
    private Thread shipThread;

    public Ship(int capacity, ShipsType type) {
        this.shipThread = new Thread();
        this.capacity = capacity;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "capacity=" + capacity +
                ", type=" + type +
                ", shipThread=" + shipThread +
                '}';
    }
}

class Generator implements Runnable {
    private final List<Ship> list;
    private final int[] cap = {10, 30, 50};

    public Generator() {
        this.list = new ArrayList();
    }

    public Ship pollShip() {
        if (!list.isEmpty())
        return list.remove(list.size() + 1);
        try {
            synchronized (this) {
                wait(500);
            }
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < 10; i++) {
                    if (i == 9) {
                        System.out.println(list);
                    }
                    list.add(generate());
                    synchronized (this) {
                        wait(500);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private Ship generate() throws InterruptedException {

        Random r = new Random();
        int capacity = cap[r.nextInt(3)];
        return new Ship(capacity, Arrays.asList(ShipsType.values()).get(r.nextInt(3)));
    }
}

class Tunnel implements Runnable {
    private ArrayBlockingQueue<Ship> blockingQueue;
    private Generator generator;

    public Tunnel(Generator generator) {
        this.generator = generator;
        blockingQueue = new ArrayBlockingQueue(5);
    }


    @Override
    public void run() {
        while (true) {
            blockingQueue.add(generator.pollShip());
            synchronized (this) {
                try {
                    wait(200);
                    if (!blockingQueue.isEmpty()) {
                        System.out.println(blockingQueue);
                        blockingQueue.remove();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}