package PSP05.server_http;

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

        InputStreamReader entrada = new InputStreamReader(
                socketCliente.getInputStream());
        BufferedReader bufLeer = new BufferedReader(entrada);

        // objeto de java.io que entre otras características, permite escribir
        // 'línea a línea' en un flujo de salida
        PrintWriter printWriter = new PrintWriter(
                socketCliente.getOutputStream(), true);

        // mensaje petición cliente
        peticion = bufLeer.readLine();

        // para compactar la petición y facilitar así su análisis, suprimimos todos
        // los espacios en blanco que contenga
        peticion = peticion.replaceAll(" ", "");

        // si realmente se trata de una petición 'GET' (que es la única que vamos a
        // implementar en nuestro Servidor)
        if (peticion.startsWith("GET")) {
            // extrae la subcadena entre 'GET' y 'HTTP/1.1'
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
            } else if (peticion.equals("/quijote")) {
                html = Paginas.html_quijote;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Date: " + Mensajes.fecha);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            } else {
                html = Paginas.html_noEncontrado;
                printWriter.println(Mensajes.lineaInicial_NotFound);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Date: " + Mensajes.fecha);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            }

        }
    }

}
