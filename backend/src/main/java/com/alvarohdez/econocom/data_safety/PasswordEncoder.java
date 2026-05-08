package com.alvarohdez.econocom.data_safety;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean checkPassword(String textPassword, String encodedPassword){
        return bCryptPasswordEncoder.matches(textPassword,encodedPassword);
    }

}
