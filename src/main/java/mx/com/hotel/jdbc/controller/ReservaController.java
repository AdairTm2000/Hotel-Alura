package mx.com.hotel.jdbc.controller;

import java.util.List;

import mx.com.hotel.jdbc.dao.ReservaDao;
import mx.com.hotel.modelo.Reserva;

public class ReservaController {
	
	public int eliminar(Integer id) {
		return new ReservaDao().eliminar(id);
	}
	
	public int modificar(String fechaEntrada, String fechaSalida, Integer valor,String formaPago, Integer id) {
		return new ReservaDao().modificar(fechaEntrada, fechaSalida, valor, formaPago, id);
	}
		
	public List<Reserva> buscar(String nombre, String apellido) {
		return new ReservaDao().buscar(nombre, apellido);
	}
	
	public List<Reserva> listar() {
		return new ReservaDao().listar();
	}
	
	public List<Reserva> listarId() {
		return new ReservaDao().listarId();
	}
	
	public void guardar(Reserva reserva){
    	new ReservaDao().guardar(reserva);
	}
}
