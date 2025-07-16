package com.GestorTareas.GestorTareas.dao.idao;

import java.util.List;

import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.model.Work;

public interface WorkDAO {

    public Boolean create(Work work);
    public Work get(String id);
    public boolean delete(String id);
    public boolean update(Work work);
    public List<Work> getItems(String id);
}
