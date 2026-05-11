package com.alvarohdez.econocom.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
@ConfigurationProperties(prefix="app") // only way to make spring initialize this class befaure injection
public class AppConfig {

    private static String fillerUserEmail;
    private static String fillerUserPassword;
    private static int jwtTokenLength;
    private static String jwtTokenSigningKey;
    private static int jwtTokenBearerPrefixLength;

    @Value("${app.filler_user_email}")
    private void setFillerUsername(String value){
        fillerUserEmail =value;
    }

    @Value("${app.filler_user_password}")
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

    @Value("${app.jwt_token_bearer_prefix_length}")
    public void setJwtTokenBearerPrefixLength(String value){
        jwtTokenBearerPrefixLength=Integer.parseInt(value);
    }

    public static String getFillerUserEmail(){
        return fillerUserEmail;
    }

    public static String getFillerUserPassword(){
        return fillerUserPassword;
    }

    public static SecretKey getJwtTokenSigningKey(){
        return Keys.hmacShaKeyFor(jwtTokenSigningKey.getBytes(StandardCharsets.UTF_8));
    }

    public static int getJwtTokenLength(){
        return jwtTokenLength;
    }

    public static int getJwtTokenBearerPrefixLength(){
        return jwtTokenBearerPrefixLength;
    }

}
