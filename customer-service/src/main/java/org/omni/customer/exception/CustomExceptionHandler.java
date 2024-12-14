package org.omni.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    // return a response entity with message and data conflict status code,
    // that data is already present
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntryException(DuplicateEntryException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
