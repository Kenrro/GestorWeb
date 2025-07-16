/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.dao.idao.TaskDAO;
import com.GestorTareas.GestorTareas.model.Task;
import java.sql.Connection;
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
public class TaskDaoImplement implements TaskDAO {

        final String INSERT = "insert into task (id, user_id, name, description) values (?, ?, ?, ?)";
        final String SELECT = "select * from task where id = ?";
        final String DELETE = "delete from task where id = ?";
        final String UPDATE = "update task set name = ?, description = ?, state = ? where id = ?";
        final String SELECTLIST = "select * from task where user_id = ?";
    
        private Task convertResultsetToTarea(ResultSet rs){
            Task tarea = new Task();
            try {
            tarea.setId(rs.getString("id"));
            tarea.setId_user(rs.getString("user_id"));
            tarea.setDescription(rs.getString("description"));
            tarea.setName(rs.getString("name"));
            tarea.setCreation_date(rs.getDate("creation_date"));
                tarea.setState(rs.getBoolean("state"));
            } catch (SQLException ex) {
                Logger.getLogger(TaskDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return tarea;
        }
    @Override
    public boolean create(Task tarea) {
        int resultado = 0;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(INSERT)){
            pst.setString(1, tarea.getId());
            pst.setString(2, tarea.getId_user());
            pst.setString(3, tarea.getName());
            pst.setString(4, tarea.getDescription());
            resultado = pst.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;
    }
    @Override
    public Task get(String id) {
        Task tarea = null;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(SELECT)){
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tarea = convertResultsetToTarea(rs);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return tarea;
    }
    @Override
    public boolean delete(String id) {
        int rs = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(DELETE)){
            pst.setString(1, id);
            rs = pst.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rs > 0;
    }
    @Override
    public boolean update(Task tarea) {
        int rs = 0;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(UPDATE)){
            pst.setString(1, tarea.getName());
            pst.setString(2, tarea.getDescription());
            pst.setBoolean(3, tarea.isState());
            pst.setString(4, tarea.getId());
            rs = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rs > 0;
    }
    @Override
    public List<Task> getItems(String id) {
        List<Task> lista = new ArrayList<>();
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(SELECTLIST)){
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               Task tarea = convertResultsetToTarea(rs);
               lista.add(tarea);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return lista;
    }
}
