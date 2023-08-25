package mx.com.hotel.jdbc.controller;

import java.util.List;

import mx.com.hotel.jdbc.dao.LoginDao;
import mx.com.hotel.modelo.LoginUsuario;

public class LoginController {
	
	public List<LoginUsuario> listar() {
		return new LoginDao().listar();
	}
	
}
