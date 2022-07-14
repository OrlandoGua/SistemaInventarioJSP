package mx.com.oga.comercializadora.dao;

import java.sql.*;
import java.util.*;

import mx.com.oga.comercializadora.conexiones.Conexion;
import mx.com.oga.comercializadora.conexiones.Conexiones;
import mx.com.oga.comercializadora.modelo.Categoria;

public class CategoriaJDBCDAO implements ICategoriaDao {

    private static final String SQL_SELECT = "SELECT * FROM categorias";
    private static final String SQL_INSERT = "INSERT INTO categorias(CATEGORIAID, NOMBRECAT )"
            + " VALUES( ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM categorias WHERE CATEGORIAID=? LIMIT 1";
    private static final String SQL_UPDATE = "UPDATE categorias"
            + " SET NOMBRECAT=? WHERE CATEGORIAID=?";
    private static final String SQL_DELETE = "DELETE FROM categorias WHERE CATEGORIAID =?";
    
   

    @Override
    public List<Categoria> listAll() {
          
       Conexion con = new Conexion();
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Categoria categoria = null;

        List<Categoria> categorias = new ArrayList();

        try {
        
            stmt = con.getConnection().prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int categoriaid = rs.getInt("CATEGORIAID");
                String nombre = rs.getString("NOMBRECAT");

                categoria = new Categoria(categoriaid, nombre);
                categorias.add(categoria);
            }
          con.desconectarBD();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
           con.desconectarBD();
        } 

        return categorias;
    }

    @Override
    public String Insertat(Categoria categoria) {
       Conexion con = new Conexion();
        PreparedStatement stmt = null;
        String mensaje = "";
        try {
           
            stmt = con.getConnection().prepareStatement(SQL_INSERT);

            stmt.setInt(1, categoria.getCategoriaid());
            stmt.setString(2, categoria.getNombreCat());
            stmt.executeUpdate();

            mensaje = "La categoria se ha creado correctamente";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje = "no fue posible crear la categoria: " + ex.getMessage();
            con.desconectarBD();
        } 
        return mensaje;

    }

    @Override
    public Categoria findById(int idCat ) {
        
        Conexion con = new Conexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Categoria cat =null;

        try {
            
            stmt = con.getConnection().prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idCat);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            cat = new Categoria();
            cat.setCategoriaid(rs.getInt("CATEGORIAID"));
            cat.setNombreCat(rs.getString("NOMBRECAT"));
            }  
            
            con.desconectarBD();
           
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            con.desconectarBD();
        } 
       return cat;
    }

    @Override
    public String update(Categoria cat) {
        Conexion con = new Conexion();
         PreparedStatement stmt = null;

        String mensaje = "";

        try {
            
            stmt = con.getConnection().prepareStatement(SQL_UPDATE);

            stmt.setString(1, cat.getNombreCat());
           
            stmt.setInt(2, cat.getCategoriaid());

            stmt.executeUpdate();
            mensaje ="Se actualizo la categoria";
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje ="Se fue posible actualizar la categoria";
            con.desconectarBD();
        } 
        return mensaje;
    }

    @Override
    public String delete(Categoria cat) {
       Conexion con = new Conexion();
        PreparedStatement stmt = null;

        String mensaje = "";

        try {
            
            stmt = con.getConnection().prepareStatement(SQL_DELETE);

            stmt.setDouble(1, cat.getCategoriaid());
            stmt.executeUpdate();
            mensaje ="La categoria se ha eliminado"; 
            con.desconectarBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mensaje ="La categoria no se ha eliminado"+ex.getMessage();
            con.desconectarBD();
        } 
        return mensaje;
    }

    @Override
    public List<Categoria>buscar(String texto) {
        Conexion con = new Conexion();
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Categoria> listCate = new ArrayList();
         Categoria categoria = null;
        String sql ="SELECT * FROM categorias where nombrecat  ILIKE ? ";
   
        try {
            
            stmt = con.getConnection().prepareStatement(sql); 
            stmt.setString(1, "%"+texto+"%");
           
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                categoria = new Categoria();
                categoria.setCategoriaid(rs.getInt("categoriaid"));
                categoria.setNombreCat(rs.getString("nombrecat"));               
               
                listCate.add(categoria);
            }
           
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            con.desconectarBD();
        } finally{
         con.desconectarBD();
        }
        return listCate;

    }

}
