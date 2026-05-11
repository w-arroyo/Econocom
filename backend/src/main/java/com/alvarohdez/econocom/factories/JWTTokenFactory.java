package com.alvarohdez.econocom.factories;

import com.alvarohdez.econocom.config.AppConfig;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public final class JWTTokenFactory {

    public String generateJwtToken(String userEmail){
        return Jwts.builder()
                .signWith(AppConfig.getJwtTokenSigningKey())
                .expiration(generateTokenExpirationDate())
                .issuedAt(new Date())
                .subject(userEmail)
                .compact();
    }

    private Date generateTokenExpirationDate(){
        return new Date(System.currentTimeMillis()+AppConfig.getJwtTokenLength());
    }

}
