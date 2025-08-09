package com.GestorTareas.GestorTareas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestorTareas.GestorTareas.dao.TasksGroupDAOImplement;
import com.GestorTareas.GestorTareas.dao.idao.TasksGroupDAO;
import com.GestorTareas.GestorTareas.dto.TasksGroupDTO;
import com.GestorTareas.GestorTareas.mapper.TasksGroupMapper;
import com.GestorTareas.GestorTareas.mapper.TasksGroupMapperImpl;
import com.GestorTareas.GestorTareas.model.TasksGroup;

@Service
public class TasksGroupService {
    TasksGroupDAO dao;
    TasksGroupMapper mapper;
    @Autowired
    public TasksGroupService(TasksGroupDAOImplement dao, TasksGroupMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }
    public List<TasksGroupDTO> getAllFromJob(String work_id){
        List<TasksGroupDTO> list = new ArrayList<>();
        list = dao.getAllFromJob(work_id).stream().map(taskGroup -> mapper.toDto(taskGroup)).collect(Collectors.toList());
        return list;
    }
    public TasksGroupDTO createTasksGroup(TasksGroup taskGroup){
        TasksGroupDTO dto = null;
        taskGroup.setId();
        TasksGroup entity = dao.createTasksGroup(taskGroup);
        dto = mapper.toDto(entity);
        return dto;
    }
    public boolean deleteTasksGroup(String work_id, String tasksGroupId){
        boolean result = dao.deleteTasksGroup(tasksGroupId, work_id);
        return result;
    }
    public boolean updateTasksGroup(TasksGroup tasksGroup){
        boolean result = dao.updateTaskGroup(tasksGroup);
        return result;
    }
}
