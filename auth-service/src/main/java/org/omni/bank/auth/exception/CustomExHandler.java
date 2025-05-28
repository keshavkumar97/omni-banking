package org.omni.bank.auth.exception;

import org.omni.bank.auth.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExHandler {
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<ProblemDetail> handleDuplicateEntry(DuplicateEntryException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ex.getBody());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<org.omni.bank.auth.dto.ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException dIVEx) {
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        dIVEx.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
