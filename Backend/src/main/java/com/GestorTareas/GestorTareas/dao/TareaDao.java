/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.Tarea;
import java.util.List;


public interface TareaDao {
    
    public boolean createTarea(Tarea tarea);
    public Tarea getTarea(String id);
    public boolean deleteTarea(String id);
    public boolean  updateTarea(Tarea tarea);
    public List<Tarea> getTareas();
    
}
