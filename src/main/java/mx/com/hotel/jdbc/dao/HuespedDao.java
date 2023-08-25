package mx.com.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.com.hotel.jdbc.factory.ConnectionFactory;
import mx.com.hotel.modelo.Huesped;

public class HuespedDao {
	final private Connection con;
	
	public HuespedDao() {
        con = new ConnectionFactory().recuperaConexion();
    }
	
	public List<Huesped> buscar(String nombre, String apellido) {
		List<Huesped> resultado = new ArrayList<>();

		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva, id_habitacion FROM hotel_alura.huesped WHERE nombre = ? OR apellido = ?;");
			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("id"), resultSet.getNString("nombre"), resultSet.getNString("apellido"), resultSet.getString("fecha_de_nacimiento"), resultSet.getNString("nacionalidad"), resultSet.getNString("telefono"), resultSet.getInt("id_reserva"), resultSet.getInt("id_habitacion"));
						resultado.add(huesped);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		
	public List<Huesped> buscarId(Integer id) {
		List<Huesped> resultado = new ArrayList<>();

		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva, id_habitacion FROM hotel_alura.huesped WHERE id = ?;");
			try (statement) {
				statement.setInt(1, id);
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("id"), resultSet.getNString("nombre"), resultSet.getNString("apellido"), resultSet.getString("fecha_de_nacimiento"), resultSet.getNString("nacionalidad"), resultSet.getNString("telefono"), resultSet.getInt("id_reserva"), resultSet.getInt("id_habitacion"));
						resultado.add(huesped);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer id, Integer id_reserva) {		
		try(con){
			
			final PreparedStatement statement = con.prepareStatement("DELETE FROM hotel_alura.huesped WHERE id = ?");
			
			try(statement) {
				statement.setInt(1, id);
				final PreparedStatement statement2 = con.prepareStatement("DELETE FROM hotel_alura.reserva WHERE id = ?");
				
				try(statement2) {
				
					statement2.setInt(1, id_reserva);
					final PreparedStatement statement3 = con.prepareStatement("ALTER TABLE hotel_alura.huesped AUTO_INCREMENT = " + 1);
					
					try (statement3) {
						
						final PreparedStatement statement4 = con.prepareStatement("ALTER TABLE hotel_alura.reserva AUTO_INCREMENT = " + 20001);
						
						try(statement4) {
							statement.execute();
							statement2.execute();
							statement3.execute();
							statement4.execute();
							
							int updateCount = statement.getUpdateCount(); 

							return updateCount;
						}
						
					}
				}
			}
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public int modificar(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer id_reserva, Integer id_habitacion, Integer id) {
	    try (con){
	    	final PreparedStatement stmt = con.prepareStatement("UPDATE hotel_alura.huesped SET nombre = ?, apellido = ?, fecha_de_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ?, id_habitacion = ? WHERE id = ?");
		    
			try (stmt) {
				stmt.setString(1, nombre);
			    stmt.setString(2, apellido);
			    stmt.setString(3, fechaNacimiento);
			    stmt.setString(4, nacionalidad);
			    stmt.setString(5, telefono);
			    stmt.setInt(6, id_reserva);
			    stmt.setInt(7, id_habitacion);
			    stmt.setInt(8, id);
			    
			    //stmt.execute();
			    int updateCount = stmt.executeUpdate(); // o = stmt.getUpdateCount(); 
			    return updateCount;
			}
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva, id_habitacion FROM hotel_alura.huesped;");
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					while(resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("id"), resultSet.getNString("nombre"), resultSet.getNString("apellido"), resultSet.getString("fecha_de_nacimiento"), resultSet.getNString("nacionalidad"), resultSet.getNString("telefono"), resultSet.getInt("id_reserva"), resultSet.getInt("id_habitacion"));
						resultado.add(huesped);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	 
	
	public void guardar(Huesped huesped) {
		
		try (con){
			final PreparedStatement statement = con.prepareStatement("INSERT INTO hotel_alura.huesped(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva, id_habitacion)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
			try (statement) {
				ejecutaRegistro(huesped, statement);
			}		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(Huesped huesped, PreparedStatement statement)
			throws SQLException {		
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId_reserva());
				statement.setInt(7, huesped.getId_habitacion());
				
				statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();
			
			try (resultSet) {
				while(resultSet.next()) {
					huesped.setId(resultSet.getInt(1));
					System.out.println(String.format( "Fue insertado el producto %s", huesped));
				}
			}
		
	}
}
