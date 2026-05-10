package com.alvarohdez.econocom.controllers;

import com.alvarohdez.econocom.dto.LoginRequestDTO;
import com.alvarohdez.econocom.dto.RegistrationRequestDTO;
import com.alvarohdez.econocom.dto.SuccessfulAuthenticationResponse;
import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.services.UserAuthenticationService;
import com.alvarohdez.econocom.services.UserService;
import com.alvarohdez.econocom.utils.generators.RequestResponseGenerator;
import com.alvarohdez.econocom.utils.validators.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")

public class LoginController {

    private final UserAuthenticationService userAuthenticationService;
    private final UserService userService;

    public LoginController(UserAuthenticationService userAuthenticationService, UserService userService) {
        this.userAuthenticationService = userAuthenticationService;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<SuccessfulAuthenticationResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
        UserValidator.checkLoginEmptyFields(loginRequestDTO);
        String generatedJwtToken= authenticateUserAndGenerateJwtToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        return RequestResponseGenerator.generateSuccessfulRequestResponse(
                new SuccessfulAuthenticationResponse(generatedJwtToken));
    }

    @PostMapping("register")
    public ResponseEntity<SuccessfulAuthenticationResponse> register(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        UserValidator.checkRegistrationEmptyFields(registrationRequestDTO);
        User savedUser= userService.saveClientUser(registrationRequestDTO);
        String generatedJwtToken= authenticateUserAndGenerateJwtToken(registrationRequestDTO.getEmail(), registrationRequestDTO.getPassword());
        return RequestResponseGenerator.generateSuccessfulRequestResponse(
                new SuccessfulAuthenticationResponse(generatedJwtToken));
    }

    private String authenticateUserAndGenerateJwtToken(String email, String password){
        return userAuthenticationService.login(email,password);
    }

}
