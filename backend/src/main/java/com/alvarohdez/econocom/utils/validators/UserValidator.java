package com.alvarohdez.econocom.utils.validators;

import com.alvarohdez.econocom.dto.UserDTO;
import com.alvarohdez.econocom.exceptions.EmptyFieldsException;
import org.springframework.stereotype.Component;

@Component
public final class UserValidator {

    public static void checkForEmptyFields(UserDTO userDTO){
        if(userDTO==null ||
        GlobalValidator.isNullOrEmpty(userDTO.getUsername()) ||
        GlobalValidator.isNullOrEmpty(userDTO.getPassword())){
            throw new EmptyFieldsException("Empty fields are not allowed.");
        }
    }

}
