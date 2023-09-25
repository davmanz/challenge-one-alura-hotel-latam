package views;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql {
	 private static final String URL = "jdbc:mysql://localhost:3306/bd_hotel";
	    private static final String USUARIO = "root";
	    private static final String CONTRASEÑA = "";
	    
	    public static Connection obtenerConexion() throws SQLException {
	        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
	    }
}
