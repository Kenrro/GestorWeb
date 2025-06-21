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
public class UsuarioController {
    // Obtine un usuario especifico por su id
    @GetMapping("/users/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable String id){
        UsuarioDTO usuario = UsuarioService.getUsuario(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }
    // Obtiene la lista de usuarios
    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> lista = new UsuarioDaoImplement().getUsers();
        return ResponseEntity.ok(lista);
    }
    @PostMapping("/users/login")
    public ResponseEntity<UsuarioDTO> getLogin(@RequestBody Usuario user){
        UsuarioDTO respuesta = UsuarioService.getUsuarios(user);
        return respuesta != null ? ResponseEntity.ok(respuesta) : ResponseEntity.notFound().build();
    }
    @PostMapping("/users")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody Usuario user){
        UsuarioDTO nuevo = UsuarioService.crearUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        
    }
    
    // Elimina a un usuario a traves de su id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id){
        boolean eliminado = UsuarioService.deleteUser(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    // Actualiza al usuario
    @PutMapping("/users")
    public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody Usuario usuario){
        UsuarioDTO actualizado = UsuarioService.updateUser(usuario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();    
    }
    
    
}
