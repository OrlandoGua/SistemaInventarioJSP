//
package mx.com.oga.comercializadora.modelo;

public class Productos {

    private int productoId;
    private Proveedor proveedor;
    private Categoria categoria;
    private String descripcion;
    private double precioUnit;
    private int existencia;

    public Productos() {
    }

    public Productos(int productoId) {
        this.productoId = productoId;
    }

    public Productos(int productoId, Proveedor proveedor, Categoria categoria, String descripcion, double precioUnit, int existencia) {
        this.productoId = productoId;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precioUnit = precioUnit;
        this.existencia = existencia;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

}
