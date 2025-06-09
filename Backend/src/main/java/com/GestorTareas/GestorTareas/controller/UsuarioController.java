/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.controller;

import com.GestorTareas.GestorTareas.dto.UsuarioDTO;
import com.GestorTareas.GestorTareas.model.Usuario;
import com.GestorTareas.GestorTareas.service.UsuarioService;
import com.GestorTareas.GestorTareas.dao.UsuarioDaoImplement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    // Obtine un usuario especifico por su id
    @GetMapping("/user/{id}")
    public UsuarioDTO getUsuario(@PathVariable String id){
        return UsuarioService.getUsuario(id);
    }
    // Obtiene la lista de usuarios
    @GetMapping("/user")
    public List<Usuario> getUsuarios(){
        return new UsuarioDaoImplement().getUsers();
    }
    // Crear un usuario a partir del siguiente json
    /*{
        "id": "27d02720-3177-4a1b-80db-bc6520a87146",
        "nombre": "Josefa",
        "contrasena": "20032003"
    }*/
    @PostMapping("/user")
    public UsuarioDTO createUsuario(@RequestBody Usuario user){
        return UsuarioService.crearUsuario(user);
        
    }
    // Elimina a un usuario a traves de su id
    @DeleteMapping("/user/{id}")
    public boolean deleteUsuario(@PathVariable String id){
        return UsuarioService.deleteUser(id);
    }
    // Actualiza al usuario
    @PutMapping("/user")
    public UsuarioDTO updateUsuario(@RequestBody Usuario usuario){
        return UsuarioService.updateUser(usuario);
    }
    
    
}
