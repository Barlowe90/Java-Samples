package PSP04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClienteEjer2 {
    private static final String HOST = "127.0.0.1";
    private static final int PUERTO = 2000;

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket(HOST, PUERTO);
            System.out.println("Cliente conectado...");

            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            out.writeUTF("arturo");
            out.writeUTF("secreta");

            DataInputStream in = new DataInputStream(cliente.getInputStream());
            String respuesta = in.readUTF();
            if (respuesta.equals("OK")) {
                System.out.println("Usuario y contraseña correctos");
                System.out.println("Contenido del directorio:");
                while (in.available() > 0) {
                    System.out.println(in.readUTF());
                }
                System.out.println("Contenido del archivo:");
                while (in.available() > 0) {
                    System.out.print((char) in.read());
                }
            } else {
                System.out.println("Usuario y/o contraseña incorrectos");
            }
            in.close();
            out.close();
            cliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
