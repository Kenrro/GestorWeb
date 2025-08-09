/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.dao.idao.TaskDAO;
import com.GestorTareas.GestorTareas.enums.ConnectionError;
import com.GestorTareas.GestorTareas.enums.TaskError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.GenericRowMapper;
import com.GestorTareas.GestorTareas.model.Task;
import com.GestorTareas.GestorTareas.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
        private static final Logger logger = LoggerFactory.getLogger(WorkDAOImplement.class);
        GenericRowMapper<Task> mapper = new GenericRowMapper<>(Task.class);
        // private Task convertResultsetToTarea(ResultSet rs){
        //     Task tarea = new Task();
        //     try {
        //     tarea.setId(rs.getString("id"));
        //     tarea.setId_user(rs.getString("user_id"));
        //     tarea.setDescription(rs.getString("description"));
        //     tarea.setName(rs.getString("name"));
        //     tarea.setCreation_date(rs.getDate("creation_date"));
        //         tarea.setState(rs.getBoolean("state"));
        //     } catch (SQLException ex) {
        //         logger.error("Error processing to the query result", ex);
        //         throw new ManagerException(ConnectionError.ERROR_PROCESSING_TO_THE_QUERY_RESULT);
        //     }
        //     return tarea;
        // }
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
            logger.error("Task creation failed", e);
            throw new ManagerException(TaskError.TASK_CREATION_FAILED);
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
                tarea = mapper.mapRow(rs);
            }
        } catch(SQLException e){
            logger.error("Task not found", e);
            throw new ManagerException(TaskError.TASK_NOT_FOUND);
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
            logger.error("Task delete failed", e);
            throw new ManagerException(TaskError.TASK_DELETE_FAILED);
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
            logger.error("Task update failed", e);
            throw new ManagerException(TaskError.TASK_UPDATE_FAILED);
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
               Task tarea = mapper.mapRow(rs);
               lista.add(tarea);
            }
        } catch(SQLException e){
            logger.error("Not task", e);
            throw new ManagerException(TaskError.TASK_NOT_FOUND);
        }
        return lista;
    }
}
