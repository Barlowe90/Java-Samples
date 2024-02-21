package PSP05.server_http;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class ServidorHTTP {

    public static void main(String[] args) throws IOException, Exception {

        ServerSocket socServidor = new ServerSocket(8066);
        imprimeDisponible();
        Socket socCliente;
        HiloServer hilo = null;

        while (true) {
            socCliente = socServidor.accept();
            System.out.println("Atendiendo al cliente ");
            
            hilo = new HiloServer(socCliente);
            hilo.start();
            
            socCliente.close();
            System.out.println("cliente atendido");
        }
    }

    private static void imprimeDisponible() {

        System.out.println("El Servidor WEB se está ejecutando y permanece a la "
                + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
                + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
                + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
                + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
                + "localhost:8066/q\n para simular un error");
    }
}
