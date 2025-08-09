package com.GestorTareas.GestorTareas.dao.idao;

public interface TasksGroupAssignedDAO {
    public boolean createAssigned(String groupId, String userId, String permitId);
    public boolean deleteAssigned(String groupId, String userId);
    public boolean updateAssigned(String groupId, String userId, String permitId);
}
