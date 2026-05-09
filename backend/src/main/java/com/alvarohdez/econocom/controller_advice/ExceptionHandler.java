package com.alvarohdez.econocom.controller_advice;

import com.alvarohdez.econocom.dto.ExceptionResponse;
import com.alvarohdez.econocom.exceptions.EmptyFieldsException;
import com.alvarohdez.econocom.exceptions.InvalidCredentials;
import com.alvarohdez.econocom.factories.ExceptionResponseFactory;
import com.alvarohdez.econocom.utils.generators.RequestResponseGenerator;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandler {

    private final ExceptionResponseFactory exceptionResponseFactory;

    public ExceptionHandler(ExceptionResponseFactory exceptionResponseFactory) {
        this.exceptionResponseFactory = exceptionResponseFactory;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyFieldsException.class)
    public ResponseEntity<ExceptionResponse> emptyFieldsExceptionHandler(EmptyFieldsException exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createEmptyFieldsResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(RuntimeException exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnexpectedExceptionResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentExceptionHandler(IllegalArgumentException exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnauthorizedResponse(request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ExceptionResponse> invalidExceptionHandler(InvalidCredentials exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createInvalidCredentialsExceptionResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponse> invalidJwtTokenExceptionHandler(JwtException exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnauthorizedResponse(request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> unidentifiedExceptionHandler(Exception exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnexpectedExceptionResponse(request.getRequestURI()));
    }

}
