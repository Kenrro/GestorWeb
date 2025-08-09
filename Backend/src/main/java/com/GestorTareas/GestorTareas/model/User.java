/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.model;

import java.time.LocalDate;
import java.util.UUID;


public class User {

    private String id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private LocalDate creation_date;

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    } 
    public void setId(String id) {
        this.id = id;
    } 

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public User() {
    }
    
    public User(String nombre, String contraseña) {
        this.id = UUID.randomUUID().toString();
        this.username = nombre;
        this.password = contraseña;
        
    }


    
    
    
    
}
