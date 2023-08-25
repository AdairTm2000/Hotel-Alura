package mx.com.hotel.jdbc.controller;

import java.util.List;

import mx.com.hotel.jdbc.dao.HabitacionDao;
import mx.com.hotel.modelo.Habitacion;


public class HabitacionController {
	public List<Habitacion> listar() {
		return new HabitacionDao().listar();
	}
	
	public List<Habitacion> listarHabitaciones() {
		return new HabitacionDao().listarHabitaciones();
	}
	
}
