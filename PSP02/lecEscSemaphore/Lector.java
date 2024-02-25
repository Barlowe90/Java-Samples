package PSP02.lecEscSemaphore;

import java.util.concurrent.Semaphore;

public class Lector extends Thread {

    private Semaphore semaforo;

    public Lector(String nombre, Semaphore s) {
        super(nombre);
        this.semaforo = s;
    }

    public void run() {
        System.out.println(getName() + " : Intentando leer");

        try {
            semaforo.acquire();

        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(getName() + " : Leyendo");
        try {
            sleep((int) (Math.random() * 50));

        } catch (InterruptedException e) {
            System.out.println(e);
        }
        semaforo.release();

        System.out.println(getName() + " : Ya he leido");
    }
}
