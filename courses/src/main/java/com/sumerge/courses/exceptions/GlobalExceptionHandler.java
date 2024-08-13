package com.sumerge.courses.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotAuthorOfCourseException.class)
    public ResponseEntity<Object> handleNotAuthorOfCourseException(Exception ex)
    {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex)
    {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
