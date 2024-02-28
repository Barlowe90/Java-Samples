package PSP02.lectEscSynchronized;

public class Escritor extends Thread {

    private Semaforo semaforo;

    public Escritor(String nombre, Semaforo s) {
        this.setName(nombre);
        this.semaforo = s;
    }

    @Override
    public void run() {
        System.out.println(getName() + ": Intentando escribir");
        semaforo.accesoEscribir();

        try {
            sleep((int) (Math.random()) * 50);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        semaforo.escrituraFinalizada();
    }

}
