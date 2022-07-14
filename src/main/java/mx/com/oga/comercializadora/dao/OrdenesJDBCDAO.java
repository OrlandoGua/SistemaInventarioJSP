package mx.com.oga.comercializadora.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.oga.comercializadora.conexiones.Conexion;
import mx.com.oga.comercializadora.modelo.Clientes;
import mx.com.oga.comercializadora.modelo.DetalleOrdenes;
import mx.com.oga.comercializadora.modelo.Empleados;
import mx.com.oga.comercializadora.modelo.Ordenes;

public class OrdenesJDBCDAO implements IOrdenDao {

    private static final String SQL_INSER = "INSERT INTO ordenes ( clienteid, empleadoid, fechaorden, descuento, importe, numserie )"
            + " VALUES( ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Ordenes> listAll() {
        Ordenes orden = null;
        Empleados empleado = null;
        Clientes cliente = null;
        List<Ordenes> ordenes = new ArrayList<Ordenes>();

        Conexion con = new Conexion();

        IEmpleadoDao daoEmp = new EmpleadoJDBCDAO();
        IClienteDao daoClie = new ClienteJDBCDAO();

        try {

            String sql = "select * from ordenes";
            PreparedStatement ps = con.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ordenid");
                int idEmpleado = rs.getInt("empleadoid");
                int idCliente = rs.getInt("clienteid");
                java.sql.Date fecha = rs.getDate("fechaorden");
                int descuento = rs.getInt("descuento");
                BigDecimal importe = rs.getBigDecimal("importe");
                String serie = rs.getString("numserie");

                empleado = daoEmp.findById(idEmpleado);
                cliente = daoClie.findById(idCliente);

                if (empleado != null || cliente != null) {
                    orden = new Ordenes(id, empleado, cliente, fecha, descuento, importe.longValue(),serie);
                    ordenes.add(orden);
                }
            }

            con.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace();
            con.desconectarBD();
        }

        return ordenes;
    }

    @Override
    public Ordenes Insertat(Ordenes orden) {

        Conexion con = new Conexion();
        String mensaje = "";
        int idGenerado = 0;
        try {

            con.getConnection().setAutoCommit(false);

            //retormanos el id orden
            PreparedStatement stmt = con.getConnection().prepareStatement(SQL_INSER, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, (int) orden.getCliente().getClienteId());
            stmt.setInt(2, (int) orden.getEmpleado().getEmpleadoId());
            stmt.setDate(3, orden.getFechaOrden());
            stmt.setDouble(4, orden.getDescuento());
            stmt.setBigDecimal(5, new BigDecimal(orden.getImporte()));
            stmt.setString(6, orden.getNumeroSerie());

            stmt.executeUpdate();

            //retornamos el id
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            while (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
            }

            PreparedStatement stmt2;

            for (DetalleOrdenes detalle : orden.getDetalles()) {

                String sqlDetalle = "insert into detalle_ordenes (ordenid, productoid, cantidad, importe) values(?, ?, ?, ?)";

                stmt2 = con.getConnection().prepareCall(sqlDetalle);

                stmt2.setInt(1, idGenerado);
                stmt2.setInt(2, (int) detalle.getProducto().getProductoId());
                stmt2.setDouble(3, detalle.getCantidad());
                stmt2.setBigDecimal(4, new BigDecimal(detalle.getImporte()));
                

                stmt2.executeUpdate();

            }
            mensaje = "El pedido se ha creado correctamente";

            con.getConnection().commit();

            orden.setOrdenId(idGenerado);

        } catch (SQLException ex) {
            if (con.getConnection() != null) {
                try {
                    System.err.println("La transaccion no pudo realizarse");
                    con.getConnection().rollback();
                } catch (SQLException excep) {
                    System.err.println("no pudo hacerse el rollback de la transacion");
                }
            }
            ex.printStackTrace();
            mensaje = "No fue posible registrar producto" + ex.getMessage();
            con.desconectarBD();

        } finally {
            if (con.getConnection() != null) {
                con.desconectarBD();
            }
        }
        return orden;
    }

    @Override
    public Ordenes findById(int idOrden) {

        Ordenes orden = null;
        Empleados empleado = null;
        Clientes cliente = null;
        Conexion con = new Conexion();

        try {
            String sql = "select * from ordenes where ordenid=? LIMIT 1";
            PreparedStatement ps = con.getConnection().prepareStatement(sql);
            ps.setInt(1, idOrden);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ordenid");
                int idEmpleado = rs.getInt("empleadoid");
                int idCliente = rs.getInt("clienteid");
                java.sql.Date fecha = rs.getDate("fechaorden");
                int descuento = rs.getInt("descuento");
                BigDecimal importe = rs.getBigDecimal("importe");
                String serie= rs.getString("numserie");

                IEmpleadoDao daoEmp = new EmpleadoJDBCDAO();
                IClienteDao daoClie = new ClienteJDBCDAO();

                empleado = daoEmp.findById(idEmpleado);
                cliente = daoClie.findById(idCliente);

                orden = new Ordenes(idOrden, empleado, cliente, fecha, descuento, importe.doubleValue(), serie);
            }

            con.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace();
            con.desconectarBD();
        }

        return orden;
    }

    @Override
    public String update(Ordenes orden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(Ordenes orden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String GenerarSerie() {
        Conexion con = new Conexion();
        String sql = "select max(numserie) from ordenes";
        PreparedStatement ps;
        ResultSet rs;
        String numeroSeries="";
        try {
            ps = con.getConnection().prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
            numeroSeries=rs.getString(1);
            }
            
                    
        } catch (SQLException ex) {
            
        }
        return numeroSeries;
    }

}
