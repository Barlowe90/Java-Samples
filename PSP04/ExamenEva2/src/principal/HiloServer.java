package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServer extends Thread {

    private Socket skCliente = null;

    public HiloServer(Socket socket) {
        skCliente = socket;
    }

    public void run() {
        try {
            procesaPeticion(skCliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void procesaPeticion(Socket socketCliente) throws IOException {
        String peticion;
        String html;

        InputStreamReader entrada = new InputStreamReader(socketCliente.getInputStream());
        BufferedReader bufLeer = new BufferedReader(entrada);

        PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(), true);

        peticion = bufLeer.readLine();
        peticion = peticion.replaceAll(" ", "");

        if (peticion.startsWith("GET")) {
            peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

            // index
            if (peticion.length() == 0 || peticion.equals("/")) {
                html = Paginas.html_index;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Date: " + Mensajes.fecha);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            }

        }
    }

}
