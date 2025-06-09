/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.controller;

import com.GestorTareas.GestorTareas.dto.TareaDTO;
import com.GestorTareas.GestorTareas.model.Tarea;
import com.GestorTareas.GestorTareas.service.TareaService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TareaController {
    
    @PostMapping("/tarea")
    public TareaDTO createTarea(@RequestBody Tarea tarea){
        return TareaService.createTarea(tarea);
    }
    @GetMapping("/tarea")
    public List<Tarea> getTareas(){
        return TareaService.getTareas();
    }
    @GetMapping("/tarea/{id}")
    public TareaDTO getTarea(@PathVariable String id){
        return TareaService.getTarea(id);
    }
    @PutMapping("/tarea")
    public TareaDTO updateTarea(@RequestBody Tarea tarea){
        return TareaService.updateTarea(tarea);
    }
    @DeleteMapping("/tarea/{id}")
    public boolean deleteTarea(@PathVariable String id){
        return TareaService.deleteTarea(id);
    }
    
}
