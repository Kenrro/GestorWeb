package com.GestorTareas.GestorTareas.dao.idao;

import java.util.List;

import com.GestorTareas.GestorTareas.model.User;

public interface UserDAO {
    public boolean create(User user);
    public User get(String id);
    public User get(User user);
    public boolean delete(String id);
    public boolean update(User user);
    public List<User> getItems();
}
