package PSP02.crearHilos;

public class Hilo_Thread extends Thread {

    String nombre = "Hilo_derviaThread";

    public Hilo_Thread(String nb) {
        nombre = nb;
    }

    public Hilo_Thread() {
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre);
        }
    }
}
