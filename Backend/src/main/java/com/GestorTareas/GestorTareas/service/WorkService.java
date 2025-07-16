package com.GestorTareas.GestorTareas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestorTareas.GestorTareas.dao.WorkDAOImplement;
import com.GestorTareas.GestorTareas.dao.idao.WorkDAO;
import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.mapper.WorkMapper;
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
        }
        else{
            return null;
        }
        return dto;
    }
}
