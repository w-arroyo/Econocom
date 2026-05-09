package com.alvarohdez.econocom.handlers;

import com.alvarohdez.econocom.app_config.AppConfig;
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

    public JwtTokenHandler(JWTTokenFactory jwtTokenFactory) {
        this.jwtTokenFactory = jwtTokenFactory;
    }

    public String generateJwtToken(String userEmail){
        return jwtTokenFactory.generateJwtToken(userEmail);
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

    public String getUserEmailFromJwtToken(String jwtToken){
        return extractClaims(jwtToken).getSubject();
    }

    public boolean checkIfJwtTokenIsStillValid(String jwtToken){
        return extractClaims(jwtToken).getExpiration()
                .after(new Date());
    }

    public String getTokenFromRequest(HttpServletRequest httpServletRequest){
        String header= httpServletRequest.getHeader("Authorization");
        if(header==null || header.trim().isEmpty() || !header.startsWith("Bearer ")){
            return null;
        }
        return header.substring(7); // i only keep the token and remove the 'Bearer ' from the full header
    }

}
