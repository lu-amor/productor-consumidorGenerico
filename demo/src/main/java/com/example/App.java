package com.example;

/* Programa principal. */
public class App extends Thread
{
    /* Crea el buffer. */
    Buffer buffer = new Buffer(30);

    /* Indica la concurrencia de procesos. */
    public void run() {
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();
    }

    /* Inicializa el programa. */
    public static void main( String[] args )
    {
        App procesos = new App();
        procesos.start();
    }
}
