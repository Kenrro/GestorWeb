package com.GestorTareas.GestorTareas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestorTareas.GestorTareas.dao.TasksGroupAssignedDAOImplement;
import com.GestorTareas.GestorTareas.dao.idao.TasksGroupAssignedDAO;

@Service
public class TasksGroupAssignedService {
    TasksGroupAssignedDAO dao;
    @Autowired
    TasksGroupAssignedService(TasksGroupAssignedDAOImplement dao){
        this.dao = dao;
    }
    public boolean createAssigned(String userId, String groupId, String permitId){
        boolean result;
        result = dao.createAssigned(groupId, userId, permitId);
        return result;
    }
    public boolean updateAssigned(String userId, String groupId, String permitId){
        boolean result;
        result = dao.updateAssigned(groupId, userId, permitId);
        return result;
    }
    public boolean deleteAssigned(String groupId, String userId){
        boolean result;
        result = dao.deleteAssigned(groupId, userId);
        return result;
    }
}
