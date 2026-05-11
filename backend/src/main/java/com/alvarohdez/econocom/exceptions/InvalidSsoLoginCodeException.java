package com.alvarohdez.econocom.exceptions;

public class InvalidSsoLoginCodeException extends RuntimeException {
    public InvalidSsoLoginCodeException(String message) {
        super(message);
    }
}
