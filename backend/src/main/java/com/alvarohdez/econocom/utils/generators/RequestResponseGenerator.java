package com.alvarohdez.econocom.utils.generators;

import com.alvarohdez.econocom.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public final class RequestResponseGenerator {

    private RequestResponseGenerator(){

    }

    public static ResponseEntity<ExceptionResponse> generateExceptionResponse(ExceptionResponse exceptionResponse){
        return ResponseEntity.status(exceptionResponse.getCode()).body(exceptionResponse);
    }

    public static <T> ResponseEntity<T> generateSuccessfulRequestResponse(T body){
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<Void> generateSsoLoginRequestResponse(String url){
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

}
