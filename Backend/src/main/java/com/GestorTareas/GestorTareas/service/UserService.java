/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.service;
import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.enums.UserError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.UserMapper;
import com.GestorTareas.GestorTareas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.GestorTareas.GestorTareas.dao.UserDaoImplement;
import com.GestorTareas.GestorTareas.dao.idao.UserDAO;

@Service
public class UserService {
    UserDAO dao;
    UserMapper mapper;
    @Autowired
    public UserService(UserDaoImplement dao,
                        UserMapper mapper){
        this.dao = dao;
        this.mapper = mapper; 
    }
    
    public UserDTO crearUsuario(User user){
        UserDTO dto = null;
        user.setId();
        if (dao.create(user)) {
            dto = mapper.toDto(user);
        }
        return dto;
    }
    public UserDTO getUsuario(String id) {
        UserDTO dto = null;
        User user = dao.get(id);
        if (user == null) {
            throw new ManagerException(UserError.USER_NOT_FOUND);
        }
        else{
            dto = mapper.toDto(user);
        }
        return dto;
    }
    // Sobrecarga para login
    public UserDTO getUsuarios(User user) {
        UserDTO dto = null;
        User usuariosretornar = dao.get(user);
        if (usuariosretornar == null) {
            throw new ManagerException(UserError.USER_LOGIN_FAILED);
        }
        else{
            dto = mapper.toDto(usuariosretornar);
        }
        return dto;
    }
    public boolean deleteUser(String id){
        boolean resultado = false;
        resultado = dao.delete(id);
        if (!resultado) {
            throw new ManagerException(UserError.USER_DELETE_FAILED);
        }
        return resultado;
    }
    public UserDTO updateUser(User user){
        UserDTO dto = null;

        if (dao.update(user)) {
            dto = mapper.toDto(user);
        }
        else throw new ManagerException(UserError.USER_UPDATE_FAILED);
        return dto;
    }
    
    
}
