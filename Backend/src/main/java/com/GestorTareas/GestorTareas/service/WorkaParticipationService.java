package com.GestorTareas.GestorTareas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestorTareas.GestorTareas.dao.WorkDAOImplement;
import com.GestorTareas.GestorTareas.dao.WorkParticipationDaoImplement;
import com.GestorTareas.GestorTareas.dao.idao.WorkParticipationDao;
import com.GestorTareas.GestorTareas.dto.PermissionDTO;
import com.GestorTareas.GestorTareas.enums.ParticipateError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.PermissionMapper;
import com.GestorTareas.GestorTareas.model.Permission;
import com.GestorTareas.GestorTareas.model.Work;

@Service
public class WorkaParticipationService {
    WorkParticipationDao dao;
    PermissionMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(WorkDAOImplement.class);
    @Autowired
    WorkaParticipationService(WorkParticipationDaoImplement dao,
                            PermissionMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }
    public PermissionDTO updatePermission(Work work, String user, Permission permission){
        PermissionDTO dto = null;
        Permission result = dao.update(work, user, permission);
        if (result != null) {
            dto = mapper.tDto(result);
        }
        else {
            throw new ManagerException(ParticipateError.ERROR_TO_UPDATE_PARTICIPATION);
        }
        return dto;
    }
    public boolean setParticipation(Work work, String user, Permission permission){
        boolean result = false;
        result = dao.set(work, user, permission);
        if (!result) {
            throw new ManagerException(ParticipateError.ERROR_TO_ASSIGMENT_PARTICIPATION);
        }
        return result;
    }
    public boolean deleteParticipation(String work_id, String user_id){
        boolean result = false;
        result = dao.delete(work_id, user_id);
        if (!result) {
            throw new ManagerException(ParticipateError.ERROR_TO_DELETE_PARTICIPATION);
        }
        return result;
    }
}
