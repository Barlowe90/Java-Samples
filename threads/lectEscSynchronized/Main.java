package threads.lectEscSynchronized;

import java.util.concurrent.Semaphore;

class Buffer {
    private int data;
    private Semaphore semRead = new Semaphore(0); // Semáforo para controlar la lectura
    private Semaphore semWrite = new Semaphore(1); // Semáforo para controlar la escritura

    public void write(int newData) throws InterruptedException {
        semWrite.acquire(); // Adquirir el semáforo de escritura
        data = newData; // Escribir en el buffer
        semRead.release(); // Liberar el semáforo de lectura
    }

    public int read() throws InterruptedException {
        semRead.acquire(); // Adquirir el semáforo de lectura
        int readData = data; // Leer del buffer
        semWrite.release(); // Liberar el semáforo de escritura
        return readData;
    }
}

class Reader extends Thread {
    private Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                int data = buffer.read();
                System.out.println("Lector leyó: " + data);
                Thread.sleep(1000); // Simular procesamiento
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Writer extends Thread {
    private Buffer buffer;
    private int newData = 1;

    public Writer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                buffer.write(newData++);
                System.out.println("Escritor escribió: " + (newData - 1));
                Thread.sleep(2000); // Simular escritura
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Reader reader = new Reader(buffer);
        Writer writer = new Writer(buffer);
        reader.start();
        writer.start();
    }
}
