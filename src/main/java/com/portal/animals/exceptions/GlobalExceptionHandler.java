package com.portal.animals.exceptions;

import com.portal.animals.constants.ErrorMessage;
import com.portal.animals.presenters.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.portal.animals.constants.ErrorMessage.ANIMAL_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String code = ex.getFieldError().getDefaultMessage();
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(code)
                .description(ErrorMessage.getMessage(code).getDescription())
                .build();
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(AnimalNotFound.class)
    public ResponseEntity<ExceptionResponse> handleValidationError(AnimalNotFound animalException) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(ANIMAL_NOT_FOUND.getCode())
                .description(ANIMAL_NOT_FOUND.getDescription())
                .build();
        return ResponseEntity.status(NOT_FOUND).body(exceptionResponse);
    }
}
