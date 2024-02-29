package PSP06;

import java.io.*;
import java.util.*;
import java.util.Random;

public class EjemploSinExcepciones {
    private ArrayList<Integer> numeros;
    private static final int SIZE = 100;

    public EjemploSinExcepciones() {
        numeros = new ArrayList<>(SIZE);
        Random randomGenerator = new Random();

        for (int i = 0; i < SIZE; i++) {
            Integer num = randomGenerator.nextInt(100);
            numeros.add(num);
        }

        PrintWriter out = null;
        try {
            System.out.println("Entrando a la zona Try");
            out = new PrintWriter(new FileWriter("Salida.txt"));
            for (int i = 0; i < SIZE; i++)
                out.println("Valor de: " + i + " = " + numeros.get(i));

            out.close();
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new EjemploSinExcepciones();
    }

}
