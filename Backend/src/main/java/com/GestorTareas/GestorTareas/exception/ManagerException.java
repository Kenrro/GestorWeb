package com.GestorTareas.GestorTareas.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.GestorTareas.GestorTareas.enums.TaskError;
import com.GestorTareas.GestorTareas.enums.UserError;

public class ManagerException extends RuntimeException {
    private HttpStatus status;
    private String description;
    private List<String> reasons;

    public ManagerException(HttpStatus status, String description, List<String> reasons){
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }
    public ManagerException(TaskError taskCreationFailed){
        this.status = taskCreationFailed.getHttpStatus();
        this.description = taskCreationFailed.getMessage();
    }
    public ManagerException(UserError userCreationFailed){
        this.status = userCreationFailed.getHttpStatus();
        this.description = userCreationFailed.getMessage();
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getReasons() {
        return reasons;
    }
    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }
}
