package mx.com.hotel.modelo;


public class Habitacion {
	private Integer id;
	private String tipo;
	private Integer camas;
	private String baño;
	private String cocina;
	private String television;
	private String balcon;
	private Integer precio;
	
	public Habitacion() {

	}
	
	public Habitacion(Integer id, String tipo, Integer precio) {
		this.id = id;
		this.tipo = tipo;
		this.precio = precio;
	}

	public Habitacion(Integer camas, String baño, String cocina, String television,
			String balcon, Integer precio) {
		this.camas = camas;
		this.baño = baño;
		this.cocina = cocina;
		this.television = television;
		this.balcon = balcon;
		this.precio = precio;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCamas() {
		return camas;
	}

	public void setCamas(Integer camas) {
		this.camas = camas;
	}

	public String getBaño() {
		return baño;
	}

	public void setBaño(String baño) {
		this.baño = baño;
	}

	public String getCocina() {
		return cocina;
	}

	public void setCocina(String cocina) {
		this.cocina = cocina;
	}

	public String getTelevision() {
		return television;
	}

	public void setTelevision(String television) {
		this.television = television;
	}

	public String getBalcon() {
		return balcon;
	}

	public void setBalcon(String balcon) {
		this.balcon = balcon;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return this.tipo;
	}
	
}
