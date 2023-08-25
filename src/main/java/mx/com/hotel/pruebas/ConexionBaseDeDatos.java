package mx.com.hotel.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import mx.com.hotel.jdbc.factory.*;

public class ConexionBaseDeDatos {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		for (int i = 0; i < 20; i++) {
			Connection conexion = connectionFactory.recuperaConexion();
			try(conexion){
				System.out.println("Abriendo la conexion de número " + (i+1));
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
}
