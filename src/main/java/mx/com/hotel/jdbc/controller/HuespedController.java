package mx.com.hotel.jdbc.controller;

import java.util.List;

import mx.com.hotel.jdbc.dao.HuespedDao;
import mx.com.hotel.modelo.Huesped;

public class HuespedController {
		
	public List<Huesped> buscar(String nombre, String apellido) {
		return new HuespedDao().buscar(nombre, apellido);
	}
	
	public List<Huesped> buscarId(Integer id) {
		return new HuespedDao().buscarId(id);
	}
	
	public int eliminar(Integer id, Integer id_reserva) {
		return new HuespedDao().eliminar(id, id_reserva);
	}
	
	public int modificar(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer id_reserva, Integer id_habitacion, Integer id) {
		return new HuespedDao().modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, id_reserva, id_habitacion, id);
	}
	
	public List<Huesped> listar() {
		return new HuespedDao().listar();	
	}
	
    public void guardar(Huesped huesped, Integer id_reserva, Integer id_habitacion){
    	huesped.setId_reserva(id_reserva);
    	huesped.setId_habitacion(id_habitacion);
    	new HuespedDao().guardar(huesped);
	}
}
