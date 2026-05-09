package com.alvarohdez.econocom.handlers;

import com.alvarohdez.econocom.app_config.AppConfig;
import com.alvarohdez.econocom.factories.JWTTokenFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenHandler {

    private final JWTTokenFactory jwtTokenFactory;

    public JwtTokenHandler(JWTTokenFactory jwtTokenFactory) {
        this.jwtTokenFactory = jwtTokenFactory;
    }

    private Claims extractClaims(String jwtToken){
        return generateJwtParser()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private JwtParser generateJwtParser(){
        return Jwts.parserBuilder()
                .setSigningKey(AppConfig.getJwtTokenSigningKey())
                .build();
    }

    public String getUserIdFromJwtToken(String jwtToken){
        return extractClaims(jwtToken).getSubject();
    }

    private boolean checkIfJwtTokenIsStillValid(String jwtToken){
        return extractClaims(jwtToken).getExpiration()
                .after(new Date());
    }

}
