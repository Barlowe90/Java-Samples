package PSP01.comunicarprocesossockets;

import java.io.PrintWriter;
import java.net.*;

public class SocketEscritor {
    public static void main(String[] args) {
        ServerSocket conexion = null; // Socket para aceptar conexiones
        Socket canal = null;
        PrintWriter streamSalida = null;

        try {
            conexion = new ServerSocket(12345);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (conexion != null) {
            try {
                canal = conexion.accept();
                streamSalida = new PrintWriter(canal.getOutputStream());

                for (int i = 0; i < 10; i++) {
                    streamSalida.println(i);
                    streamSalida.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (streamSalida != null) {
                    streamSalida.close();
                }
                if (canal != null) {
                    try {
                        canal.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
