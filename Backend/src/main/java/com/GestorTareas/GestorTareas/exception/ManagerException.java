package com.GestorTareas.GestorTareas.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.GestorTareas.GestorTareas.enums.IError;

public class ManagerException extends RuntimeException {
    private HttpStatus status;
    private String description;
    private List<String> reasons;

    public ManagerException(HttpStatus status, String description, List<String> reasons){
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }
    public <T extends IError> ManagerException(T creationFailed){
        super(creationFailed.getMessage());
        this.status = creationFailed.getHttpStatus();
        this.description = creationFailed.getMessage();
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
