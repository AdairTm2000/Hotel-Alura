package mx.com.hotel.modelo;

public class LoginUsuario {
	
	private String usuario;
	private String contrasena;
	
	public LoginUsuario(String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
		
	public String getUsuraio() {
		return usuario;
	}

	public void setUsuraio(String usuario) {
		this.usuario = usuario;
	}
	
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return this.usuario + this.contrasena;
	}

}
