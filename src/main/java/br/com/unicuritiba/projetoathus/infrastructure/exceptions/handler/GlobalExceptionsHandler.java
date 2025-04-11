package br.com.unicuritiba.projetoathus.infrastructure.exceptions.handler;

import br.com.unicuritiba.projetoathus.infrastructure.exceptions.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> forbidden(ForbiddenException exception, HttpServletRequest request) {
        return response(exception.getMessage(), request, HttpStatus.FORBIDDEN, LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request, final HttpStatus status, LocalDateTime data) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, data, status.value(), request.getRequestURI()));

    }
}
