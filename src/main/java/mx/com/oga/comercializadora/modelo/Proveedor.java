package mx.com.oga.comercializadora.modelo;

public class Proveedor {

    private int proveedorId;
    private String nombreProv;
    private String contacto;
    private String celuProv;
    private String fijoProv;

    public Proveedor() {
    }

    public Proveedor(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Proveedor(String nombreProv, String contacto, String celuProv, String fijoProv) {
        this.nombreProv = nombreProv;
        this.contacto = contacto;
        this.celuProv = celuProv;
        this.fijoProv = fijoProv;
    }

    public Proveedor(int proveedorId, String nombreProv, String contacto, String celuProv, String fijoProv) {
        this.proveedorId = proveedorId;
        this.nombreProv = nombreProv;
        this.contacto = contacto;
        this.celuProv = celuProv;
        this.fijoProv = fijoProv;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCeluProv() {
        return celuProv;
    }

    public void setCeluProv(String celuProv) {
        this.celuProv = celuProv;
    }

    public String getFijoProv() {
        return fijoProv;
    }

    public void setFijoProv(String fijoProv) {
        this.fijoProv = fijoProv;
    }

}
