package PSP02.lectEscSynchronized;

public class MainLecEscSemaforo {

    public static void main(String args[]) {
        Semaforo smfro = new Semaforo();

        for (int i = 1; i <= 5; i++) {
            new Lector("Lector" + i, smfro).start();
        }

        for (int i = 1; i <= 2; i++) {
            new Escritor("Escritor" + i, smfro).start();
        }
    }

}