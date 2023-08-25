package mx.com.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.hotel.jdbc.factory.ConnectionFactory;
import mx.com.hotel.modelo.Habitacion;

public class HabitacionDao {
	final private Connection con;
	
	public HabitacionDao() {
        con = new ConnectionFactory().recuperaConexion();
    }
	
	public List<Habitacion> listar() {
		List<Habitacion> resultado = new ArrayList<>();
		
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id, tipo, precio FROM hotel_alura.habitaciones;");
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					while(resultSet.next()) {
						var habitacion = new Habitacion(resultSet.getInt("id"), resultSet.getNString("tipo"), resultSet.getInt("precio"));
						resultado.add(habitacion);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Habitacion> listarHabitaciones() {
		List<Habitacion> resultado = new ArrayList<>();
		
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT camas, baño, cocina, television, balcon, precio FROM hotel_alura.habitaciones;");
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					while(resultSet.next()) {
						var habitacion = new Habitacion(resultSet.getInt("camas"), resultSet.getNString("baño"),  resultSet.getNString("cocina"),  resultSet.getNString("television"),  resultSet.getNString("balcon"), resultSet.getInt("precio"));
						resultado.add(habitacion);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
