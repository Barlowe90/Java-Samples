package PSP02.prioridadesHilos;

public class Hilo extends Thread {

    public Hilo() {
    }

    public Hilo(int prioridad) {
        this.setPriority(prioridad);
    }

    @Override
    public void run() {

        String strCadena = "";

        for (int i = 0; i < 20000; ++i) {
            strCadena += "A";
            Thread.yield();
        }

        System.out.println("Hilo de prioridad " + this.getPriority()
                + " termina ahora");
    }
}
