/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.User;
import java.util.List;

/**
 *
 * @author kenrr
 */
public interface UserDao {
    
    public boolean createUser(User user);
    public User getUser(String id);
    public User getUser(User user);
    public boolean deleteUser(String id);
    public boolean updateUser(User user);
    public List<User> getUsers();
    
}
