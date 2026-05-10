package com.alvarohdez.econocom.controllers;

import com.alvarohdez.econocom.dto.LoginRequestDTO;
import com.alvarohdez.econocom.dto.SuccessfulAuthenticationResponse;
import com.alvarohdez.econocom.services.UserAuthenticationService;
import com.alvarohdez.econocom.utils.generators.RequestResponseGenerator;
import com.alvarohdez.econocom.utils.validators.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login/")

public class LoginController {

    private final UserAuthenticationService userAuthenticationService;

    public LoginController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("authenticate")
    public ResponseEntity<SuccessfulAuthenticationResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
        UserValidator.checkForEmptyFields(loginRequestDTO);
        // later for registration i should validate the email

        // FIX DATA ACCESS

        String generatedJwtToken= userAuthenticationService.login(loginRequestDTO);
        return RequestResponseGenerator.generateSuccessfulRequestResponse(
                new SuccessfulAuthenticationResponse(generatedJwtToken));
    }

}
