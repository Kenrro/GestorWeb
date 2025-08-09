package com.GestorTareas.GestorTareas.enums;

import org.springframework.http.HttpStatus;

public enum WorkError implements IError {

    WORK_NOT_FOUND(HttpStatus.NOT_FOUND, "Work not found"),
    WORK_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create work"),
    WORK_WITH_THE_SAME_NAME(HttpStatus.CONFLICT, "You have a work with the same name"),
    WORK_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete work"),
    WORK_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update work");


    HttpStatus httpStatus;
    String message;
    
    WorkError(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    } 
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public String getMessage() {
        return message;
    }
}
