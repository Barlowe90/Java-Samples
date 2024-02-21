package PSP04.ejemplo;

import java.io.*;
import java.net.*;

public class ServerHilo extends Thread {

    private Socket skCliente = null;
    private int contadorCliente;

    public ServerHilo(Socket socket, int contador) {
        skCliente = socket;
        contadorCliente = contador;
    }

    public void run() {
        try {
            // Creo los flujos de entrada y salida
            // DataInputStream flujo_entrada = new
            // DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // ATENDER PETICIÓN DEL CLIENTE
            flujo_salida.writeUTF("Hola cliente " + contadorCliente);

            // Se cierra la conexión
            skCliente.close();
            System.out.println("Cliente desconectado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
