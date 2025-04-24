package br.com.unicuritiba.projetoathus.infrastructure.exceptions.handler;

import br.com.unicuritiba.projetoathus.infrastructure.exceptions.ForbiddenException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnauthorizedException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> forbidden(ForbiddenException exception, HttpServletRequest request) {
        return response(exception.getMessage(), request, HttpStatus.FORBIDDEN, LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorized(UnauthorizedException exception, HttpServletRequest request) {
        return response(exception.getMessage(), request, HttpStatus.UNAUTHORIZED, LocalDateTime.now());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> unauthorized(UnprocessableEntityException exception, HttpServletRequest request) {
        return response(exception.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
    }


    private ResponseEntity<ErrorResponse> response( String message,  HttpServletRequest request,  HttpStatus status, LocalDateTime data) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, data, status.value(), request.getRequestURI()));


    }
}

