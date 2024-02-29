package sockets.Ejercicio3_2;
import java.io.*;
import java.net.*;

public class FileServer {

    private final static int BUFSIZE = 1024;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1500);
            System.out.println("Server escuchando...");

            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado...");

                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

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
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
