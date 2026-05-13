package com.alvarohdez.econocom.data_safety;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean checkPassword(String textPassword, String encodedPassword){
        return bCryptPasswordEncoder.matches(textPassword,encodedPassword);
    }

}
