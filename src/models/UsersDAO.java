package models;
import controllers.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsersDAO {
    
    public boolean create(Users u){
        //Creamos la consulta de insercion con preparedStatements
        //para evitar posibles injecciones de SQL
        String sql = "INSERT INTO users (nickname, secret) VALUES (?, ?)";
        try {
            //Nos conectamos a la base de datos
            Connection conn = DB.getConnection();
            //Procesamos la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            //Definimos los valores para los dos parametros con ? en la consulta
            stmt.setString(1, u.getNickname());
            stmt.setString(2, u.getSecret());
            //Ejecutamos la consulta y obtenemos los resultados
            int results = stmt.executeUpdate();
            //Si el resultado es mayor que cero, es que fue un exito
            return results > 0;
        } catch(SQLException x_X){
            //Capturamos las excepciones que se pudieran generar
            System.out.println("Error al insertar el registro");
            System.out.println(x_X.getMessage());
            return false;
        }
    }
    
    public List<Users> read() {
        //Creamos la consulta SQL para obtener todos los usuarios
        String sql = "SELECT * FROM users";
        //Creamos un objeto de tipo ArrayList para almacenar los resultados
        List<Users> users = new ArrayList<>();
        try{
            //Establecemos la conexión
            Connection conn = DB.getConnection();
            //procesamos la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            //Ejecutamos la consulta y obtenemos los resultados
            ResultSet rs = stmt.executeQuery();
            //Recorremos los resultados y creamos un objeto a partir de ellos
            while(rs.next()){
                //Creamos un objeto a partir de la clase Users 
                Users u = new Users(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("secret"),
                        Boolean.parseBoolean(rs.getString("active"))
                );
                //Añadimos el objeto al ArrayList
                users.add(u);
            }
        }catch(SQLException x_X){
            System.out.println("Error listando los usuarios");
            System.out.println(x_X.getMessage());
        }
        //devolvemos el ArrayList con los objetos creados
        return users;
    }
    
    public Users read(int id) {
        //Creamos la consulta SQL para obtener el usuario del id indicado
        String sql = "SELECT * FROM users WHERE id = "+id; 
        Users u = null;
        try{
            //Establecemos la conexión
            Connection conn = DB.getConnection();
            //procesamos la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            //Ejecutamos la consulta y obtenemos los resultados
            ResultSet rs = stmt.executeQuery();            
            if(rs.next()){
                //Si el juego de resultados contiene datos, creamos el objeto 
                u = new Users(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("secret"),
                        Boolean.parseBoolean(rs.getString("active"))
                );                
            }
        }catch(SQLException x_X){
            //Capturamos los eventuales errores
            System.out.println("Error listando los usuarios");
            System.out.println(x_X.getMessage());
        }
        //Devolvemos el objeto o null según se haya ejecutado el metodo
        return u;
    }
    
    public Users read(String nick, String passcode) {
        String sql = "SELECT * FROM users WHERE nickname = '"+nick+"' AND secret = '"+passcode+"';"; 
        System.out.println(sql);
        try{
            Connection conn = DB.getConnection();            
            Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){                
                Users u = new Users(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("secret"),
                        Boolean.parseBoolean(rs.getString("active"))
                );
                return u;            
            } else {
                return null;
            }                        
        }catch(SQLException x_X){
            System.out.println("Error listando al usuario");
            System.out.println(x_X.getMessage());
            return null;
        }
        
    }
}
