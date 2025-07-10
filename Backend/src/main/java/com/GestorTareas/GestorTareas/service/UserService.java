/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.service;

import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.exception.UserException;
import com.GestorTareas.GestorTareas.model.User;
import com.GestorTareas.enums.UserError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestorTareas.GestorTareas.dao.UserDao;
import com.GestorTareas.GestorTareas.dao.UserDaoImplement;

@Service
public class UserService {
    UserDao dao;
    @Autowired
    public UserService(UserDaoImplement dao){
        this.dao = dao;
    }
    
        // Convierte un usuario a dto.
        private  UserDTO convertUsuarioToDto(User user){
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setName(user.getName());
            dto.setLastname(user.getLastname());
            dto.setCreation_date(user.getCreation_date());
            return dto;
        }
    
    public UserDTO crearUsuario(User user){
        UserDTO dto = null;
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
    public UserDTO getUsuario(String id) {
        UserDTO dto = null;
        try {
            User user = dao.getUser(id);
            if (user == null) {
                throw new UserException(UserError.USER_NOT_FOUND);
            }
            else{
                dto = convertUsuarioToDto(user);
            }
        } catch (UserException e) {
            System.out.println(e.getDescription());
        }
        return dto;
    }
    // Sobrecarga para login
    public UserDTO getUsuarios(User user) {
        UserDTO dto = null;
        try {
            User usuariosretornar = dao.getUser(user);
            if (user == null) {
                System.out.println("Error en el login");
            }
            else{
                dto = convertUsuarioToDto(usuariosretornar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    public boolean deleteUser(String id){
        boolean resultado = false;
        try{
            resultado = dao.deleteUser(id);
        } catch(Exception E){
            throw new RuntimeException("Error al eliminar al usuario");
        }
        return resultado;
    }
    public UserDTO updateUser(User user){
        UserDTO dto = null;
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
