package com.example;

import java.util.Random;

/* Ejemplo de clase productora. */
public class Productor extends Thread {
    Random random = new Random();
    Buffer buffer;

    int min = 1; // Define el rango mínimo
    int max = 100; // Define el rango máximo

    /* Constructor. */
    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    /* Método para generar números aleatorios entre 1 y 100. */
    public double generarNumeros() throws InterruptedException {
            double numeroAleatorio = random.nextDouble(max - min + 1) + min;
            return numeroAleatorio;
        }

    /* Genera números y los inserta en el buffer. */
    public void run() {
        try {
            while (true) {
                double numDouble = generarNumeros();
                int numInt = (int) Math.round(numDouble);
                buffer.produce(numInt);
            }
        }  catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }
}

