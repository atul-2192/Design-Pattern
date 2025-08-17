package com.Chronicles.PaymentService.Exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotSupportedException.class)
    public ResponseEntity<ApiError> handleNotSupported(NotSupportedException ex, HttpServletRequest req) {
        log.warn("Not supported: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ApiError(Instant.now(), 422, "UNPROCESSABLE_ENTITY", ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ApiError> handleBadRequest(Exception ex, HttpServletRequest req) {
        String msg = ex.getMessage();
        log.warn("Bad request: {}", msg);
        return ResponseEntity.badRequest()
                .body(new ApiError(Instant.now(), 400, "BAD_REQUEST", msg, req.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest req) {
        log.error("Unhandled error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(Instant.now(), 500, "INTERNAL_SERVER_ERROR", "Something went wrong", req.getRequestURI()));
    }
}