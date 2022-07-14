
package mx.com.oga.comercializadora.dao;

import java.util.List;
import mx.com.oga.comercializadora.modelo.Proveedor;


public interface IProveedor {
    public List<Proveedor> listAll();

    public String Insertat(Proveedor proveedor);

    public Proveedor findById(int idProv);

    public String update(Proveedor pro);

    public String delete(Proveedor pro);
}
