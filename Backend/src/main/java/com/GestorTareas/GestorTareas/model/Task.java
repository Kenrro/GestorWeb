/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.model;

import java.sql.Date;
import java.util.UUID;


public class Task {
    
    private String id;
    private String id_user;
    private String task_group_id;
    private String work_id;  
    private String name;
    private String description;
    private boolean state;
    private Date creation_date;
    private Date limit_date;
    
    //Genera el hash de id
    public void setId() {
        this.id = UUID.randomUUID().toString();
    }
    
    public String getId() {
        return id;
    }
    
    
    public void setId(String id) {
        this.id = id;
    }
    
    
    public String getId_user() {
        return id_user;
    }
    
    
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
    public String getTask_group_id() {
        return task_group_id;
    }

    public void setTask_group_id(String task_group_id) {
        this.task_group_id = task_group_id;
    }

    public String getWork_id() {
        return work_id;
    }

    public void setWork_id(String work_id) {
        this.work_id = work_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public Date getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
    public Date getLimit_date() {
        return limit_date;
    }
    public void setLimit_date(Date limit_date) {
        this.limit_date = limit_date;
    }
}
