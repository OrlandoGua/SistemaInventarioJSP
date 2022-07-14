package mx.com.oga.comercializadora.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.oga.comercializadora.conexiones.Conexion;
import mx.com.oga.comercializadora.modelo.Categoria;
import mx.com.oga.comercializadora.modelo.Productos;
import mx.com.oga.comercializadora.modelo.Proveedor;

public class ProductoJDBCDAO implements IProducto {

    private static final String SQL_SELECT = "SELECT * FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos(productoid, proveedorid, categoriaid, descripcion, preciounit, existencia )"
            + " VALUES( ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM productos WHERE productoid=? LIMIT 1";
    private static final String SQL_UPDATE = "UPDATE productos "
            + " SET proveedorid=?, categoriaid=?, descripcion=?, preciounit=?, existencia=? WHERE productoid =?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE productoid=?";

    private static final String SQL_SELECT_MUL = "select * from productos where categoriaid=?";

    @Override
    public List<Productos> listAll() {
        Conexion con = new Conexion();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = null;

        List<Productos> listProd = new ArrayList();

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int proid = rs.getInt("productoid");
                int proveId = rs.getInt("proveedorid");
                int idCat = rs.getInt("categoriaid");
                String des = rs.getString("descripcion");
                double pre = rs.getDouble("preciounit");
                int exi = rs.getInt("existencia");

                Categoria cat = new CategoriaJDBCDAO().findById(idCat);
                Proveedor pro = new ProveedorJDBCDAO().findById(proveId);

                producto = new Productos(proid, pro, cat, des, pre, exi);
                listProd.add(producto);
            }
            con.desconectarBD();

        } catch (SQLException ex) {
            System.out.println("Error en listALL de productos = " + ex.getMessage());
            con.desconectarBD();
        }

        return listProd;
    }

    @Override
    public String insert(Productos prod) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        String mensaje = "";
        try {

            stmt = con.getConnection().prepareStatement(SQL_INSERT);

            stmt.setInt(1, prod.getProductoId());
            stmt.setInt(2, prod.getProveedor().getProveedorId());
            stmt.setInt(3, prod.getCategoria().getCategoriaid());
            stmt.setString(4, prod.getDescripcion());
            stmt.setDouble(5, prod.getPrecioUnit());
            stmt.setDouble(6, prod.getExistencia());
            stmt.executeUpdate();

            mensaje = "El producto se ha creado correctamente";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "No fue posible crear el producto: " + ex.getMessage();
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public String update(Productos prod) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_UPDATE);

            stmt.setInt(1, prod.getProveedor().getProveedorId());
            stmt.setInt(2, prod.getCategoria().getCategoriaid());
            stmt.setString(3, prod.getDescripcion());
            stmt.setDouble(4, prod.getPrecioUnit());
            stmt.setDouble(5, prod.getExistencia());
            stmt.setInt(6, prod.getProductoId());

            stmt.executeUpdate();
            mensaje = "Se actualizo el producto";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "NO Se fue posible actualizar el producto";
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public String delete(Productos prod) {

        Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {

            stmt = con.getConnection().prepareStatement(SQL_DELETE);

            stmt.setInt(1, prod.getProductoId());
            stmt.executeUpdate();
            mensaje = "El producto se ha eliminado";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "El producto no se ha eliminado" + ex.getMessage();
            con.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public Productos findById(int idProd) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos productos = null;

        try {

            stmt = con.getConnection().prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idProd);
            rs = stmt.executeQuery();
//productoid, proveedorid, categoriaid, descripcion, preciounit, existencia
            while (rs.next()) {
                int proid = rs.getInt("productoid");
                int proveId = rs.getInt("proveedorid");
                int idCat = rs.getInt("categoriaid");
                String des = rs.getString("descripcion");
                double pre = rs.getDouble("preciounit");
                int exi = rs.getInt("existencia");

                Categoria cat = new CategoriaJDBCDAO().findById(idCat);
                Proveedor pro = new ProveedorJDBCDAO().findById(proveId);

                productos = new Productos(proid, pro, cat, des, pre, exi);

            }

            con.desconectarBD();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            con.desconectarBD();
        }
        return productos;
    }

    @Override
    public List<Productos> getProductosByCategoria(Categoria cat) {
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos productos = null;
        List<Productos> listaProductos = new ArrayList();

        try {
            stmt = con.getConnection().prepareStatement(SQL_SELECT_MUL);
            stmt.setInt(1, cat.getCategoriaid());
            rs = stmt.executeQuery();

            while (rs.next()) {

                int proid = rs.getInt("productoid");
                int proveId = rs.getInt("proveedorid");
                int idCat = rs.getInt("categoriaid");
                String des = rs.getString("descripcion");
                double pre = rs.getDouble("preciounit");
                int exi = rs.getInt("existencia");

                Categoria ca = new CategoriaJDBCDAO().findById(idCat);
                Proveedor pro = new ProveedorJDBCDAO().findById(proveId);

                productos = new Productos(proid, pro, ca, des, pre, exi);
                listaProductos.add(productos);
            }

        } catch (SQLException ex) {

        }
        return listaProductos;
    }

    @Override
    public int actualizarExistencia(int id, int existencia) {
        int r = 0;
        String sql = "update productos set existencia=?  where productoid=?";
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, existencia);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

}
