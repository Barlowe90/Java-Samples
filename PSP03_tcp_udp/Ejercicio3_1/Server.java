package sockets.Ejercicio3_1;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2000);
            System.out.println("Server escuchando...");

            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado...");

                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                Random random = new Random();
                int numeroSecreto = random.nextInt(101);
                System.out.println("Numero a adivinar: " + numeroSecreto);

                int num;
                do {
                    num = in.readInt();
                    if (num < numeroSecreto) {
                        out.writeUTF("El numero es mayor");
                    } else if (num > numeroSecreto) {
                        out.writeUTF("El numero es menor");
                    } else {
                        out.writeUTF("Correcto");
                    }
                } while (num != numeroSecreto);

                in.close();
                out.close();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
