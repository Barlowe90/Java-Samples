package principal;

import java.util.Date;

public class Paginas {

    public static final String primeraCabecera = "Content-Type:text/html;charset=UTF-8";
    // contenido index
    public static final String html_index = "<html>"
            + "<head><title>Pagina principal</title></head>"
            + "<body>"
            + "<p>La fecha y hora de Adrian</p>"
            + new Date()
            + "</body>"
            + "</html>";
    // contenido noEncontrado
    public static final String html_noEncontrado = "<html>"
            + "<head><title>noEncontrado</title></head>"
            + "<body>"
            + "<h1>¡ERROR! Página no encontrada</h1>"
            + "<p>La página que solicitaste no existe en nuestro "
            + "servidor</p>"
            + "</body>"
            + "</html>";
}
