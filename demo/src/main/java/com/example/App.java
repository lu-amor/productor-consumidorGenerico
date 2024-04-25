package com.example;

public class App extends Thread
{
    Buffer buffer = new Buffer(30);

    public void run() {
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
    }
    public static void main( String[] args )
    {
        App procesos = new App();
        procesos.start();
    }
}
