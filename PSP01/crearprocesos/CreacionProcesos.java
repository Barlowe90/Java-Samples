package PSP01.crearprocesos;

import java.io.IOException;

/**
 * Escribe un programa de ejemplo de creación de procesos en Java.
 */

public class CreacionProcesos {

    public static void main(String[] args) {
        try {
            Process nuevoProceso = null;
            String osName = System.getProperty("os.name");

            if (osName.equals("Linux")) {
                System.out.println("Sistema operativo Linux");
                nuevoProceso = Runtime.getRuntime().exec("ls -l");
            } else if (osName.equals("Windows")) {
                System.out.println("Sistema operativo Windows");
                nuevoProceso = Runtime.getRuntime().exec("cmd /c dir");
            } else {
                System.out.println("Sistema operativo no soportado");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}