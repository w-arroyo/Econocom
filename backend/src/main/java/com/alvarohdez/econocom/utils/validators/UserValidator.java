package com.alvarohdez.econocom.utils.validators;

import com.alvarohdez.econocom.dto.LoginRequestDTO;
import com.alvarohdez.econocom.exceptions.EmptyFieldsException;
import org.springframework.stereotype.Component;

@Component
public final class UserValidator {

    public static void checkForEmptyFields(LoginRequestDTO loginRequestDTO){
        if(loginRequestDTO ==null ||
        GlobalValidator.isNullOrEmpty(loginRequestDTO.getEmail()) ||
        GlobalValidator.isNullOrEmpty(loginRequestDTO.getPassword())){
            throw new EmptyFieldsException("Empty fields are not allowed.");
        }
    }

}
