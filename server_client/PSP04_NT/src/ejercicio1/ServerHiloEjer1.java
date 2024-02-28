package ejercicio1;

import java.io.*;
import java.net.*;
import java.util.Random;

public class ServerHiloEjer1 extends Thread {

    private Socket skCliente = null;
    private int contadorCliente;

    public ServerHiloEjer1(Socket socket, int contador) {
        skCliente = socket;
        contadorCliente = contador;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(skCliente.getInputStream());
            DataOutputStream out = new DataOutputStream(skCliente.getOutputStream());

            out.writeUTF("Cliente " + contadorCliente);

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
            skCliente.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
