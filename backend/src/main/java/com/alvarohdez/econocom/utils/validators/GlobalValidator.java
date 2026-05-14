package com.alvarohdez.econocom.utils.validators;

import com.alvarohdez.econocom.exceptions.EmptyFieldsException;
import org.springframework.stereotype.Component;

@Component
public final class GlobalValidator {

    public static void isNullOrEmpty(String string){
        if(string==null || string.trim().isEmpty()){
            throw new EmptyFieldsException("Empty fields are not allowed");
        }
    }

}
