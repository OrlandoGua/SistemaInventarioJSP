package mx.com.oga.comercializadora.dao;


import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import mx.com.oga.comercializadora.conexiones.Conexion;
import mx.com.oga.comercializadora.modelo.Empleados;

public class EmpleadoJDBCDAO implements IEmpleadoDao {

    private static final String SQL_SELECT = " SELECT * FROM empleados ";

    private static final String SQL_INSERT = "INSERT INTO empleados(EMPLEADOID, NOMBRE, APELLIDO, FECHA_NAC, REPORTA_A, EXTENSION )"
            + " VALUES( ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE empleados"
            + " SET NOMBRE=?, APELLIDO=?, FECHA_NAC=?, REPORTA_A=?, EXTENSION=? WHERE EMPLEADOID=?";
   
     private static final String SQL_SELECT_BY = "SELECT *FROM empleados WHERE EMPLEADOID=? LIMIT 1 ";
           
     
    private static final String SQL_DELETE = "DELETE FROM empleados WHERE EMPLEADOID =?";

    @Override
    public List<Empleados> listAll() {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleados emp = null;

        List<Empleados> empleados = new ArrayList<>();

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("EMPLEADOID");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDO");
                Date fecha = rs.getDate("FECHA_NAC");
                int reportaA = rs.getInt("REPORTA_A");
                int extension = rs.getInt("EXTENSION");

                emp = new Empleados(id, nombre, apellido, fecha, reportaA, extension);
               
//                if (reportaA > 0) {                    
//                    Empleados jefe = findById(reportaA);
//                    emp.setJefe(jefe.getNombreCompleto());
//                }
                
                empleados.add(emp);
            }
            
            con.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("Error en la lista de empleado " + ex.getMessage());
            con.desconectarBD();
        }

        return empleados;
    }

    @Override
    public String Insertat(Empleados empleado) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        String mensaje = "";
        try {

            stmt = con.getConnection().prepareStatement(SQL_INSERT);

            stmt.setInt(1, empleado.getEmpleadoId());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getApellido());
            stmt.setDate(4, (Date) empleado.getFechaNacimiento());
            stmt.setInt(5, empleado.getReporteA());
            stmt.setInt(6, empleado.getExtencion());
            stmt.executeUpdate();
            mensaje = "El empleado se ha creado correctamente";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "No fue posible registrar este empleado " + ex.getMessage();
            con.desconectarBD();
        }

        return mensaje;
    }

    @Override
    public Empleados findById(int idEmp) {

        Conexion con = new Conexion();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleados emp = null;

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT_BY);
            stmt.setInt(1, idEmp);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("EMPLEADOID");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDO");
                Date fecha = rs.getDate("FECHA_NAC");
                int reportaA = rs.getInt("REPORTA_A");
                int extension = rs.getInt("EXTENSION");

                emp = new Empleados(id, nombre, apellido, fecha, reportaA, extension);

//                if (reportaA > 0 ) {                    
//                    Empleados jefe = findById(reportaA);
//                    emp.setJefe(jefe.getNombreCompleto());                    
//                }

            }
            con.desconectarBD();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            con.desconectarBD();
        }
        return emp;
    }

    @Override
    public String update(Empleados emp) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_UPDATE);

            stmt.setString(1, emp.getNombre());
            stmt.setString(2, emp.getApellido());
            stmt.setDate(3, (Date) emp.getFechaNacimiento());
            stmt.setInt(4, emp.getReporteA());
            stmt.setInt(5, emp.getExtencion());
            stmt.setLong(6, emp.getEmpleadoId());

            stmt.executeUpdate();
            mensaje = "Los datos del empleado se han actualizado correctamente";
            con.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "No fue posible actualizar los datos de este empleado con id";
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public String delete(Empleados emp) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_DELETE);

            stmt.setInt(1, emp.getEmpleadoId());
            stmt.executeUpdate();
            mensaje = "Se ha eliminado el registro de este empleado";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "No fue posible eliminar este empleado" + ex.getMessage();
            con.desconectarBD();
        }
        return mensaje;
    }

}
