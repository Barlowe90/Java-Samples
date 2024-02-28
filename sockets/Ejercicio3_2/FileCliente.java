package sockets.Ejercicio3_2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileCliente {

    private final static int BUFSIZE = 1024;

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 1500);
            System.out.println("Connected to server...");

            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            String filename;
            String respuesta;
            do {
                System.out.print("Introduce el nombre del fichero: ");
                filename = scanner.nextLine();
                out.writeUTF(filename);
                respuesta = in.readUTF();
                if (respuesta.equals("Correcto")) {
                    byte[] buffer = new byte[BUFSIZE];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        System.out.write(buffer, 0, len);
                    }
                } else {
                    System.out.println("Error: el fichero no existe");
                }
            } while (!respuesta.equals("Correcto"));

            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}