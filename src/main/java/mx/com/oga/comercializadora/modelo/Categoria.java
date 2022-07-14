package mx.com.oga.comercializadora.modelo;

import java.util.List;

public class Categoria {

    private int categoriaid;
    private String nombreCat;
    private List<Productos> productos;
    
    

    public Categoria() {
          
    }

    public Categoria(int categoriaid) {
        this.categoriaid = categoriaid;
    }

    public Categoria(int categoriaid, String nombreCat) {
        this.categoriaid = categoriaid;
        this.nombreCat = nombreCat;
    }

    public int getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(int categoriaid) {
        this.categoriaid = categoriaid;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
    
    
    @Override
    public String toString() {
        return "Categoria{" + "categoriaid=" + categoriaid + ", nombreCat=" + nombreCat + '}';
    }

}
