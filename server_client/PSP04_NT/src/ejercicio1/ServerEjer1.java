package ejercicio1;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class ServerEjer1 {

    private static final int MAX_CLIENTES_SIMULTANEOS = 10;
    private static final int PUERTO = 2000;
    private static Semaphore semaforo = new Semaphore(MAX_CLIENTES_SIMULTANEOS);

    public static void main(String[] args) {
        ServerSocket server = null;
        int cont = 0;
        try {
            server = new ServerSocket(PUERTO);
            System.out.println("Server escuchando...");

            Socket skCliente = null;
            ServerHiloEjer1 hilo = null;

            while (true) {
                semaforo.acquire(); // Adquirir un permiso del semáforo
                skCliente = server.accept();
                System.out.println("Cliente conectado " + cont + " veces");
                hilo = new ServerHiloEjer1(skCliente, cont);
                hilo.start();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
