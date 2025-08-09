/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dto;

import java.time.LocalDate;

/**
 *
 * @author kenrr
 */
public class UserDTO {
    
    private String id;
    private String username;
    private String name;
    private String lastname;
    private LocalDate  creation_date;
    
    public String getId() {
        return id;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastName) {
        this.lastname = lastName;
    }
    public LocalDate  getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(LocalDate  creation_date) {
        this.creation_date = creation_date;
    }
    
}
