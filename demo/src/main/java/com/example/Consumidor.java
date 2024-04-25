package com.example;

/* Ejemplo de clase consumidora. */
public class Consumidor extends Thread {
    private Buffer buffer;

    /* Constructor. */
    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    /* Toma números del buffer y los imprime por pantalla. */
    public void run() {
        try {
            while (true) {
                double item = buffer.consume();
                System.out.println(item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


