package com.GestorTareas.GestorTareas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestorTareas.GestorTareas.dao.WorkDAOImplement;
import com.GestorTareas.GestorTareas.dao.idao.WorkDAO;
import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.enums.WorkError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.WorkMapper;
import com.GestorTareas.GestorTareas.model.Permission;
import com.GestorTareas.GestorTareas.model.Work;

@Service
public class WorkService {
    WorkDAO dao;
    WorkMapper mapper;
    @Autowired
    public WorkService(WorkDAOImplement dao,
                        WorkMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }

    public WorkDTO createWork(Work work){
        WorkDTO dto = null;
        work.setId();
        if (dao.create(work)) {
            dto = mapper.toDTO(work);
            dto.setPermits(Permission.ADMINISTRATION);
        }
        else{
            throw new ManagerException(WorkError.WORK_CREATION_FAILED);
        }
        return dto;
    }
    public WorkDTO getWork(Work work){
        WorkDTO dto = null;
        Work entity = dao.get(work);
        if (entity != null) {
            dto = mapper.toDTO(entity);
        }
        return dto;
    }
    public List<WorkDTO> getWorks(String id){
        List<WorkDTO> dto = new ArrayList<>();
        List<Work> entities = dao.get(id);
        if (entities != null) {
            entities
            .stream()
            .forEach(entity -> dto.add(mapper.toDTO(entity)));
        }
        return dto;
    }
    public List<UserDTO> getParticipation(String work_id){
        List<UserDTO> list;
        list = dao.getParticipation(work_id);
        return list;
    }
    public boolean deleteWork(Work work){
        boolean result = false;
        result = dao.delete(work);
        return result;
    }
    public boolean updateWork(Work work){
        boolean result = false;
        result = dao.update(work);
        return result;
    }
}
