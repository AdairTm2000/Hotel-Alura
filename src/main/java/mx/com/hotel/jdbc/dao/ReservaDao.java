package mx.com.hotel.jdbc.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.com.hotel.jdbc.factory.ConnectionFactory;
import mx.com.hotel.modelo.Reserva;

public class ReservaDao {
	final private Connection con;
	
	public ReservaDao() {
        con = new ConnectionFactory().recuperaConexion();
    }
	
	public List<Reserva> buscar(String nombre, String apellido) {
		List<Reserva> resultado = new ArrayList<>();

		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT reserva.id,reserva.fechaEntrada,reserva.fechaSalida,reserva.valor,reserva.formaPago FROM hotel_alura.reserva INNER JOIN hotel_alura.huesped ON reserva.id = huesped.id_reserva AND (huesped.nombre = ? OR huesped.apellido = ?)");
			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("id"), resultSet.getString("fechaEntrada"),
								resultSet.getString("fechaSalida"), resultSet.getInt("valor"),
								resultSet.getNString("formaPago"));
						resultado.add(reserva);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer id) {		
		try(con){
			final PreparedStatement statement = con.prepareStatement("DELETE FROM hotel_alura.huesped WHERE id_reserva = ?");
			try(statement) {
				statement.setInt(1, id);
				final PreparedStatement statement2 = con.prepareStatement("DELETE FROM hotel_alura.reserva WHERE id = ?");
				
				try(statement2) {
					statement2.setInt(1, id);
					final PreparedStatement statement3 = con.prepareStatement("ALTER TABLE hotel_alura.reserva AUTO_INCREMENT = " + 20001);

					try(statement3) {
						
						final PreparedStatement statement4 = con.prepareStatement("ALTER TABLE hotel_alura.huesped AUTO_INCREMENT = " + 1);

						try (statement4) {
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
	
	public int modificar(String fechaEntrada, String fechaSalida, Integer valor,String formaPago, Integer id) {
	    try (con){
	    	final PreparedStatement stmt = con.prepareStatement("UPDATE hotel_alura.reserva SET fechaEntrada = ?, fechaSalida = ?, valor = ?, formaPago = ? WHERE id = ?");
		    
			try (stmt) {
				stmt.setString(1, fechaEntrada);
			    stmt.setString(2, fechaSalida);
			    stmt.setInt(3, valor);
			    stmt.setString(4, formaPago);
			    stmt.setInt(5, id);
			    
			    //stmt.execute();
			    int updateCount = stmt.executeUpdate(); // o = stmt.getUpdateCount(); 
			    return updateCount;
			}
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();
		
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM hotel_alura.reserva;");
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					while(resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("id"), resultSet.getString("fechaEntrada"), resultSet.getString("fechaSalida"), resultSet.getInt("valor"), resultSet.getNString("formaPago"));
						resultado.add(reserva);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> listarId() {
		List<Reserva> resultado = new ArrayList<>();
		
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id FROM hotel_alura.reserva;");
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					while(resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("id"));
						resultado.add(reserva);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void guardar(Reserva reserva) {
		
		try (con){
			final PreparedStatement statement = con.prepareStatement("INSERT INTO hotel_alura.reserva(fechaEntrada, fechaSalida, valor, formaPago)"
					+ " VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
			try (statement) {
				ejecutaRegistro(reserva, statement);
			}		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement)
			throws SQLException {		
				statement.setString(1, reserva.getFechaEntrada());
				statement.setString(2, reserva.getFechaSalida());
				statement.setInt(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				
				statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();
			
			try (resultSet) {
				while(resultSet.next()) {
					reserva.setId(resultSet.getInt(1));
					System.out.println(String.format( "Fue insertado el producto %s", reserva));
				}
			}
		
	}
}
