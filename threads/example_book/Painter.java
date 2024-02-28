package threads.example_book;

public class Painter extends Thread{
    private AlmacenCuadros almacen;

    public Painter(AlmacenCuadros a){
        this.almacen = a;
    }

    public void run(){
        for (int i = 0; i < 30; i++) {
            almacen.guardar();
        }
    }
}
