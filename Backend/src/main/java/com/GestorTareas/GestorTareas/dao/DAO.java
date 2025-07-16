package com.GestorTareas.GestorTareas.dao;

import java.util.List;

import com.GestorTareas.GestorTareas.model.Task;

public interface DAO<T, id> {
    
    public boolean create(T tarea);
    public T get(id id);
    public boolean delete(id id);
    public boolean update(T tarea);
    public List<T> getItems(id id);
}
