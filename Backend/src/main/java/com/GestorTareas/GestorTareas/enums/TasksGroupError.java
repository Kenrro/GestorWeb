package com.GestorTareas.GestorTareas.enums;

import org.springframework.http.HttpStatus;

public enum TasksGroupError implements IError {
    TASKS_GROUP_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Tasksgroup creation failed");

    HttpStatus httpStatus;
    String message;
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    TasksGroupError(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
