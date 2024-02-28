package multi_process.comunicarprocesossockets;

import java.io.*;
import java.net.*;

public class SocketLector {
    public static void main(String[] args) {
        Socket canal = null; // Socket para establecer el canal de conexión con el escritor
        BufferedReader entrada = null; // Para el stream de lectura
        String valorEntrada = null; // Valores que iremos leyendo del canal

        try {
            canal = new Socket("localhost", 12345);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (canal != null) {
            try {
                entrada = new BufferedReader(new InputStreamReader(canal.getInputStream()));
                while ((valorEntrada = entrada.readLine()) != null) {
                    System.out.println(valorEntrada);
                    System.out.println("**");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (entrada != null) {
                    try {
                        entrada.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (canal != null) {
                    try {
                        canal.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
