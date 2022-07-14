package mx.com.oga.comercializadora.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.oga.comercializadora.conexiones.Conexion;
import mx.com.oga.comercializadora.modelo.DetalleOrdenes;
import mx.com.oga.comercializadora.modelo.Ordenes;
import mx.com.oga.comercializadora.modelo.Productos;

public class DetalleOrdenesJDBCDAO implements IDetalleOrdenDao {

    @Override
    public List<DetalleOrdenes> getDetalles(Ordenes orde) {
        DetalleOrdenes detalle;
        List<DetalleOrdenes> detalles = new ArrayList<>();

        ProductoJDBCDAO daoProducto = new ProductoJDBCDAO();

        Conexion con = new Conexion();

        try {
            String sql = "select * from detalle_ordenes where ordenid= ?";
            PreparedStatement ps = con.getConnection().prepareStatement(sql);
            ps.setLong(1, orde.getOrdenId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
//            int ordenId = rs.getInt("ordenid");
                int detalleId = rs.getInt("detalleid");
                int productoId = rs.getInt("productoid");
                int cantidad = rs.getInt("cantidad");
                double importe = rs.getDouble("importe");
                

                Productos prod = daoProducto.findById(productoId);

                detalle = new DetalleOrdenes(detalleId, orde, prod, cantidad, importe);
                detalles.add(detalle);
            }

            con.desconectarBD();

        } catch (SQLException ex) {
            System.out.println("Error en listAll de detalles: "
                    + ex.getMessage());
            con.desconectarBD();
        }

        return detalles;
    }

}
