package mx.com.oga.comercializadora.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.oga.comercializadora.conexiones.Conexion;
import mx.com.oga.comercializadora.modelo.Proveedor;

public class ProveedorJDBCDAO implements IProveedor {

    private static final String SQL_SELECT = "SELECT * FROM proveedores";
    private static final String SQL_INSERT = "INSERT INTO proveedores(proveedorid, nombreprov, contacto, celuprov, fijoprov )"
            + " VALUES( ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM proveedores WHERE proveedorid=? LIMIT 1";
    private static final String SQL_UPDATE = "UPDATE proveedores "
            + " SET  nombreprov=?, contacto=?, celuprov=?, fijoprov=? WHERE proveedorid=?";
    private static final String SQL_DELETE = "DELETE FROM proveedores WHERE proveedorid=?";

    @Override
    public List<Proveedor> listAll() {
        Conexion con = new Conexion();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proveedor proveedor = null;

        List<Proveedor> listProv = new ArrayList();

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int proid = rs.getInt("proveedorid");
                String nombre = rs.getString("nombreprov");
                String contacto = rs.getString("contacto");
                String celpro = rs.getString("celuprov");
                String fijpro = rs.getString("fijoprov");

                proveedor = new Proveedor(proid, nombre, contacto, celpro, fijpro);
                listProv.add(proveedor);
            }
            con.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            con.desconectarBD();
        }

        return listProv;
    }

    @Override
    public String Insertat(Proveedor proveedor) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        String mensaje = "";
        try {

            stmt = con.getConnection().prepareStatement(SQL_INSERT);

            stmt.setInt(1, proveedor.getProveedorId());
            stmt.setString(2, proveedor.getNombreProv());
            stmt.setString(3, proveedor.getContacto());
            stmt.setString(4, proveedor.getCeluProv());
            stmt.setString(5, proveedor.getFijoProv());
            stmt.executeUpdate();

            mensaje = "El proveedor se ha creado correctamente";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "No fue posible crear el proveedor: " + ex.getMessage();
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public Proveedor findById(int idProv) {

        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proveedor prov = null;

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idProv);
            rs = stmt.executeQuery();

            while (rs.next()) {
                prov = new Proveedor();
                prov.setProveedorId(rs.getInt("proveedorid"));
                prov.setNombreProv(rs.getString("nombreprov"));
                prov.setContacto(rs.getString("contacto"));
                prov.setCeluProv(rs.getString("celuprov"));
                prov.setFijoProv(rs.getString("fijoprov"));
            }

            con.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            con.desconectarBD();
        }
        return prov;
    }

    @Override
    public String update(Proveedor pro) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_UPDATE);

            stmt.setString(1, pro.getNombreProv());
            stmt.setString(2, pro.getContacto());
            stmt.setString(3, pro.getCeluProv());
            stmt.setString(4, pro.getFijoProv());
            stmt.setInt(5, pro.getProveedorId());

            stmt.executeUpdate();
            mensaje = "Se actualizo el proveedor";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "NO Se fue posible actualizar el proveedor";
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public String delete(Proveedor pro) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_DELETE);

            stmt.setInt(1,pro.getProveedorId() );
            stmt.executeUpdate();
            mensaje = "El proveedor se ha eliminado";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "El proveedor no se ha eliminado" + ex.getMessage();
            con.desconectarBD();
        }
        return mensaje;
    }

}
