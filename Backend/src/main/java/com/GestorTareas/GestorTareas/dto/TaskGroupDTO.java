package com.GestorTareas.GestorTareas.dto;

public class TaskGroupDTO {
    private String id;
    private String name;
    private boolean visible;
    private boolean restriction;
    private String work_id;
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
