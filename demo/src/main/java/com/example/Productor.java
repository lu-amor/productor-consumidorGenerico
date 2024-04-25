package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Productor {
    Random random = new Random();
    Buffer buffer;

    int min = 1; // Define el rango mínimo
    int max = 100; // Define el rango máximo

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    public int generarNumeros() throws InterruptedException {
            int numeroAleatorio = random.nextInt(max - min + 1) + min;
            return numeroAleatorio;
        }

    public void run() {
        try {
            while (true) {
                int num1 = generarNumeros();
                buffer.produce(num1);
                System.out.println("Transferido: " + num1);
            }
        }  catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }
}

