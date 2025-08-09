package com.GestorTareas.GestorTareas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.GestorTareas.GestorTareas.service.TasksGroupAssignedService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class TasksGroupAssignedController {
    TasksGroupAssignedService service;
    @Autowired
    TasksGroupAssignedController(TasksGroupAssignedService service){
        this.service = service;
    }
    @PostMapping("/tasksgroupassigned/{userId}/{groupId}/{permitId}")
    public ResponseEntity<Void> createAssigned(@PathVariable String userId, @PathVariable String groupId, @PathVariable String permitId) {
        boolean result = false;
        result = service.createAssigned(userId, groupId, permitId);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @PutMapping("/tasksgroupassigned/{userId}/{groupId}/{permitId}")
    public ResponseEntity<Void> updateAssigned(@PathVariable String userId, @PathVariable String groupId, @PathVariable String permitId) {
        boolean result = false;
        result = service.updateAssigned(userId, groupId, permitId);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/tasksgroupassigned/{userId}/{groupId}")
    public ResponseEntity<Void> updateAssigned(@PathVariable String userId, @PathVariable String groupId) {
        boolean result = false;
        result = service.deleteAssigned(groupId, userId);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
}
