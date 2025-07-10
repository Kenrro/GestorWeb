/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

/**
 *
 * @author kenrr
 */
@Repository
public class UserDaoImplement implements UserDao {
    // Querys
    final String INSERT = "insert into usuarios (id, username, password, nombre, apellido) values (?,?,?,?,?)";
    final String SELECT = "select * from usuarios where id = ?";
    final String LOGIN = "select * from usuarios where username = ? AND password = ?";
    final String DELETE = "delete from usuarios where id = ?";
    final String selectUsers = "select * from usuarios";
    final String UPDATE = "update usuarios set nombre = ?, password = ? where id = ?";

    @Override
    public boolean createUser(User user) {
        int resultado = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(INSERT) ){
            pst.setString(1, user.getId());
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getName());
            pst.setString(5, user.getLastname());
            resultado = pst.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;
    }
    @Override
    public User getUser(String id) {
        
        User user = null;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(SELECT)){ 
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("simon");
                user = convertResultToUser(rs);
            }
        } catch (Exception e) {
            System.out.println("aca esta el error");
            e.printStackTrace();
        }
        return user;
    }
    // Sobre carga para el login
    public User getUser(User user){
        System.out.println(user.getUsername()+ user.getPassword());
        
        User userretornar = null;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(LOGIN)){
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                userretornar = convertResultToUser(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userretornar;
    }
        private User convertResultToUser(ResultSet rs){
            User user = null;
            try {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fech = rs.getDate("fech-creacion");
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setId(id);
                user.setName(nombre);
                user.setLastname(apellido);
                user.setCreation_date(fech);
            } catch (SQLException ex) {
                throw new RuntimeException("Error de conversion");
            }
            return user;
        }
    @Override
    public boolean deleteUser(String id) {
        int resultado = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(DELETE)){
            pst.setString(1, id);
            resultado = pst.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Problema al eliminar usuario");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;            
    }
    @Override
    public List<User> getUsers() {
        List<User> lista = new ArrayList<>();
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(selectUsers)){
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = convertResultToUser(rs);          
                lista.add(user);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public boolean updateUser(User user) {
        int resultado = 0;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(UPDATE)){
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getId());
            resultado = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException("Error al acualizar al usuario");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;
    }
}
