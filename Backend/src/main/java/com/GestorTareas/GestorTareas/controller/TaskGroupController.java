package com.GestorTareas.GestorTareas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.GestorTareas.GestorTareas.dto.TasksGroupDTO;
import com.GestorTareas.GestorTareas.model.TasksGroup;
import com.GestorTareas.GestorTareas.service.TasksGroupService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class TaskGroupController {
    TasksGroupService service;
    @Autowired
    public TaskGroupController(TasksGroupService service){
        this.service = service;
    }
    @GetMapping("/tasksgroups/{work_id}")
    public ResponseEntity<List<TasksGroupDTO>> getAllFromToWork(@PathVariable String work_id) {
        List<TasksGroupDTO> list;
        list = service.getAllFromJob(work_id);
        return ResponseEntity.ok(list);
    }
    @PostMapping("/tasksgroups")
    public ResponseEntity<TasksGroupDTO> postMethodName(@RequestBody TasksGroup tasksGroup) {
        TasksGroupDTO tasksGroupDTO;
        tasksGroupDTO = service.createTasksGroup(tasksGroup);
        
        return tasksGroupDTO != null ? ResponseEntity.ok(tasksGroupDTO) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/tasksgroups/{work_id}/{id}")
    public ResponseEntity<Void> deleteOneTasksGroup(@PathVariable String work_id, @PathVariable String id){
        boolean result = service.deleteTasksGroup(work_id, id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @PutMapping("/tasksgroups")
    public ResponseEntity<Void> updateOneTasksGroup(@RequestBody TasksGroup entity) {
        boolean result = service.updateTasksGroup(entity);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    
}
