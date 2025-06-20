package com.GestorTareas.GestorTareas;

import com.GestorTareas.GestorTareas.model.Usuario;
import com.GestorTareas.GestorTareas.dao.ConexionSql;
import com.GestorTareas.GestorTareas.dao.UsuarioDao;
import com.GestorTareas.GestorTareas.dao.UsuarioDaoImplement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestorTareasApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(GestorTareasApplication.class, args);
	}

}
