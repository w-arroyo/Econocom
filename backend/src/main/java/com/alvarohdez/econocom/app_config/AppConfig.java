package com.alvarohdez.econocom.app_config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class AppConfig {

    private static String fillerUserEmail;
    private static String fillerUserPassword;
    private static int jwtTokenLength;
    private static String jwtTokenSigningKey;

    @Value("{app.filler_user_email}")
    private void setFillerUsername(String value){
        fillerUserEmail =value;
    }

    @Value("{app.filler_user_password}")
    private void setFillerUserPassword(String value){
        fillerUserPassword=value;
    }

    @Value("${app.jwt_token_signing_key}")
    public void setJwtTokenSigningKey(String value){
        jwtTokenSigningKey=value;
    }

    @Value("${app.jwt_token_length}")
    public void setTokenLength(String value){
        jwtTokenLength=Integer.parseInt(value);
    }

    public static String getFillerUserEmail(){
        return fillerUserEmail;
    }

    public static String getFillerUserPassword(){
        return fillerUserPassword;
    }

    public static Key getJwtTokenSigningKey(){
        return Keys.hmacShaKeyFor(jwtTokenSigningKey.getBytes(StandardCharsets.UTF_8));
    }

    public static int getJwtTokenLength(){
        return jwtTokenLength;
    }

}
