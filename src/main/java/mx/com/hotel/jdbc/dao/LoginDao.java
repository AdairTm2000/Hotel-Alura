package mx.com.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.hotel.jdbc.factory.ConnectionFactory;
import mx.com.hotel.modelo.LoginUsuario;


public class LoginDao {
	
	final private Connection con;
	
	public LoginDao() {
        con = new ConnectionFactory().recuperaConexion();
    }
	
	public List<LoginUsuario> listar() {
		List<LoginUsuario> resultado = new ArrayList<>();
		
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT usuario,contraseña FROM hotel_alura.login;");
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					while(resultSet.next()) {
						var loginUsuario = new LoginUsuario(resultSet.getNString("usuario"),resultSet.getNString("contraseña"));
						resultado.add(loginUsuario);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
