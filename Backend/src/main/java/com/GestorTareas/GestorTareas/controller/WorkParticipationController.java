package com.GestorTareas.GestorTareas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.GestorTareas.GestorTareas.model.Permission;
import com.GestorTareas.GestorTareas.model.Work;
import com.GestorTareas.GestorTareas.service.WorkaParticipationService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class WorkParticipationController {
    WorkaParticipationService service;
    @Autowired
    public WorkParticipationController(WorkaParticipationService service){
        this.service = service;
    }

    @PutMapping("/participations/{id}")
    public ResponseEntity<Permission> putMethodName(@PathVariable String id, @RequestBody Work entity) {
        Permission permission = entity.getPermits();
        permission = service.updatePermission(entity, id, permission);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(permission);
    }
    @PostMapping("/participations/{id}")
    public ResponseEntity<Void> postMethodName(@PathVariable String id,@RequestBody Work work) {
        Permission permission = work.getPermits();
        boolean result = service.setParticipation(work, id, permission);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); 
    }
    @DeleteMapping("/participations/{user_id}/{work_id}")
    public ResponseEntity<Void> deleteController(@PathVariable String user_id, @PathVariable String work_id){
        boolean result = service.deleteParticipation(work_id, user_id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); 
    }
    
}
