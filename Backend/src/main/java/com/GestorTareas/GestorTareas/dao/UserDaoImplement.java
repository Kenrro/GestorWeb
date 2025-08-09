/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.dao.idao.UserDAO;
import com.GestorTareas.GestorTareas.enums.UserError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.GenericRowMapper;
import com.GestorTareas.GestorTareas.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kenrr
 */
@Repository
public class UserDaoImplement implements UserDAO {
    // Querys
    final String INSERT = "insert into users (id, username, password, name, lastname) values (?,?,?,?,?)";
    final String SELECT = "select * from users where id = ?";
    final String LOGIN = "select * from users where username = ? AND password = ?";
    final String DELETE = "delete from users where id = ?";
    final String selectUsers = "select * from users";
    final String UPDATE = "update users set name = ?, password = ? where id = ?";

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImplement.class);
    GenericRowMapper<User> mapper = new GenericRowMapper<>(User.class);
    @Override
    public boolean create(User user) {
        int resultado = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(INSERT) ){
            pst.setString(1, user.getId());
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getName());
            pst.setString(5, user.getLastname());
            resultado = pst.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
        logger.error("User creation failed.", e);
           throw new ManagerException(UserError.USER_CREATION_FAILED);
        } 
        catch(SQLException e){
            e.printStackTrace();
        } 
        return resultado > 0;
    }
    @Override
    public User get(String id) {
        
        User user = null;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(SELECT)){ 
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("simon");
                user = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            
        }
        return user;
    }
    // Sobre carga para el login
    @Override
    public User get(User user){
        System.out.println(user.getUsername()+ user.getPassword());
        
        User userretornar = null;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(LOGIN)){
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                userretornar = mapper.mapRow(rs);
            }
        } catch (SQLException e){
            logger.error("User not found", e);
            throw new ManagerException(UserError.USER_NOT_FOUND);
        }
        return userretornar;
    }
        // private User convertResultToUser(ResultSet rs){
        //     User user = null;
        //     try {
        //         String id = rs.getString("id");
        //         String username = rs.getString("username");
        //         String password = rs.getString("password");
        //         String nombre = rs.getString("name");
        //         String apellido = rs.getString("lastname");
        //         LocalDate fech =  rs.getDate("creation_date").toLocalDate();
        //         user = new User();
        //         user.setUsername(username);
        //         user.setPassword(password);
        //         user.setId(id);
        //         user.setName(nombre);
        //         user.setLastname(apellido);
        //         user.setCreation_date(fech);
        //     } catch (SQLException ex) {
        //         logger.error("Error processing to the query result", ex);
        //         throw new ManagerException(ConnectionError.ERROR_PROCESSING_TO_THE_QUERY_RESULT);
        //     }
        //     return user;
        // }
    @Override
    public boolean delete(String id) {
        int resultado = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(DELETE)){
            pst.setString(1, id);
            resultado = pst.executeUpdate();
        } catch (SQLException e){
            logger.error("User delete failed", e);
            throw new ManagerException(UserError.USER_DELETE_FAILED);
        } 
        return resultado > 0;            
    }
    @Override
    public List<User> getItems() {
        List<User> lista = new ArrayList<>();
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(selectUsers)){
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = mapper.mapRow(rs);          
                lista.add(user);
            }
        } catch (SQLException e) {
            logger.error("Not users", e);
            throw new ManagerException(UserError.USER_NOT_FOUND);
        }
        return lista;
    }

    @Override
    public boolean update(User user) {
        int resultado = 0;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(UPDATE)){
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getId());
            resultado = pst.executeUpdate();
        } catch (SQLException e) {
            logger.error("User update failed", e);
            throw new ManagerException(UserError.USER_UPDATE_FAILED);
        } 
        return resultado > 0;
    }
}
