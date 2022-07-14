
package mx.com.oga.comercializadora.dao;

import java.util.List;
import mx.com.oga.comercializadora.modelo.Clientes;


public interface IClienteDao {
     public List<Clientes> listAll();

    public String Insertat(Clientes cliente);

    public Clientes findById(int idCliente);

    public String update(Clientes cliente);

    public String delete(Clientes clientes);

}
