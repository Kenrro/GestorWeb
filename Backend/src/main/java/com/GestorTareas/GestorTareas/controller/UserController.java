/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.controller;

import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.model.User;
import com.GestorTareas.GestorTareas.service.UserService;
import com.GestorTareas.GestorTareas.dao.UserDaoImplement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // Obtine un usuario especifico por su id
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        UserDTO usuario = userService.getUsuario(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }
    // Obtiene la lista de usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> lista = new UserDaoImplement().getUsers();
        return ResponseEntity.ok(lista);
    }
    @PostMapping("/users/login")
    public ResponseEntity<UserDTO> getLogin(@RequestBody User user){
        UserDTO respuesta = userService.getUsuarios(user);
        return respuesta != null ? ResponseEntity.ok(respuesta) : ResponseEntity.notFound().build();
    }
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user){
        UserDTO nuevo = userService.crearUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        
    }
    
    // Elimina a un usuario a traves de su id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    // Actualiza al usuario
    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User usuario){
        UserDTO update = userService.updateUser(usuario);
        return update != null ? ResponseEntity.ok(update) : ResponseEntity.notFound().build();    
    }
    
    
}
