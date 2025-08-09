package com.GestorTareas.GestorTareas.enums;

import org.springframework.http.HttpStatus;

public enum TaskError implements IError {

    TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "Task not found"),
    TASK_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Task creation failed"),
    TASK_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Task update failed"),
    TASK_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Task delete failed"),
    TASKS_NOT_FOUND(HttpStatus.NOT_FOUND, "Tasks not found");

    private final HttpStatus httpStatus;
    private final String message;
    TaskError(HttpStatus httpStatus, String message){
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
