/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.Task;
import java.util.List;


public interface TareaDao {
    
    public boolean createTarea(Task tarea);
    public Task getTarea(String id);
    public boolean deleteTarea(String id);
    public boolean  updateTarea(Task tarea);
    public List<Task> getTareas(String id);
    
}
