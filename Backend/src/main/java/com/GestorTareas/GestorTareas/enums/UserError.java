package com.GestorTareas.GestorTareas.enums;

import org.springframework.http.HttpStatus;

public enum UserError implements IError {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    USER_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to created user"),
    USER_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "Invalid login credentials"),
    USER_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete user"),
    USER_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user");


    private final HttpStatus httpStatus;
    private final String message;
    UserError(HttpStatus httpStatus, String message){
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
