package com.GestorTareas.GestorTareas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.model.Work;
import com.GestorTareas.GestorTareas.service.WorkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class WorkController {

    WorkService service;
    @Autowired
    public WorkController(WorkService service){
        this.service = service;
    }

    @PostMapping("/work")
    public ResponseEntity<WorkDTO> postMethodName(@RequestBody Work entity) {
        WorkDTO nuevo = service.createWork(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    

}
