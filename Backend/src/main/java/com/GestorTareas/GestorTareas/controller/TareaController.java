/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.controller;

import com.GestorTareas.GestorTareas.dto.TareaDTO;
import com.GestorTareas.GestorTareas.model.Tarea;
import com.GestorTareas.GestorTareas.service.TareaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TareaController {
    
    // Crear una tarea
    /** JSON para crear una tarea
     {
        "id_usuario": "",
        "descripcion": ""
        } 
     */
    @PostMapping("/tareas")
    public ResponseEntity<TareaDTO> createTarea(@RequestBody Tarea tarea){
        TareaDTO nueva = TareaService.createTarea(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
    // Listar tareas de un usuario por su id
    
     @GetMapping("/users/{id}/tareas")
    public ResponseEntity<List<Tarea>> getTareas(@PathVariable String id){
        List<Tarea> tareas = TareaService.getTareas(id);
        return ResponseEntity.ok(tareas);
    }
    
    @GetMapping("/tareas/{id}")
    public ResponseEntity<TareaDTO> getTarea(@PathVariable String id){
        TareaDTO tarea = TareaService.getTarea(id);
        return tarea != null ? ResponseEntity.ok(tarea) : ResponseEntity.notFound().build();
    }
    @PutMapping("/tareas")
    public ResponseEntity<TareaDTO> updateTarea(@RequestBody Tarea tarea){
        TareaDTO actualizada = TareaService.updateTarea(tarea);
        return tarea != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/tareas/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable String id){
        boolean eliminado = TareaService.deleteTarea(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    
}
