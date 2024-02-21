package PSP04;

import java.io.*;
import java.net.*;

/**
 * una vez iniciada sesión a través de un nombre de usuario y contraseña
 * específico (por ejemplo arturo / secreta) el sistema permita Ver el contenido
 * del directorio actual, mostrar el contenido de un determinado archivo y
 * salir.
 */

public class ServerHiloEjer2 extends Thread {

    private Socket skCliente = null;
    private final static int BUFSIZE = 1024;

    public ServerHiloEjer2(Socket socket) {
        skCliente = socket;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(skCliente.getInputStream());
            DataOutputStream out = new DataOutputStream(skCliente.getOutputStream());

            String filename = in.readUTF();
            File file = new File(filename);
            if (file.exists()) {
                out.writeUTF("Correcto");
                FileInputStream fileIn = new FileInputStream(file);
                byte[] buffer = new byte[BUFSIZE];
                int len;
                while ((len = fileIn.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                fileIn.close();
            } else {
                out.writeUTF("Error");
            }

            in.close();
            out.close();
            skCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
