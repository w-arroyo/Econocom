package com.alvarohdez.econocom.utils.validators;

import com.alvarohdez.econocom.dto.LoginRequestDTO;
import com.alvarohdez.econocom.dto.RegistrationRequestDTO;
import com.alvarohdez.econocom.exceptions.EmptyFieldsException;
import org.springframework.stereotype.Component;

@Component
public final class UserValidator {

    public static void checkLoginEmptyFields(LoginRequestDTO loginRequestDTO){
        if(loginRequestDTO ==null){
            throw new EmptyFieldsException("Empty fields are not allowed.");
        }
        GlobalValidator.isNullOrEmpty(loginRequestDTO.getEmail());
        GlobalValidator.isNullOrEmpty(loginRequestDTO.getPassword());
    }

}
