package mx.com.oga.comercializadora.dao;

import java.util.List;
import mx.com.oga.comercializadora.modelo.Empleados;

public interface IEmpleadoDao {

    public List<Empleados> listAll();

    public String Insertat(Empleados empleado);

    public Empleados findById(int idEmp);

    public String update(Empleados emp);

    public String delete(Empleados emp);

}
