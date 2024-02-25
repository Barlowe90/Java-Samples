package PSP02.lectEscSynchronized;

public class Semaforo {
    private final static int LIBRE = 0;
    private final static int CON_LECTORES = 1;
    private final static int CON_ESCRITOR = 2;
    private int estado = LIBRE;
    private int tLectores = 0;

    public synchronized void accesoLeer() {
        String nombre = Thread.currentThread().getName();

        if (estado == LIBRE) {
            System.out.println("leyendo " + nombre);
            estado = CON_LECTORES;
        } else if (estado != CON_LECTORES) {
            while (estado == CON_ESCRITOR) {
                try {
                    System.out.println("esperando " + nombre);
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
            estado = CON_LECTORES;
        }
        tLectores++;
    }

    public synchronized void accesoEscribir() {
        String nombre = Thread.currentThread().getName();

        if (estado == LIBRE) {
            System.out.println("escribiendo " + nombre);
            estado = CON_ESCRITOR;
        } else {
            while (estado != LIBRE) {
                try {
                    System.out.println("esperando " + nombre);
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
            estado = CON_ESCRITOR;
        }
    }

    public synchronized void lecturaFinalizada() {
        String nombre = Thread.currentThread().getName();
        System.out.println("leyendo finalizado " + nombre);

        tLectores--;
        if (tLectores == 0) {
            estado = LIBRE;
            notify();
        }
    }

    public synchronized void escrituraFinalizada() {
        String nombre = Thread.currentThread().getName();
        System.out.println("escribiendo finalizado " + nombre);

        estado = LIBRE;
        notify();
    }
}
