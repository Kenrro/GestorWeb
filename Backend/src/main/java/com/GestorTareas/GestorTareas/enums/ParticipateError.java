package com.GestorTareas.GestorTareas.enums;

import org.springframework.http.HttpStatus;

public enum ParticipateError implements IError {
    ERROR_TO_ASSIGMENT_PARTICIPATION(HttpStatus.INTERNAL_SERVER_ERROR, "Error to assigment participation"),
    ERROR_TO_DELETE_PARTICIPATION(HttpStatus.INTERNAL_SERVER_ERROR, "Error to delete participation"),
    ERROR_TO_UPDATE_PARTICIPATION(HttpStatus.INTERNAL_SERVER_ERROR, "Error to update participation");
    HttpStatus httpStatus;
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    String message;
    public String getMessage() {
        return message;
    }
    ParticipateError(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
