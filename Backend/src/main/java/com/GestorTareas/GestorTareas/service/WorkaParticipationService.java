package com.GestorTareas.GestorTareas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestorTareas.GestorTareas.dao.WorkDAOImplement;
import com.GestorTareas.GestorTareas.dao.WorkParticipationDaoImplement;
import com.GestorTareas.GestorTareas.dao.idao.WorkParticipationDao;
import com.GestorTareas.GestorTareas.enums.ParticipateError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.model.Permission;
import com.GestorTareas.GestorTareas.model.Work;

@Service
public class WorkaParticipationService {
    WorkParticipationDao dao;
    private static final Logger logger = LoggerFactory.getLogger(WorkDAOImplement.class);
    @Autowired
    WorkaParticipationService(WorkParticipationDaoImplement dao){
        this.dao = dao;
    }
    public Permission updatePermission(Work work, String user, Permission permission){
        Permission result = dao.update(work, user, permission);
        if (result == null) {
            throw new ManagerException(ParticipateError.ERROR_TO_UPDATE_PARTICIPATION);
        }
        return result;
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
