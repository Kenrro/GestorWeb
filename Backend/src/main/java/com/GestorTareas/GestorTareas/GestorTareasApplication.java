package com.GestorTareas.GestorTareas;

import com.GestorTareas.GestorTareas.model.User;
import com.GestorTareas.GestorTareas.dao.ConexionSql;
import com.GestorTareas.GestorTareas.dao.UserDao;
import com.GestorTareas.GestorTareas.dao.UserDaoImplement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestorTareasApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(GestorTareasApplication.class, args);
	}

}
