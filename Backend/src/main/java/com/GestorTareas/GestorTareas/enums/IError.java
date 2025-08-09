package com.GestorTareas.GestorTareas.enums;

import org.springframework.http.HttpStatus;

public interface IError {
    public HttpStatus getHttpStatus();
    public String getMessage();
}
