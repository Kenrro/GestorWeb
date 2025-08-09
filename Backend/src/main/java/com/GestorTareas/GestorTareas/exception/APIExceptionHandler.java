package com.GestorTareas.GestorTareas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.GestorTareas.GestorTareas.dto.ErrorDTO;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ManagerException.class)
    public ResponseEntity<ErrorDTO> exception(ManagerException e, WebRequest request){
        return ResponseEntity.status(e.getStatus())
            .body(new ErrorDTO(e.getDescription(), e.getReasons()));
    }
}
