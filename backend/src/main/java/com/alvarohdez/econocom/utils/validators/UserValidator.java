package com.alvarohdez.econocom.utils.validators;

import com.alvarohdez.econocom.dto.LoginRequestDTO;
import com.alvarohdez.econocom.dto.RegistrationRequestDTO;
import com.alvarohdez.econocom.exceptions.EmptyFieldsException;
import org.springframework.stereotype.Component;

@Component
public final class UserValidator {

    public static void checkLoginEmptyFields(LoginRequestDTO loginRequestDTO){
        if(loginRequestDTO ==null ||
        GlobalValidator.isNullOrEmpty(loginRequestDTO.getEmail()) ||
        GlobalValidator.isNullOrEmpty(loginRequestDTO.getPassword())){
            throw new EmptyFieldsException("Empty fields are not allowed.");
        }
    }

    // even tho it's the same logic i use a different method for future scalability
    public static void checkRegistrationEmptyFields(RegistrationRequestDTO registrationRequestDTO){
        if(registrationRequestDTO ==null ||
                GlobalValidator.isNullOrEmpty(registrationRequestDTO.getEmail()) ||
                GlobalValidator.isNullOrEmpty(registrationRequestDTO.getPassword())){
            throw new EmptyFieldsException("Empty fields are not allowed.");
        }
    }

}
