/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.model;

import java.util.UUID;


public class Usuario {

    private String id;
    private String nombre;
    private String contrasena;

    public Usuario() {
    }
    
    public Usuario(String nombre, String contraseña) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.contrasena = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    

}
