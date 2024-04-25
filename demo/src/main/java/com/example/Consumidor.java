package com.example;

public class Consumidor {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                int item = buffer.consume();
                System.out.println(item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


