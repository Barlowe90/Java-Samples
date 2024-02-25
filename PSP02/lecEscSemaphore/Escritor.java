package PSP02.lecEscSemaphore;

import java.util.concurrent.Semaphore;

public class Escritor extends Thread {

    private Semaphore semaforo;

    public Escritor(String nombre, Semaphore s) {
        super(nombre);
        this.semaforo = s;
    }

    @Override
    public void run() {
        System.out.println(getName() + " intentando escribir");

        try {
            semaforo.acquire(5);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(getName() + ": Escribirndo");

        try {
            sleep((int) (Math.random() + 50));
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        semaforo.release(5);

        System.out.println(getName() + ": Ya he escrito");
    }
}
