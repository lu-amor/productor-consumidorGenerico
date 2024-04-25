package com.example;

public class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

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


