package server_echo;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorEcho {
    public static void main(String[] args) {
        try {
            // El servidor comienza a escuchar en el puerto 5555
            ServerSocket socketServidor = new ServerSocket(5555);

            while (true) {
                Socket socketCliente = socketServidor.accept();
                // Establece los flujos de comunicación con ese cliente
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                // COMIENZA LA COMUNICACION CON EL CLIENTE

                // Crea y lanza un hilo para atender a ese cliente
                // ConexionCliente conexionCliente = new ConexionCliente(socketCliente, salida,
                // entrada);
                // conexionCliente.start();
                // Envia algunos mensajes al cliente en cuanto este se conecta
                /*
                 * salida.println("Solo se repetir lo que me escribas");
                 * salida.println("Cuando escribas ’.’, se terminara la conexion");
                 */
                System.out.println("Solo se repetir lo que me escribas");
                System.out.println("Cuando escribas '.', se terminara la conexion");
                String linea = null;
                while ((linea = entrada.readLine()) != null) {
                    if (linea.equals(".")) {
                        socketCliente.close();
                        socketServidor.close();
                        break;
                    } else {
                        salida.println(linea);
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
