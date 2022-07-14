
package mx.com.oga.comercializadora.dao;

import java.util.List;
import mx.com.oga.comercializadora.modelo.Categoria;
import mx.com.oga.comercializadora.modelo.Productos;

public interface IProducto {
  public List<Productos> listAll();
  public String insert(Productos prod);
  public String update(Productos prod);
  public String delete(Productos prod);
  public Productos findById(int idProd);
  public List<Productos>getProductosByCategoria(Categoria cat);
  
  public int actualizarExistencia(int id, int existencia);
  
   
}
