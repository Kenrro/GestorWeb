/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.service;

// Servicios de tareas

import com.GestorTareas.GestorTareas.dto.TaskDTO;
import com.GestorTareas.GestorTareas.enums.TaskError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.TaskMapper;
import com.GestorTareas.GestorTareas.model.Task;
import com.GestorTareas.GestorTareas.dao.TaskDaoImplement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    TaskDaoImplement dao;
    TaskMapper mapper;
    public TaskService(TaskDaoImplement dao,
                        TaskMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }
    
    public TaskDTO createTask(Task tarea){
        TaskDTO dto = null;
        tarea.setId();
        if (dao.create(tarea)) {
            dto = mapper.toDto(tarea);
        }
        else {
            throw new ManagerException(TaskError.TASK_CREATION_FAILED);
        }
        return dto;
    }
    public TaskDTO getTask(String id){
        TaskDTO dto = null;
        Task tarea = dao.get(id);
        if (tarea == null) {
            throw new ManagerException(TaskError.TASK_NOT_FOUND);
        }else {
            dto = mapper.toDto(tarea);
        }
        return dto;
    }
    public TaskDTO updateTask(Task tarea){
        TaskDTO dto = null;
        if (dao.update(tarea)) {
            dto = mapper.toDto(tarea);
        }
        else throw new ManagerException(TaskError.TASK_UPDATE_FAILED);
        return dto;
    }
    public boolean deleteTask(String id){
        boolean resultado = false;
        resultado = dao.delete(id);
        if (resultado) throw new ManagerException(TaskError.TASK_DELETE_FAILED);
        return resultado;
    }
    public List<TaskDTO> getTasks(String id){
        List<TaskDTO> lista = new ArrayList<>();
        dao.getItems(id)
        .stream()
        .forEach(task -> lista.add(mapper.toDto(task)));
        if (lista.isEmpty()) throw new ManagerException(TaskError.TASKS_NOT_FOUND);
        return lista;
    }
}
