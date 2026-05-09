package com.alvarohdez.econocom.exceptions;

public class FraudulentRequestException extends RuntimeException {
    public FraudulentRequestException(String message) {
        super(message);
    }
}
