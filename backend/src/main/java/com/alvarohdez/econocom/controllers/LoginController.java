package com.alvarohdez.econocom.controllers;

import com.alvarohdez.econocom.dto.LoginRequestDTO;
import com.alvarohdez.econocom.dto.RegistrationRequestDTO;
import com.alvarohdez.econocom.dto.SuccessfulAuthenticationResponse;
import com.alvarohdez.econocom.models.User;
import com.alvarohdez.econocom.services.UserLoginService;
import com.alvarohdez.econocom.services.UserService;
import com.alvarohdez.econocom.services.UserSsoLoginService;
import com.alvarohdez.econocom.utils.generators.RequestResponseGenerator;
import com.alvarohdez.econocom.utils.validators.GlobalValidator;
import com.alvarohdez.econocom.utils.validators.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")

public class LoginController {

    private final UserLoginService userLoginService;
    private final UserService userService;
    private final UserSsoLoginService userSsoLoginService;

    public LoginController(UserLoginService userLoginService, UserService userService, UserSsoLoginService userSsoLoginService) {
        this.userLoginService = userLoginService;
        this.userService = userService;
        this.userSsoLoginService = userSsoLoginService;
    }

    @PostMapping("login")
    public ResponseEntity<SuccessfulAuthenticationResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
        UserValidator.checkLoginEmptyFields(loginRequestDTO);
        String generatedJwtToken= authenticateUserAndGenerateJwtToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        return generateSuccessfulLoginResponseEntity(generatedJwtToken);
    }

    @GetMapping("sso")
    public ResponseEntity<Void> starSsoLogin(@RequestParam("email") String email){
        GlobalValidator.isNullOrEmpty(email);
        String redirectUrl= userSsoLoginService.startSsoLogin(email);
        return RequestResponseGenerator.generateSsoLoginRequestResponse(redirectUrl);
    }

    @GetMapping("sso/callback")
    public ResponseEntity<SuccessfulAuthenticationResponse> processSsoCallback(@RequestParam("code") String ssoCode){
        GlobalValidator.isNullOrEmpty(ssoCode);
        String token= userSsoLoginService.completeSsoLogin(ssoCode);
        return generateSuccessfulLoginResponseEntity(token);
    }

    @PostMapping("register")
    public ResponseEntity<SuccessfulAuthenticationResponse> register(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        UserValidator.checkRegistrationEmptyFields(registrationRequestDTO);
        User savedUser= userService.saveClientUser(registrationRequestDTO);
        String generatedJwtToken= authenticateUserAndGenerateJwtToken(registrationRequestDTO.getEmail(), registrationRequestDTO.getPassword());
        // for future updates i would respond with a different object with some user details instead of just the jwt token
        return generateSuccessfulLoginResponseEntity(generatedJwtToken);
    }

    private String authenticateUserAndGenerateJwtToken(String email, String password){
        return userLoginService.login(email,password);
    }

    private ResponseEntity<SuccessfulAuthenticationResponse> generateSuccessfulLoginResponseEntity(String token){
        return RequestResponseGenerator.generateSuccessfulRequestResponse(
                new SuccessfulAuthenticationResponse(token));
    }

}
