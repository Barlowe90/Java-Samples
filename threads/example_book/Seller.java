package threads.example_book;

public class Seller extends Thread{
    private AlmacenCuadros almacen;

    public Seller(AlmacenCuadros a){
        this.almacen = a;
    }

    public void run(){
        for (int i = 0; i < 30; i++) {
            almacen.sacar();
        }
    }
}
