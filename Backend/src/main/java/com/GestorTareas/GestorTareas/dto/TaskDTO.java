/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dto;

import java.sql.Date;

/**
 *
 * @author kenrr
 */
public class TaskDTO {
    
    private String id;
    private String id_user;
    private String name;
    private String description;
    private boolean state;
    private Date creation_date;

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }
    
    
    
    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_usuario) {
        this.id_user = id_usuario;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean estado) {
        this.state = estado;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date fech_creacion) {
        this.creation_date = fech_creacion;
    }
    
    
    
}
