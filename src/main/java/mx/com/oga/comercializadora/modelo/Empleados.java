package mx.com.oga.comercializadora.modelo;

import java.util.Date;

public class Empleados {

    private int empleadoId;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private int reporteA;
    private int Extencion;
    private String jefe;

    public Empleados() {
    }

    public Empleados(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Empleados(int empleadoId, String nombre, String apellido, Date fechaNacimiento, int reporteA, int Extencion) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.reporteA = reporteA;
        this.Extencion = Extencion;
       
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getReporteA() {
        return reporteA;
    }

    public void setReporteA(int reporteA) {
        this.reporteA = reporteA;
    }

    public int getExtencion() {
        return Extencion;
    }

    public void setExtencion(int Extencion) {
        this.Extencion = Extencion;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    @Override
    public String toString() {
        return "Empleados{" + "empleadoId=" + empleadoId + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", reporteA=" + reporteA + ", Extencion=" + Extencion + ", jefe=" + jefe + '}';
    }
 
}
