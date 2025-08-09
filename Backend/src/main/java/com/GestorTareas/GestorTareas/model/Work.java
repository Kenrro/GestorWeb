package com.GestorTareas.GestorTareas.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Work {
    private String id;
    private String name;
    private String user_id;
    private String description;
    private LocalDate creation_date;
    private Permission permits;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setId() {
        this.id = UUID.randomUUID().toString();
    } 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }
    public Permission getPermits() {
        return permits;
    }
    public void setPermits(Permission permits) {
        this.permits = permits;
    }
}
