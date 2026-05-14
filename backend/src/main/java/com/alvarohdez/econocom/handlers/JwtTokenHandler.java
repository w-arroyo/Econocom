package com.alvarohdez.econocom.handlers;

import com.alvarohdez.econocom.config.AppConfig;
import com.alvarohdez.econocom.factories.JWTTokenFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenHandler {

    private final JWTTokenFactory jwtTokenFactory;

    private final String HEADER="Authorization";
    private final String BEARER_PREFIX="Bearer ";

    public JwtTokenHandler(JWTTokenFactory jwtTokenFactory) {
        this.jwtTokenFactory = jwtTokenFactory;
    }

    public String generateJwtToken(String userEmail){
        return jwtTokenFactory.generateJwtToken(userEmail);
    }

    private Claims extractClaims(String jwtToken){
        return generateJwtParser()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private JwtParser generateJwtParser(){
        return Jwts.parser()
                .verifyWith(AppConfig.getJwtTokenSigningKey())
                .build();
    }

    public String getUserEmailFromJwtToken(String jwtToken){
        return extractClaims(jwtToken).getSubject();
    }

    public boolean checkIfJwtTokenIsStillValid(String jwtToken){
        return extractClaims(jwtToken).getExpiration()
                .after(new Date());
    }

    public String getTokenFromRequest(HttpServletRequest httpServletRequest){
        String header= httpServletRequest.getHeader(this.HEADER);
        if(header==null || header.trim().isEmpty() || !header.startsWith(this.BEARER_PREFIX)){
            return null;
        }
        return header.substring(AppConfig.getJwtTokenBearerPrefixLength()); // i only keep the token and remove the 'Bearer ' from the full header
    }

}
