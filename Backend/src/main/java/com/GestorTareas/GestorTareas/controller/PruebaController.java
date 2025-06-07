/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.controller;

import com.GestorTareas.GestorTareas.model.Usuario;
import dacom.GestorTareas.GestorTareas.dao.UsuarioDaoImplement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
    
    @GetMapping("/user")
    public List <Usuario> getUsuario(){
        new UsuarioDaoImplement().deleteUser("asfasdf");
        return new UsuarioDaoImplement().getUsers();
        
    }
    
    
}
