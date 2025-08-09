/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.controller;

import com.GestorTareas.GestorTareas.dto.TaskDTO;
import com.GestorTareas.GestorTareas.model.Task;
import com.GestorTareas.GestorTareas.service.TaskService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TaskController {
    
    
    TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/tareas")
    public ResponseEntity<TaskDTO> createTask(@RequestBody Task tarea){
        TaskDTO create = taskService.createTask(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }
    // Listar tareas de un usuario por su id
    
     @GetMapping("/users/{id}/tareas")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable String id){
        List<TaskDTO> tasks = taskService.getTasks(id);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/tareas/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable String id){
        TaskDTO task = taskService.getTask(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }
    @PutMapping("/tareas")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody Task task){
        TaskDTO update = taskService.updateTask(task);
        return task != null ? ResponseEntity.ok(update) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/tareas/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id){
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    
}
