
package mx.com.oga.comercializadora.modelo;


public class Clientes {
    private int clienteId;
    private String  cedulaRuc;
    private String nombrecia;
    private String nombreContacto;
    private String direccionCliente;
    private String fax;
    private String email;
    private String celulara;
    private String fijo;

    public Clientes() {
    }

    public Clientes(int clienteId) {
        this.clienteId = clienteId;
    }
    
    public Clientes(int clienteId, String cedulaRuc, String nombrecia, String nombreContacto, String direccionCliente, String fax, String email, String celulara, String fijo) {
        this.clienteId = clienteId;
        this.cedulaRuc = cedulaRuc;
        this.nombrecia = nombrecia;
        this.nombreContacto = nombreContacto;
        this.direccionCliente = direccionCliente;
        this.fax = fax;
        this.email = email;
        this.celulara = celulara;
        this.fijo = fijo;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCedulaRuc() {
        return cedulaRuc;
    }

    public void setCedulaRuc(String cedulaRuc) {
        this.cedulaRuc = cedulaRuc;
    }

    public String getNombrecia() {
        return nombrecia;
    }

    public void setNombrecia(String nombrecia) {
        this.nombrecia = nombrecia;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelulara() {
        return celulara;
    }

    public void setCelulara(String celulara) {
        this.celulara = celulara;
    }

    public String getFijo() {
        return fijo;
    }

    public void setFijo(String fijo) {
        this.fijo = fijo;
    }

    @Override
    public String toString() {
        return "Clientes{" + "clienteId=" + clienteId + ", cedulaRuc=" + cedulaRuc + ", nombrecia=" + nombrecia + ", nombreContacto=" + nombreContacto + ", direccionCliente=" + direccionCliente + ", fax=" + fax + ", email=" + email + ", celulara=" + celulara + ", fijo=" + fijo + '}';
    }

    

  
}
