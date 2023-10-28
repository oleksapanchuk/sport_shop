package dev.oleksa.sportshop.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleCustomException(Exception e) {
        CustomException customException = CustomException.builder()
                .message(e.getMessage())
                .throwable(e)
                .httpStatus(HttpStatus.valueOf(500))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleCustomException(NotFoundException e) {
        CustomException customException = CustomException.builder()
                .message(e.getMessage())
                .throwable(e)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }

}
