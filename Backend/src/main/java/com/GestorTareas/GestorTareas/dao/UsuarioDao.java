/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.Usuario;
import java.util.List;

/**
 *
 * @author kenrr
 */
public interface UsuarioDao {
    
    public boolean createUser(Usuario user);
    public Usuario getUser(String id);
    public boolean deleteUser(String id);
    public boolean updateUser(Usuario user);
    public List<Usuario> getUsers();
    
}
