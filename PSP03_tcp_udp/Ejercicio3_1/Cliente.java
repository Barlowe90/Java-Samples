package sockets.Ejercicio3_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 2000);
            System.out.println("Connected to server...");

            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            int num;
            String respuesta;
            do {
                System.out.print("Introduce un numero: ");
                num = scanner.nextInt();
                out.writeInt(num);
                respuesta = in.readUTF();
                System.out.println(respuesta);
            } while (!respuesta.equals("Correcto"));

            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
