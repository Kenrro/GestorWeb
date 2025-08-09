package com.GestorTareas.GestorTareas.dao.idao;



import com.GestorTareas.GestorTareas.model.Permission;
import com.GestorTareas.GestorTareas.model.Work;

public interface WorkParticipationDao {
    public Permission get(Work work, String User);
    public Permission update(Work work, String User, Permission permission);
    public boolean delete(String work_id, String user_id);
    public boolean set(Work work, String user_id, Permission permission);
}
