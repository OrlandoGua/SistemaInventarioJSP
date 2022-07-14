
package mx.com.oga.comercializadora.modelo;

import java.text.DecimalFormat;


public class DetalleOrdenes {
    
    private int detalleId;
    private Ordenes orden;    
    private Productos producto;
    private int cantidad;
    private double importe;
    private String importeRedondeado;
  

    public DetalleOrdenes() {
    }

    public DetalleOrdenes(int detalleId, Ordenes orden, Productos producto, int cantidad, double importe ) {
        this.detalleId = detalleId;
        this.orden = orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe=importe;
       
        
    }
    
    public int getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(int detalleId) {
        this.detalleId = detalleId;
    }

    public Ordenes getOrden() {
        return orden;
    }

    public void setOrden(Ordenes orden) {
        this.orden = orden;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getImporteRedondeado() {
        return new DecimalFormat("#.##").format(importe);
    }

    public void setImporteRedondeado(String imp) {
        this.importeRedondeado = imp;
    }

  
    
    
    
    
}
