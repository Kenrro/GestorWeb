/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.service;

// Servicios de tareas

import com.GestorTareas.GestorTareas.dto.TaskDTO;
import com.GestorTareas.GestorTareas.model.Task;
import com.GestorTareas.GestorTareas.dao.TaskDaoImplement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    TaskDaoImplement dao;
    public TaskService(TaskDaoImplement dao){
        this.dao = dao;
    }

        private TaskDTO convertTareaToDto(Task tarea){
            TaskDTO dto = new TaskDTO();
            dto.setId(tarea.getId());
            dto.setDescription(tarea.getDescription());
            dto.setId_user(tarea.getId_user());
            dto.setName(tarea.getName());
            dto.setCreation_date(tarea.getCreation_date());
            dto.setState(tarea.isState());
            return dto;
        }
    
    public TaskDTO createTask(Task tarea){
        TaskDTO dto = null;
        try{
            tarea.setId();
            if (dao.createTarea(tarea)) {
                dto = convertTareaToDto(tarea);
            }
            else {
                System.out.println("fallo");
            }
        } catch(Exception ex){
            throw new RuntimeException("Error al crear la tarea");
        }
        return dto;
    }
    public TaskDTO getTask(String id){
        TaskDTO dto = null;
        try {
           
            Task tarea = dao.getTarea(id);
            if (tarea == null) {
                System.out.println("Tarea no encontrada");
            }else {
                dto = convertTareaToDto(tarea);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public TaskDTO updateTask(Task tarea){
        TaskDTO dto = null;
        try{
            if (dao.updateTarea(tarea)) {
                dto = convertTareaToDto(tarea);
            }
            else System.out.println("no se pudo modificar");
        } catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }
    public boolean deleteTask(String id){
        boolean resultado = false;
        try {
            resultado = dao.deleteTarea(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public List<Task> getTasks(String id){
        List<Task> lista = new ArrayList<>();
        try {
            lista = dao.getTareas(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
