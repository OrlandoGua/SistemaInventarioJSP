
package mx.com.oga.comercializadora.dao;

import java.util.List;
import mx.com.oga.comercializadora.modelo.Ordenes;


public interface IOrdenDao {
     public List<Ordenes> listAll();

    public Ordenes Insertat(Ordenes orden);

    public Ordenes findById(int idOrden);

    public String update(Ordenes orden);

    public String delete(Ordenes orden);
    
    public String GenerarSerie();
}
