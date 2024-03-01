package principal;

public class Productor extends Thread {

    private ArrayLimitado array;

    public Productor(ArrayLimitado array) {
        this.array = array;
    }

    /**
     * Función que genera cada 500 milisegundos un número del 1 al 6, al azar y
     * lo guarda en un array.
     */
    public void run() {
        while (true) {
            try {
                sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int num = (int) ((Math.random() * 6) + 1);
            array.guardar(num);
        }
    }
}
