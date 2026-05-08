package com.alvarohdez.econocom.utils.validators;

import org.springframework.stereotype.Component;

@Component
public final class GlobalValidator {

    public static boolean isNullOrEmpty(String string){
        return string==null || string.trim().isEmpty();
    }

}
