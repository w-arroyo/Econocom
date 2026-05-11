package com.alvarohdez.econocom.controller_advice;

import com.alvarohdez.econocom.dto.ExceptionResponse;
import com.alvarohdez.econocom.exceptions.*;
import com.alvarohdez.econocom.factories.ExceptionResponseFactory;
import com.alvarohdez.econocom.utils.generators.RequestResponseGenerator;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
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
        log.error("Exception: ",exception);
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnexpectedExceptionResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentExceptionHandler(IllegalArgumentException exception, HttpServletRequest request){
        log.error("Exception: ",exception);
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnauthorizedResponse(request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidCredentialsExceptionHandler(InvalidCredentialsException exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createInvalidCredentialsExceptionResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> authenticationExceptionHandler(AuthenticationException exception, HttpServletRequest request){
        log.error("Exception: ",exception);
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createInvalidCredentialsExceptionResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedRequestException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedExceptionHandler(UnauthorizedRequestException exception, HttpServletRequest request){

        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnauthorizedResponse(request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ExceptionResponse> userDoesNotExistExceptionHandler(UserDoesNotExistException exception, HttpServletRequest request){

        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUserDoesNotExistExceptionResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FraudulentRequestException.class)
    public ResponseEntity<ExceptionResponse> fraudulentExceptionHandler(FraudulentRequestException exception, HttpServletRequest request){

        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createForbiddenResponse(request.getRequestURI())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponse> invalidJwtTokenExceptionHandler(JwtException exception, HttpServletRequest request){
        log.error("Exception: ",exception);
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnauthorizedResponse(request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> unidentifiedExceptionHandler(Exception exception, HttpServletRequest request){
        log.error("Exception: ",exception);
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createUnexpectedExceptionResponse(request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ExceptionResponse> emailAlreadyInUseExceptionHandler(EmailAlreadyInUseException exception, HttpServletRequest request){
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createEmailAlreadyInUseExceptionResponse(request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidSsoLoginCodeException.class)
    public ResponseEntity<ExceptionResponse> invalidSsoLoginCodeExceptionHandler(InvalidSsoLoginCodeException exception, HttpServletRequest request){
        log.error("Exception: ",exception);
        return RequestResponseGenerator.generateExceptionResponse(
                exceptionResponseFactory.createInvalidSsoLoginCodeExceptionResponse(request.getRequestURI())
        );
    }

}
