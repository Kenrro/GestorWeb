package com.GestorTareas.GestorTareas.dao.idao;

import java.util.List;

import com.GestorTareas.GestorTareas.model.TasksGroup;

public interface TasksGroupDAO {
    public List<TasksGroup> getAllFromJob(String work_id);
    public TasksGroup createTasksGroup(TasksGroup tasksGroup);
    public boolean deleteTasksGroup(String taskGroupId, String work_id);
    public boolean updateTaskGroup(TasksGroup tasksGroup);
}
