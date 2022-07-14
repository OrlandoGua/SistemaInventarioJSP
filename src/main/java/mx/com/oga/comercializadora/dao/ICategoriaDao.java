package mx.com.oga.comercializadora.dao;

import java.util.List;
import mx.com.oga.comercializadora.modelo.Categoria;

public interface ICategoriaDao {

    public List<Categoria> listAll();

    public String Insertat(Categoria categoria);

    public Categoria findById(int idCat);

    public String update(Categoria cat);

    public String delete(Categoria cat);
    
    public List<Categoria> buscar(String texto);
}
