package org.omni.bank.auth.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.omni.bank.auth.dto.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
@Slf4j
public class FallBackController implements ErrorController {
    @RequestMapping("/")
    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
        Object statusCodeObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object messageObj = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object pathObj = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        int statusCode = statusCodeObj != null ? Integer.parseInt(statusCodeObj.toString()) : 500;
        String message = messageObj != null ? messageObj.toString() : "Unexpected error";
        String path = pathObj != null ? pathObj.toString() : request.getRequestURI();

        log.error("Unhandled error ({}): {}", statusCode, message);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.valueOf(statusCode))
                .message(message)
                .path(path)
                .error(getErrorType(statusCode))
                .build();

        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    private String getErrorType(int statusCode) {
        return switch (statusCode) {
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            default -> "Internal Server Error";
        };
    }
}
