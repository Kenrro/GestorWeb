/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.model;

import java.sql.Date;
import java.util.UUID;


public class Tarea {
    
    private String id;
    private String id_usuario;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fech_creacion;
    private Date fech_limit;

    public String getId() {
        return id;
    }
    //Genera el hash de id
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

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFech_creacion() {
        return fech_creacion;
    }

    public void setFech_creacion(Date fech_creacion) {
        this.fech_creacion = fech_creacion;
    }

    public Date getFech_limit() {
        return fech_limit;
    }

    public void setFech_limit(Date fech_limit) {
        this.fech_limit = fech_limit;
    }
    
    
}
