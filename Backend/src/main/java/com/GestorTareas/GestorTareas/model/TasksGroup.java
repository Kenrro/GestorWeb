package com.GestorTareas.GestorTareas.model;

import java.util.UUID;

public class TasksGroup {
    String id;
    String name;
    boolean visible;
    boolean restriction;
    String work_id;
    Permission permission;
    public Permission getPermission() {
        return permission;
    }
    public void setPermission(Permission permission) {
        this.permission = permission;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public boolean isRestriction() {
        return restriction;
    }
    public void setRestriction(boolean restriction) {
        this.restriction = restriction;
    }
    public String getWork_id() {
        return work_id;
    }
    public void setWork_id(String work_id) {
        this.work_id = work_id;
    }
}
