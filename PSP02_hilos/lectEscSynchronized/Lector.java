package threads.lectEscSynchronized;

public class Lector extends Thread {

    private Semaforo semaforo;

    public Lector(String nombre, Semaforo s) {
        this.setName(nombre);
        this.semaforo = s;
    }

    @Override
    public void run() {
        System.out.println(getName() + ": Intentando leer");
        semaforo.accesoLeer();

        try {
            sleep((int) (Math.random()) * 50);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        semaforo.lecturaFinalizada();
    }

}
