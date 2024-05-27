package uz.pdp.g34springbootsecurity.exception.handler;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.g34springbootsecurity.dto.web.ErrorResponse;
import uz.pdp.g34springbootsecurity.exception.UserNotFoundException;
import uz.pdp.g34springbootsecurity.util.ErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .errorMessage(e.getMessage())
                        .errorCode(ErrorCode.INTERNAL_ERROR.code())
                        .path(request.getRequestURI())
                        .build()
                );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .errorMessage(e.getMessage())
                        .errorCode(ErrorCode.DATA_NOT_FOUND.code())
                        .path(request.getRequestURI())
                        .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.builder()
                        .errorMessage(e.getMessage())
                        .errorCode(ErrorCode.UNAUTHORIZED.code())
                        .path(request.getRequestURI())
                        .build());
    }
}
