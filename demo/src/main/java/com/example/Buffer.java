package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/* Clase buffer. */
public class Buffer {
    private double[] buffer;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;
    private int in;
    private int out;
    List<Integer> buff;

    /* Constructor. */
    public Buffer(int size) {
        buffer = new double[size];
        mutex = new Semaphore(1);
        empty = new Semaphore(size);
        full = new Semaphore(0);
        in = 0;
        out = 0;
        buff = new ArrayList<Integer>();
    }

    public int getLength() {
        return buff.size();
    }

    public List<Integer> get() {
        return buff;
    }
    public int getBy(int index) {
        return buff.get(index);
    }

    public void push(int value) {
        buff.add(value);
    }

    public void pop() {
        buff.remove(buff.size() - 1);
    }
    public void popBy(int index) {
        buff.remove(index);
    }
    public void popRandom() {
        if (!buff.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(buff.size());
            buff.remove(index);
        }
    }

    /* Mediante el uso de semáforos inserta un elemento en el buffer. */
    public void produce(double item) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        buffer[in] = item;
        in = (in + 1) % buffer.length;
        mutex.release();
        full.release();
    }

    /* Mediante el uso de semáforos quita un elemento del buffer y lo devuelve. */
    public double consume() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        double item = buffer[out];
        out = (out + 1) % buffer.length;
        mutex.release();
        empty.release();
        return item;
    }
}
