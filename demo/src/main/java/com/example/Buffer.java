package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/* Clase buffer. */
public class Buffer {
    private double[] buffer;   // Array para guardar los elementos del buffer.
    private Semaphore mutex;   // Semáforo de mutua exclusión.
    private Semaphore empty;   // Semáforo para controlar si el buffer está vacío.
    private Semaphore full;    // Semáforo para controlar si el buffer está lleno.
    private int in;            // Índice de inserción.
    private int out;           // Índice de extracción.
    

    /* Constructor. */
    public Buffer(int size) {
        buffer = new double[size];
        mutex = new Semaphore(1);
        empty = new Semaphore(size);
        full = new Semaphore(0);
        in = 0;
        out = 0;
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
