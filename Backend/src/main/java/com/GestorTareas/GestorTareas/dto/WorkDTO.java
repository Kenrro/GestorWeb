package com.GestorTareas.GestorTareas.dto;

import java.sql.Date;

import com.GestorTareas.GestorTareas.model.Permission;

public class WorkDTO {

    private String id;
    private String name;
    private String user_id;
    private String description;
    private Date creation_date;
    private Permission permits;
    public Permission getPermits() {
        return permits;
    }
    public void setPermits(Permission permits) {
        this.permits = permits;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public Date getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }


}
