/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


public class Usuario {

    private String id;
    private String userName;
    private String contrasena;
    private String nombre;
    private String apellido;
    private Date fech_creacion;

    public Usuario() {
        setFechCreacion();
        System.out.println(userName);
    }
    
    public Usuario(String nombre, String contraseña) {
        this.id = UUID.randomUUID().toString();
        this.userName = nombre;
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

    

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechCreacion() {
        return fech_creacion;
    }

    public void setFechCreacion() {
        this.fech_creacion = Date.valueOf(LocalDate.MAX);
    }
    public void setFechCreacion(Date fech) {
        this.fech_creacion = fech;
    }
    
    
    
}
