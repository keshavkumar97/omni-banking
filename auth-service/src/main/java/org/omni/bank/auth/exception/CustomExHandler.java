package org.omni.bank.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.omni.bank.auth.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExHandler {
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<ProblemDetail> handleDuplicateEntry(DuplicateEntryException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ex.getBody());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException iaEx) {
        log.error("{} : {}", HttpStatus.BAD_REQUEST, iaEx.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(iaEx.getMessage())
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException dIVEx) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message(dIVEx.getMessage())
                .build();
        log.error("{} : {}", HttpStatus.BAD_REQUEST, dIVEx.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialException(BadCredentialsException bcEx) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.UNAUTHORIZED)
                .message(bcEx.getMessage())
                .build();
        log.error(HttpStatus.UNAUTHORIZED + " : " + bcEx.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
