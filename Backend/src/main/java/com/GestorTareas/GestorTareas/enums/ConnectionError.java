package com.GestorTareas.GestorTareas.enums;

import org.springframework.http.HttpStatus;

public enum ConnectionError implements IError {
    ERROR_CONNECTING_TO_THE_DATABASE(HttpStatus.INTERNAL_SERVER_ERROR, "Error connecting to the database."),
    ERROR_PROCESSING_TO_THE_QUERY_RESULT(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing to the query result.");
    
    private String message;
    private HttpStatus httpStatus;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    ConnectionError(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
    
}
