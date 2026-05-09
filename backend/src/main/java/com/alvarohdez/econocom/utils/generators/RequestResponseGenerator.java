package com.alvarohdez.econocom.utils.generators;

import com.alvarohdez.econocom.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;

public class RequestResponseGenerator {

    public static ResponseEntity<ExceptionResponse> generateExceptionResponse(ExceptionResponse exceptionResponse){
        return ResponseEntity.status(exceptionResponse.getCode()).body(exceptionResponse);
    }

    public static <T> ResponseEntity<T> generateSuccessfulRequestResponse(T body){
        return ResponseEntity.ok(body);
    }

}
