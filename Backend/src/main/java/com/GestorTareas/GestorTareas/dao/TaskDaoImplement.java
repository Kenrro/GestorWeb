/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

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
public class TaskDaoImplement implements TareaDao {

        final String INSERT = "insert into tareas (id, usuario_id, nombre, descripcion) values (?, ?, ?, ?)";
        final String SELECT = "select * from tareas where id = ?";
        final String DELETE = "delete from tareas where id = ?";
        final String UPDATE = "update tareas set nombre = ?, descripcion = ?, completada = ? where id = ?";
        final String SELECTLIST = "select * from tareas where usuario_id = ?";
    
        private Task convertResultsetToTarea(ResultSet rs){
            Task tarea = new Task();
            try {
            tarea.setId(rs.getString("id"));
            tarea.setId_user(rs.getString("usuario_id"));
            tarea.setDescription(rs.getString("descripcion"));
            tarea.setName(rs.getString("nombre"));
            tarea.setCreation_date(rs.getDate("fech-creacion"));
                tarea.setState(rs.getBoolean("completada"));
            } catch (SQLException ex) {
                Logger.getLogger(TaskDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return tarea;
        }
    @Override
    public boolean createTarea(Task tarea) {
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
    public Task getTarea(String id) {
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
    public boolean deleteTarea(String id) {
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
    public boolean updateTarea(Task tarea) {
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
    public List<Task> getTareas(String id) {
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
