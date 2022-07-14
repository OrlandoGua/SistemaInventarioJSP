package mx.com.oga.comercializadora.modelo;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Ordenes {

    private int ordenId;
    private Empleados empleado;
    private Clientes cliente;
    private Date fechaOrden;
    private double descuento;
    private double importe;
    private String importeRedondo;
    private String numeroSerie;
    private List< DetalleOrdenes> detalles = new ArrayList();

    public Ordenes() {
    }

    public Ordenes(int ordenId, Empleados empleado, Clientes cliente, Date fechaOrden, double descuento, double importe, String numeroSerie) {
        this.ordenId = ordenId;
        this.empleado = empleado;
        this.cliente = cliente;
        this.fechaOrden = fechaOrden;
        this.descuento = descuento;
        this.importe = importe;
        this.numeroSerie = numeroSerie;
    }

    public int getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(int ordenId) {
        this.ordenId = ordenId;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getImporteRedondo() {
        return new DecimalFormat("#.##").format(importe);
    }

    public void setImporteRedondo(String importeRedondo) {
        this.importeRedondo = importeRedondo;
    }

    public List<DetalleOrdenes> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenes> detalles) {
        this.detalles = detalles;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

}
