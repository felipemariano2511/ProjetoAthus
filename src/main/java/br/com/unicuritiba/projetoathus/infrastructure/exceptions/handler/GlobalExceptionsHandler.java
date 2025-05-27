package br.com.unicuritiba.projetoathus.infrastructure.exceptions.handler;

import br.com.unicuritiba.projetoathus.infrastructure.exceptions.*;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableEntity(UnprocessableEntityException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY, request.getRequestURI());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidenException(ForbiddenException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.FORBIDDEN, request.getRequestURI());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED, request.getRequestURI());
    }

    @ExceptionHandler(BuisnessException.class)
    public ResponseEntity<ErrorResponse> handleBuisnessException(BuisnessException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.UNPROCESSABLE_ENTITY, request.getRequestURI());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ErrorResponse> handleNoContentException(NoContentException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NO_CONTENT, request.getRequestURI());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT, request.getRequestURI());
    }

    @ExceptionHandler(CreatedException.class)
    public ResponseEntity<ErrorResponse> handleCreatedException(CreatedException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.CREATED, request.getRequestURI());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", "Token expirado.");
        body.put("path", "/auth/refresh");

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status, String path) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path
        );
        return ResponseEntity.status(status).body(response);
    }
}
