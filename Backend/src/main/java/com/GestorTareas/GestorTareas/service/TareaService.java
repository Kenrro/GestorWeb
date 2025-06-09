/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.service;

// Servicios de tareas

import com.GestorTareas.GestorTareas.dto.TareaDTO;
import com.GestorTareas.GestorTareas.model.Tarea;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.GestorTareas.GestorTareas.dao.TareaDaoImplement;
import java.util.ArrayList;
import java.util.List;

public class TareaService {
    static TareaDaoImplement dao = new TareaDaoImplement();
    
        private static TareaDTO convertTareaToDto(Tarea tarea){
            TareaDTO dto = new TareaDTO();
            dto.setId(tarea.getId());
            dto.setDescripcion(tarea.getDescripcion());
            dto.setId_usuario(tarea.getId_usuario());
            dto.setEstado(tarea.isEstado());
            return dto;
        }
    
    public static TareaDTO createTarea(Tarea tarea){
        TareaDTO dto = null;
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
    public static TareaDTO getTarea(String id){
        TareaDTO dto = null;
        try {
           
            Tarea tarea = dao.getTarea(id);
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
    public static TareaDTO updateTarea(Tarea tarea){
        TareaDTO dto = null;
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
    public static boolean deleteTarea(String id){
        boolean resultado = false;
        try {
            resultado = dao.deleteTarea(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public static List<Tarea> getTareas(){
        List<Tarea> lista = new ArrayList<>();
        try {
            lista = dao.getTareas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
