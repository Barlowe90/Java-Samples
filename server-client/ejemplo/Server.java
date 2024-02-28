package PSP04.ejemplo;

// import java.io.*;
import java.net.*;

public class Server {

    static final int PUERTO = 2000;

    public Server() {

        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            Socket scliente = null;
            // OutputStream aux = null;
            // DataOutputStream flujo_salida = null;
            ServerHilo hilo = null;
            int i = 0;
            while (true) {
                scliente = servidor.accept();
                // System.out.println("Sirvo al cliente "+ PUERTO);
                // aux = scliente.getOutputStream();
                // flujo_salida = new DataOutputStream (aux);
                // flujo_salida.writeUTF("Hola cliente "+ i);
                i++;
                hilo = new ServerHilo(scliente, i);
                hilo.start();
                // scliente.close();
            }
            // System.out.println("Ya se han creado los clientes");

        } catch (Exception ex) {
            System.out.println("Excepcion " + ex.getStackTrace());
        }
    }

    public static void main(String[] arg) {
        new Server();

    }
}
