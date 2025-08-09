package com.GestorTareas.GestorTareas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.model.Work;
import com.GestorTareas.GestorTareas.service.WorkService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class WorkController {

    WorkService service;
    @Autowired
    public WorkController(WorkService service){
        this.service = service;
    }

    @PostMapping("/works") // Crear un work
    public ResponseEntity<WorkDTO> postMethodName(@RequestBody Work entity) {
        WorkDTO nuevo = service.createWork(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    @GetMapping("/works/{id}/{user_id}") // 
    public ResponseEntity<WorkDTO> getMethodName(@PathVariable String id, @PathVariable String user_id) {
        Work send = new Work();
        send.setId(id);
        send.setUser_id(user_id);
        WorkDTO dto = service.getWork(send);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }
    @GetMapping("/works/{user_id}") // Works creados por el usuario
    public ResponseEntity<List<WorkDTO>> getWorksParticipation(@PathVariable String user_id) {
        List<WorkDTO> dto = service.getWorks(user_id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }
    @GetMapping("/works/{work_id}/users") // participantes de un work
    public ResponseEntity<List<UserDTO>> getParticipationsWork(@PathVariable String work_id) {
        List<UserDTO> dto = service.getParticipation(work_id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }
    @DeleteMapping("/works/{user_id}/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable String user_id, @PathVariable String id){
        Work work = new Work();
        work.setUser_id(user_id);
        work.setId(id);
        boolean deleted = service.deleteWork(work);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @PutMapping("/works")
    public ResponseEntity<Void> putMethodName(@RequestBody Work Work) {    
        boolean update = service.updateWork(Work);
        return update ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();    
   }
    
    

}
