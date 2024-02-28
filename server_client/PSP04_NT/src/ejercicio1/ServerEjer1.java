package ejercicio1;

import java.io.*;
import java.net.*;

public class ServerEjer1 {

    private static final int MAX_CLIENTES_SIMULTANEOS = 10;
    private static final int PUERTO = 2000;

    public static void main(String[] args) {
        ServerSocket server = null;
        int cont = 0;
        try {
            server = new ServerSocket(PUERTO);
            System.out.println("Server escuchando...");

            Socket skCliente = null;
            ServerHiloEjer1 hilo = null;

            while (true && cont < MAX_CLIENTES_SIMULTANEOS) {
                skCliente = server.accept();
                System.out.println("Cliente conectado " + cont + " veces");
                cont++;
                hilo = new ServerHiloEjer1(skCliente, cont);
                hilo.start();

            }

            if (cont >= MAX_CLIENTES_SIMULTANEOS) {
                System.out.println("Se ha alcanzado el límite de clientes simultáneos");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
