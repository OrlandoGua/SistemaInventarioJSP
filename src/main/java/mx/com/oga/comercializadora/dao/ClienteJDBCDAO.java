package mx.com.oga.comercializadora.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.oga.comercializadora.conexiones.Conexion;
import mx.com.oga.comercializadora.modelo.Clientes;

public class ClienteJDBCDAO implements IClienteDao {

    private static final String SQL_SELECT = "SELECT * FROM clientes ";
    private static final String SQL_INSERT = "INSERT INTO clientes (clienteid, cedula_ruc, nombrecia, nombrecontacto, direccioncli, fax, email, celular, fijo )"
            + " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM clientes WHERE clienteid=? LIMIT 1";
    private static final String SQL_UPDATE = "UPDATE clientes "
            + " SET cedula_ruc=?, nombrecia=?, nombrecontacto=?, direccioncli=?, fax=?, email=?, celular=?, fijo=? WHERE clienteid=?";
    private static final String SQL_DELETE = "DELETE FROM clientes WHERE clienteid =?";

    @Override
    public List<Clientes> listAll() {
        Conexion con = new Conexion();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes clientes = null;

        List<Clientes> listClientes = new ArrayList();

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            //       clienteid, cedula_ruc, nombrecia, nombrecontacto, direccioncli, fax, email, celular, fijo
            while (rs.next()) {
                int cliId = rs.getInt("clienteid");
                String ced = rs.getString("cedula_ruc");
                String nombrec = rs.getString("nombrecia");
                String nombre = rs.getString("nombrecontacto");
                String direc = rs.getString("direccioncli");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String celular = rs.getString("celular");
                String fijo = rs.getString("fijo");

                clientes = new Clientes(cliId, ced, nombrec, nombre, direc, fax, email, celular, fijo);
                listClientes.add(clientes);
            }
            con.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            con.desconectarBD();
        }

        return listClientes;
    }

    @Override
    public String Insertat(Clientes cliente) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        String mensaje = "";
        try {

            stmt = con.getConnection().prepareStatement(SQL_INSERT);

            stmt.setInt(1, cliente.getClienteId());
            stmt.setString(2, cliente.getCedulaRuc());
            stmt.setString(3, cliente.getNombrecia());
            stmt.setString(4, cliente.getNombreContacto());
            stmt.setString(5, cliente.getDireccionCliente());
            stmt.setString(6, cliente.getFax());
            stmt.setString(7, cliente.getEmail());
            stmt.setString(8, cliente.getCelulara());
            stmt.setString(9, cliente.getFijo());

            stmt.executeUpdate();

            mensaje = "El cliente se ha creado correctamente";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "No fue posible crear el cliente: " + ex.getMessage();
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public Clientes findById(int idCliente) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes clientes = null;

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                clientes = new Clientes();
                clientes.setClienteId(rs.getInt("clienteid"));
                clientes.setCedulaRuc(rs.getString("cedula_ruc"));
                clientes.setNombrecia(rs.getString("nombrecia"));
                clientes.setNombreContacto(rs.getString("nombrecontacto"));
                clientes.setDireccionCliente(rs.getString("direccioncli"));
                clientes.setFax(rs.getString("fax"));
                clientes.setEmail(rs.getString("email"));
                clientes.setCelulara(rs.getString("celular"));
                clientes.setFijo(rs.getString("fijo"));

            }

            con.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            con.desconectarBD();
        }
        return clientes;
    }

    @Override
    public String update(Clientes cliente) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_UPDATE);

            stmt.setString(1, cliente.getCedulaRuc());
            stmt.setString(2, cliente.getNombrecia());
            stmt.setString(3, cliente.getNombreContacto());
            stmt.setString(4, cliente.getDireccionCliente());
            stmt.setString(5, cliente.getFax());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getCelulara());
            stmt.setString(8, cliente.getFijo());
            stmt.setInt(9, cliente.getClienteId());

            stmt.executeUpdate();
            mensaje = "Se actualizo el cliente";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "Se fue posible actualizar el cliente";
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public String delete(Clientes clientes) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_DELETE);

            stmt.setInt(1, clientes.getClienteId());
            stmt.executeUpdate();
            mensaje = "El cliente se ha eliminado";
            con.desconectarBD();
        } catch (SQLException ex) {
           
            mensaje = "El cliente  no se ha eliminado" + ex.getMessage();
            con.desconectarBD();
        }
        return mensaje;
    }

}
