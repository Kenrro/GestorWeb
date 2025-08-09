package com.GestorTareas.GestorTareas.dao.idao;

import java.util.List;

import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.model.Work;

public interface WorkDAO {

    public Boolean create(Work work);
    public Work get(Work work);
    public List<Work> get(String work);
    public List<UserDTO> getParticipation(String work_id);
    public boolean delete(Work Work);
    public boolean update(Work work);
    public List<Work> getItems(String id);
}
