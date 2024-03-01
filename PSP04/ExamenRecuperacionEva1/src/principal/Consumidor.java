package principal;

public class Consumidor extends Thread {

    private ArrayLimitado array;
    private String nombre;

    public Consumidor(ArrayLimitado array, String nombre) {
        this.array = array;
        this.nombre = nombre;
    }

    /**
     * Función que obitene del array un número, descansa un tiempo aleatorio
     * entre 1 y 300 milisegundos. Para cada número escribirá en pantalla el id
     * del hilo y el número.
     */
    public void run() {
        while (true) {
            int num = array.sacar();
            try {
                int tiempoDormir = (int) Math.random() * 300 + 1;
                sleep(tiempoDormir);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Soy el hilo: " + nombre + ". El num es: " + num);
        }
    }
}
