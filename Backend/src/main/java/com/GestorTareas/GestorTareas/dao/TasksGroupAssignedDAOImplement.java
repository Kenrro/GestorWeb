package com.GestorTareas.GestorTareas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.GestorTareas.GestorTareas.dao.idao.TasksGroupAssignedDAO;

@Repository
public class TasksGroupAssignedDAOImplement implements TasksGroupAssignedDAO{

    String INSERTASSIGNED = "insert into assigned (user_id, group_id, permission_id) values (?,?,?)";
    String DELETEASSIGNED = "delete from assigned where user_id = ? and group_id = ?";
    String UPDATEASSIGNED = "update assigned set permission_id = ? where user_id = ? and group_id = ?";
    public boolean createAssigned(String groupId, String userId, String permitId){
        boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(INSERTASSIGNED);){
                pst.setString(1, userId);
                pst.setString(2, groupId);
                pst.setString(3, permitId);
                result = pst.executeUpdate() > 0 ? true : false;
            } catch(SQLException e){
                e.printStackTrace();
            }
        return result;
    }
    public boolean deleteAssigned(String groupId, String userId){
        boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(DELETEASSIGNED);){
                pst.setString(1, userId);
                pst.setString(2, groupId);
                result = pst.executeUpdate() > 1 ? true : false;

        } catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public boolean updateAssigned(String groupId, String userId, String permitId){
        boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(UPDATEASSIGNED);){
                pst.setString(1, permitId);
                pst.setString(2, userId);
                pst.setString(3, groupId);
                result = pst.executeUpdate() > 1 ? true : false;

        } catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }


}
