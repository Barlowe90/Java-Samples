package threads.example_book;

public class AlmacenCuadros {
    private int cuadros = 0;

    public synchronized void guardar(){
        try {
            while (cuadros > 0){
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cuadros++;
        System.out.println("pintando cuadro: " + cuadros);
        this.notify();
    }

    public synchronized void sacar(){
        try {
            while (cuadros == 0){
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cuadros--;
        System.out.println("vendido cuadro: " + cuadros);
        this.notify();
    }
}
