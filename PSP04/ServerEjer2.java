package PSP04;

import java.io.*;
import java.net.*;

public class ServerEjer2 {

    private static final int PUERTO = 2000;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PUERTO);
            System.out.println("Server escuchando...");

            Socket skCliente = null;
            ServerHiloEjer2 hilo = null;

            while (true) {
                skCliente = server.accept();
                System.out.println("Cliente conectado...");

                hilo = new ServerHiloEjer2(skCliente);
                hilo.start();
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
