package multi_process.Ejercicio1;

import java.net.*;
import java.io.*;

/**
 * Aplicacion que genera al menos 50 números aleatorios (entre 0 y 90), y que
 * los escribe en su salida estándar.
 *
 * @author adri
 */
public class Aleatorios {

    public static void main(String[] args) {
        ServerSocket conexion = null;
        Socket canal = null;
        PrintWriter streamSalida = null;
        try {
            conexion = new ServerSocket(12345);
        } catch (IOException ex) {
            System.err.println("No se ha podido abrir el puerto de escucha.");
            System.err.println(ex.toString());
        }
        if (conexion != null) {
            try {
                canal = conexion.accept();
                streamSalida = new PrintWriter(canal.getOutputStream());
                for (int i = 0; i < 50; i++) {
                    streamSalida.println((int) (Math.random() * 90) + 1);
                    System.out.println(i);
                    streamSalida.flush();
                }
            } catch (Exception ex) {
                System.err.println("No se ha podido establecer conexión, "
                        + "o no ha ocurrido un fallo al escribir en el canal.");
                System.err.print(ex.toString());
            } finally {

                if (streamSalida != null) {
                    streamSalida.close();
                }
                if (canal != null) {
                    try {
                        canal.close();
                    } catch (IOException ex) {
                        System.err.println("Error al cerrar el socket.");
                        System.err.print(ex.toString());
                    }
                    if (conexion != null) {
                        try {
                            conexion.close();
                        } catch (IOException ex) {
                            System.err.println("Error al cerrar ServerSocket.");
                            System.err.print(ex.toString());
                        }
                    }
                }
            }
        }
    }
}
