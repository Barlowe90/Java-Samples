package PSP04;

import java.io.*;
import java.net.*;

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

            // 1. Iniciar sesión a través de un nombre de usuario y contraseña específico
            String user = in.readUTF();
            String password = in.readUTF();
            if (user.equals("arturo") && password.equals("secreta")) {
                out.writeUTF("OK");
            } else {
                out.writeUTF("Error credenciales");
                in.close();
                out.close();
                skCliente.close();
                return;
            }

            leerContenidoDir(out);
            leerContenidoFile(out);

            in.close();
            out.close();
            skCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void leerContenidoDir(DataOutputStream out) throws IOException {
        File dir = new File(".");
        File[] files = dir.listFiles();
        for (File file : files) {
            out.writeUTF(file.getName());
        }
        out.writeUTF("Fin leer directorio");
    }

    private void leerContenidoFile(DataOutputStream out) throws IOException, FileNotFoundException {
        File file = new File(".\\test.txt");
        if (file.exists()) {
            out.writeUTF("Leyendo archivo...");
            FileInputStream fileIn = new FileInputStream(file);
            byte[] buffer = new byte[BUFSIZE];
            int len;
            while ((len = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            fileIn.close();
        } else {
            out.writeUTF("Error leer archivo...");
        }
    }

}
