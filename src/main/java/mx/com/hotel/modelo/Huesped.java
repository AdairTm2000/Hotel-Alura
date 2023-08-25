package mx.com.hotel.modelo;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer id_reserva;
	private Integer id_habitacion;
			
	public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public Huesped(Integer id,String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer id_reserva, Integer id_habitacion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
		this.id_habitacion = id_habitacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(Integer id_reserva) {
		this.id_reserva = id_reserva;
	}
	
	public Integer getId_habitacion() {
		return id_habitacion;
	}

	public void setId_habitacion(Integer id_habitacion) {
		this.id_habitacion = id_habitacion;
	}

	@Override
	public String toString() {
		return String.format("{id: %s, nombre: %s, Apellido: %s, fecha de nacimiento: %s, nacionalidad: %s, telefono: %s}", this.id, this.nombre, this.apellido, this.fechaNacimiento, this.nacionalidad, this.telefono);
	}
}
