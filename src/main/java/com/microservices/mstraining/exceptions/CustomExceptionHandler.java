package com.microservices.mstraining.exceptions;

import com.microservices.mstraining.model.error.ErrorResponse;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        // To Get one error message
        // String errorMessage = ex.getFieldErrors().stream().findFirst().get().getDefaultMessage();

        // To get all error messages;
        List<String> errorMessages = ex.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        ErrorResponse errorResponse = ErrorResponse.builder().code(status.value()).timestamp(LocalDateTime.now())
                .messages(errorMessages)
                .details(request.getDescription(false)).build();
        // To get more
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
