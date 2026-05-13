package com.alvarohdez.econocom.unit.services;

import com.alvarohdez.econocom.handlers.JwtTokenHandler;
import com.alvarohdez.econocom.services.UserLoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test - UserLoginService")
public class UserLoginServiceTest {

    @Mock
    private JwtTokenHandler jwtTokenHandler;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserLoginService userLoginService;
    // creates a real instance of the class being tested and injects the @Mock fields automatically

}
