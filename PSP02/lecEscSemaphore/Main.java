package PSP02.lecEscSemaphore;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore sema = new Semaphore(5);

        for (int i = 1; i <= 2; i++) {
            new Escritor("Escritor " + i, sema).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Lector("Lector " + i, sema).start();
        }
    }

}
