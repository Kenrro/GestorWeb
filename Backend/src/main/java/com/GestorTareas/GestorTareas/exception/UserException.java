package com.GestorTareas.GestorTareas.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.GestorTareas.enums.UserError;

public class UserException extends RuntimeException {
    private HttpStatus status;
    private String description;
    private List<String> reasons;

    public UserException(HttpStatus status, String description, List<String> reasons){
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }
    public UserException(UserError userError){
        this.status = userError.getHttpStatus();
        this.description = userError.getMessage();
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
