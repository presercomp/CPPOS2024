package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/pos";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection conn = null;
    
    public static Connection getConnection() {
        if(conn == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
            } catch(ClassNotFoundException x_X){
                System.out.println("Clase no encontrada");
            } catch(SQLException X_x){
                System.out.println("Error al conectar la base de datos");
                System.out.println(X_x.getMessage());
            }
        }
        return conn;
    }
            
}
