package mx.com.oga.comercializadora.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection conn = null;

    public Conexion() {
        
      String urlDatabase = "jdbc:mysql://localhost:3306/pedidos?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        // String urlDatabase = "jdbc:postgresql://localhost:5432/pedidos";
       
        try {                
            Class.forName("com.mysql.cj.jdbc.Driver");
      //       Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase, "root", "20051990");

        } catch (SQLException ex) {
            System.out.println("Excepcion" + ex.getMessage());
        } catch (ClassNotFoundException ex) {

            System.out.println("Excencion no encontro driver = " + ex.getMessage());

        }
    }
   public Connection getConnection(){
   return this.conn;
   }
    
    public void desconectarBD() {
        System.out.println("Cerrar conexion a base de datos ");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("no se realizo la desconexion " + ex.getMessage());
            }
        }
    }

}
