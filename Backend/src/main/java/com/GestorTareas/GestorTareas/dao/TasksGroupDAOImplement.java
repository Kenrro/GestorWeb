package com.GestorTareas.GestorTareas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.GestorTareas.GestorTareas.dao.idao.TasksGroupDAO;
import com.GestorTareas.GestorTareas.enums.WorkError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.GenericRowMapper;
import com.GestorTareas.GestorTareas.model.TasksGroup;

@Repository
public class TasksGroupDAOImplement implements TasksGroupDAO {
    String SELECTALLGROUPSFROMAJOB = "select * from tasks_groups where work_id = ?";
    String INSERTNEWTASKSGROUP = "insert into tasks_groups (id, name, visible, restriction, work_id) values (?, ?, ?, ?, ?)";
    String DELETEONEGROUP = "delete from tasks_groups where work_id = ? and id = ?";
    String UPDATEONETASKSGROUP = "update tasks_groups set name = ?, visible = ?, restriction = ? where work_id = ? and id = ?";
    GenericRowMapper<TasksGroup> mapper = new GenericRowMapper<>(TasksGroup.class);
       
    public List<TasksGroup> getAllFromJob(String work_id){
        List<TasksGroup> list = new ArrayList<>();
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(SELECTALLGROUPSFROMAJOB);){
            pst.setString(1, work_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }
        } catch(SQLException e){
                e.printStackTrace();
        }
        return list;
    }
    
    public TasksGroup createTasksGroup (TasksGroup tasksGroup){
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(INSERTNEWTASKSGROUP);){
                pst.setString(1, tasksGroup.getId());
                pst.setString(2, tasksGroup.getName());
                pst.setBoolean(3, tasksGroup.isVisible());
                pst.setBoolean(4, tasksGroup.isRestriction());
                pst.setString(5, tasksGroup.getWork_id());
                int result = pst.executeUpdate();
                if (result < 1) {
                    throw new ManagerException(WorkError.WORK_CREATION_FAILED);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasksGroup;
    }
    public boolean deleteTasksGroup(String taskGroupId, String work_id){
        boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(DELETEONEGROUP);){
                pst.setString(1, work_id);
                pst.setString(2, taskGroupId);
                result = pst.executeUpdate() > 0 ? true : false;
            } catch (SQLException e){
                e.printStackTrace();
            }
        return result;
    }
    public boolean updateTaskGroup(TasksGroup tasksGroup){
        boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(UPDATEONETASKSGROUP);){
                pst.setString(1, tasksGroup.getName());
                pst.setBoolean(2, tasksGroup.isVisible());
                pst.setBoolean(3, tasksGroup.isRestriction());
                pst.setString(4, tasksGroup.getWork_id());
                pst.setString(5, tasksGroup.getId());
                result = pst.executeUpdate() > 0 ? true : false;
            } catch(SQLException e){
                e.printStackTrace();
            }
        return result; 
    }

   
    
        
}
