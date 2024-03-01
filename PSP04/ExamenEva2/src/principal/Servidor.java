package principal;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Semaphore;

public class Servidor {

    private static final int MAX_CLIENTES_SIMULTANEOS = 3;

    public static void main(String[] args) throws IOException, Exception {
        Semaphore semaforo = new Semaphore(MAX_CLIENTES_SIMULTANEOS);

        ServerSocket socServidor = new ServerSocket(8888);
        imprimeDisponible();
        Socket socCliente;
        HiloServer hilo = null;

        while (true) {
            try {
                semaforo.acquire();
            } catch (Exception e) {
                e.printStackTrace();
            }

            socCliente = socServidor.accept();
            System.out.println("Atendiendo al cliente ");

            hilo = new HiloServer(socCliente);
            hilo.start();

            semaforo.release();
            System.out.println("cliente atendido");
        }
    }

    private static void imprimeDisponible() {

        System.out.println("El Servidor WEB se está ejecutando y permanece a la "
                + "escucha por el puerto 8888.\nEscribe en la barra de direcciones "
                + "de tu explorador preferido:\n\nhttp://localhost:8888 para"
                + "solicitar la página del examen\n");
    }
}
