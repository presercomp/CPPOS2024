package models;
import controllers.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProductsDAO {
        public boolean create(Products u){
        //Creamos la consulta de insercion con preparedStatements
        //para evitar posibles injecciones de SQL
        String sql = "INSERT INTO products (name) VALUES (?)";
        try {
            //Nos conectamos a la base de datos
            Connection conn = DB.getConnection();
            //Procesamos la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            //Definimos los valores para los dos parametros con ? en la consulta
            stmt.setString(1, u.getName());
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
    
    public List<Products> read() {
        //Creamos la consulta SQL para obtener todos los usuarios
        String sql = "SELECT * FROM products";
        //Creamos un objeto de tipo ArrayList para almacenar los resultados
        List<Products> products = new ArrayList<>();
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
                Products u = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Boolean.parseBoolean(rs.getString("active"))
                );
                //Añadimos el objeto al ArrayList
                products.add(u);
            }
        }catch(SQLException x_X){
            System.out.println("Error listando los productos");
            System.out.println(x_X.getMessage());
        }
        //devolvemos el ArrayList con los objetos creados
        return products;
    }
    
    public Products read(int id) {
        //Creamos la consulta SQL para obtener el usuario del id indicado
        String sql = "SELECT * FROM products WHERE id = "+id; 
        Products u = null;
        try{
            //Establecemos la conexión
            Connection conn = DB.getConnection();
            //procesamos la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            //Ejecutamos la consulta y obtenemos los resultados
            ResultSet rs = stmt.executeQuery();            
            if(rs.next()){
                //Si el juego de resultados contiene datos, creamos el objeto 
                u = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),                        
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
    
    public Products read(String name) {
        String sql = "SELECT * FROM products WHERE name = '"+name+"';"; 
        System.out.println(sql);
        try{
            Connection conn = DB.getConnection();            
            Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){                
                Products u = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),                        
                        Boolean.parseBoolean(rs.getString("active"))
                );
                return u;            
            } else {
                return null;
            }                        
        }catch(SQLException x_X){
            System.out.println("Error listando al producto");
            System.out.println(x_X.getMessage());
            return null;
        }
        
    }

}
