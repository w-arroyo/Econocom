package com.alvarohdez.econocom.factories;

import com.alvarohdez.econocom.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public final class ExceptionResponseFactory {

    private ExceptionResponse generateExceptionResponse(String message, int code, String path){
        return new ExceptionResponse(
                message,
                code,
                path
        );
    }

    public ExceptionResponse createEmptyFieldsResponse(String path){
        return generateExceptionResponse(
                "Empty required fields are not allowed.",
                HttpStatus.BAD_REQUEST.value(),
                path
        );
    }

    public ExceptionResponse createUnauthorizedResponse(String path){
        return generateExceptionResponse(
                "Access not allowed.",
                HttpStatus.UNAUTHORIZED.value(),
                path
        );
    }

    public ExceptionResponse createInvalidCredentialsExceptionResponse(String path){
        return generateExceptionResponse(
                "Invalid credentials.",
                HttpStatus.BAD_REQUEST.value(),
                path
        );
    }

    public ExceptionResponse createUnexpectedExceptionResponse(String path){
        return generateExceptionResponse(
                "There was an unexpected error handling your request.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                path
        );
    }

}
