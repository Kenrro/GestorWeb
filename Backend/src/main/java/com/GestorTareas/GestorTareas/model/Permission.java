package com.GestorTareas.GestorTareas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = PermissionDeserializer.class)
public enum Permission {
    ADMINISTRATION(1, "Administration"),
    EDIT(2, "Edit"),
    WRITE(3, "Write"),
    READ(4, "Read");

    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    Permission(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static Permission fromId(int id){
        for(Permission p : values()){
            if (p.getId() == id) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid argument "+ id);
    }
}
