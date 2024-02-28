package multi_process.Ejercicio2;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

public class Lenguaje {

    public static void main(String[] args) {
        int num_conjuntos_letras = 0;
        String nombreFichero = "";
        File archivo = null;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;
        int valor = 0;

        if (args.length > 0) {
            num_conjuntos_letras = Integer.parseInt(args[0]);
            try {
                PrintStream ps = new PrintStream(
                        new BufferedOutputStream(new FileOutputStream(
                                new File("./javalog.txt"), true)), true);
                System.setOut(ps);
                System.setErr(ps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) {
            if (args.length > 1) {
                nombreFichero = args[1].replace("\\", "\\\\");
            } else {
                nombreFichero = "C:\\Users\\rique\\Documents\\GitHub\\DI\\PSP\\src\\PSP01\\Ejercicio2\\por_defecto.txt";
            }
        } else {
            if (args.length > 1) {
                nombreFichero = args[1];
            } else {
                nombreFichero = "/home/margye/por_defecto.txt";
            }
        }

        archivo = new File(nombreFichero);
        for (int i = 0; i < num_conjuntos_letras; i++)
         try {
            raf = new RandomAccessFile(archivo, "rw"); //Abrimos el fichero
            raf.seek(raf.length());
            //***************
            //Sección crítica
            bloqueo = raf.getChannel().lock();
            //bloqueamos el canal de acceso al fichero. Obtenemos el objeto que
            //representa el bloqueo para después poder liberarlo
            System.out.println("ENTRA sección");
            // Lectura del fichero
            String conjunto = generaConjunto();
            raf.writeBytes(conjunto + "\n");

            System.out.println("SALE sección");
            bloqueo.release(); //Liberamos el bloqueo del canal del fichero
            bloqueo = null;
            //Fin sección crítica
            //*******************
        } catch (Exception e) {
            System.err.println(" Error al acceder al fichero");
            System.err.println(e.toString());
        } finally {
            try {
                if (null != raf) {
                    raf.close();
                }
                if (null != bloqueo) {
                    bloqueo.release();
                }
            } catch (Exception e2) {
                System.err.println(" Error al cerrar el fichero");
                System.err.println(e2.toString());
                System.exit(1);
            }
        }
    }

    private static String generaConjunto() {
        String conjunto = "";
        int n = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < n; i++) {
            char c = (char) (Math.random() * 26 + 'a');
            conjunto += c;
        }
        return conjunto;
    }
}
