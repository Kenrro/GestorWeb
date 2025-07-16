package com.GestorTareas.GestorTareas.dao.idao;

import java.util.List;

import com.GestorTareas.GestorTareas.model.Task;

public interface TaskDAO {
    public boolean create(Task task);
    public Task get(String id);
    public boolean delete(String id);
    public boolean update(Task task);
    public List<Task> getItems(String id);
}
