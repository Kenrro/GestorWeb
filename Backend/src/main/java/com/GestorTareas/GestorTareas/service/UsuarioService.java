/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.service;

import com.GestorTareas.GestorTareas.dto.UsuarioDTO;
import com.GestorTareas.GestorTareas.model.Usuario;
import com.GestorTareas.GestorTareas.dao.UsuarioDao;
import com.GestorTareas.GestorTareas.dao.UsuarioDaoImplement;

public class UsuarioService {

    private static UsuarioDao dao = new UsuarioDaoImplement();
    
        // Convierte un usuario a dto.
        private static UsuarioDTO convertUsuarioToDto(Usuario user){
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(user.getId());
            dto.setNombre(user.getNombre());
            return dto;
        }
    
    public static UsuarioDTO crearUsuario(Usuario user){
        UsuarioDTO dto = null;
        try {
            user.setId();
            if (dao.createUser(user)) {
                dto = convertUsuarioToDto(user);
            }
            else {
                System.out.println("fallo");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Problema al conversar al usuario");
        }
        return dto;
    }
    public static UsuarioDTO getUsuario(String id) {
        UsuarioDTO dto = null;
        try {
            Usuario user = dao.getUser(id);
            if (user == null) {
                System.out.println("problema aqui");
            }
            else{
                dto = convertUsuarioToDto(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public static boolean deleteUser(String id){
        boolean resultado = false;
        try{
            resultado = dao.deleteUser(id);
        } catch(Exception E){
            throw new RuntimeException("Error al eliminar al usuario");
        }
        return resultado;
    }
    public static UsuarioDTO updateUser(Usuario user){
        UsuarioDTO dto = null;
        try {
            if (dao.updateUser(user)) {
                dto = convertUsuarioToDto(user);
            }
            else System.out.println("No se pudo modificar");
        } catch (Exception e) {
            //throw new RuntimeException("Problema al actualizar al usuario");
            e.printStackTrace();
        }
        return dto;
    }
    
    
}
