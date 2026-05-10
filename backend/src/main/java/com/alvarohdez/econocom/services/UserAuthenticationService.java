package com.alvarohdez.econocom.services;

import com.alvarohdez.econocom.exceptions.FraudulentRequestException;
import com.alvarohdez.econocom.exceptions.UnauthorizedRequestException;
import com.alvarohdez.econocom.handlers.JwtTokenHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    private final JwtTokenHandler jwtTokenHandler;
    private final AuthenticationManager authenticationManager;

    public UserAuthenticationService(JwtTokenHandler jwtTokenHandler, @Lazy AuthenticationManager authenticationManager) {
        this.jwtTokenHandler = jwtTokenHandler;
        this.authenticationManager = authenticationManager;
    }

    public String login(String email, String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password));
        return jwtTokenHandler.generateJwtToken(email);
    }

    public String getAuthenticatedUserId() {
        checkIfAUserIsLoggedIn();
        return SecurityContextHolder.getContext().getAuthentication().getName(); // gets the email of the logged user
    }

    public void checkIfAUserIsLoggedIn(){
        final Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedRequestException("User is not logged in.");
        }
    }

    public void checkIfARequestIsFraudulent(String requestUserId){
        if(!requestUserId.equals(getAuthenticatedUserId())) {
            throw new FraudulentRequestException("User sending the request does not have permission.");
        }
    }

}
