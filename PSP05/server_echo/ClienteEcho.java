package server_echo;

import java.net.*;
import java.io.*;

public class ClienteEcho {

    public static void main(String[] args) {
        // Nombre y puerto del servidor
        String hostname = "localhost";
        int puerto = 5555;
        try {
            Socket socket = new Socket(hostname, puerto);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Captura el teclado del usuario
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String cadena = null;
            String mensajeRepetido = "";
            // Envia lo que el usuario escribe por teclado al servidor y lee la respuesta
            while ((cadena = teclado.readLine()) != null) {
                salida.println(cadena);
                mensajeRepetido = entrada.readLine();
                System.out.println("Ese es el mensaje recibido del servidor: " + mensajeRepetido);
            }
        } catch (UnknownHostException uhe) {

        } catch (IOException ioe) {

        }

    }
}
