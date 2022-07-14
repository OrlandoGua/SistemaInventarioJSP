
package mx.com.oga.comercializadora.dao;

import java.util.List;
import mx.com.oga.comercializadora.modelo.DetalleOrdenes;
import mx.com.oga.comercializadora.modelo.Ordenes;


public interface IDetalleOrdenDao {
   public List<DetalleOrdenes>getDetalles(Ordenes orde); 
}
