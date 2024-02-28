package multi_process.monitores;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class Suministrador {

    public static void main(String[] args) {
        Suministrador suministrador = new Suministrador();

        String nombreFichero = "";
        File archivo = null;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;

        try {
            PrintStream ps = new PrintStream(
                    new BufferedOutputStream(new FileOutputStream(
                            new File("javalog_suministrador.txt"), true)),
                    true);
            System.setOut(ps);
            System.setErr(ps);
        } catch (Exception e) {
            System.err.println("Suministrador. No he podido redirigir salidas.");
        }

        nombreFichero = suministrador.getOsName(args);

        archivo = new File(nombreFichero);
        int i = 0;
        while (i < 10) {// escribiremos 10 datos
            try {
                raf = new RandomAccessFile(archivo, "rwd");
                // ***************
                // Sección crítica
                bloqueo = raf.getChannel().lock();

                System.out.println("Suministrador: ENTRA sección");
                if (raf.length() == 0) {
                    raf.writeInt(i);
                    System.out.println("Suministrador: valor escrito " + i);
                    i++;
                } else
                    System.out.println("Suministrador: no puede escribir.");
                System.out.println("Suministrador: SALE sección");
                bloqueo.release();
                bloqueo = null;
                // Fin sección crítica
                // *******************
                Thread.sleep(500);
            } catch (Exception e) {
                System.err.println("Suministrador. Error al acceder al fichero.");
                System.err.println(e.toString());
            } finally {
                try {
                    if (null != bloqueo)
                        bloqueo.release();
                    if (null != raf)
                        raf.close();
                } catch (Exception e2) {
                    System.err.println("Suministrador. Error al cerrar el fichero.");
                    System.err.println(e2.toString());
                    System.exit(1);
                }
            }
        }
    }

    private String getOsName(String[] args) {
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) {
            if (args.length > 0)
                return args[0].replace("\\", "\\\\");
            else {
                return "C:\\buffer.txt";
            }
        } else { // GNU/Linux
            if (args.length > 0)
                return args[0];
            else {
                return "/home/usuario/buffer.txt";
            }
        }
    }

}
