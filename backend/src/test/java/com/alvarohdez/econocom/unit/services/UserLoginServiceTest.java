package com.alvarohdez.econocom.unit.services;

import com.alvarohdez.econocom.exceptions.UnauthorizedRequestException;
import com.alvarohdez.econocom.handlers.JwtTokenHandler;
import com.alvarohdez.econocom.services.UserLoginService;
import com.alvarohdez.econocom.unit.utils.constants.UserTestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test - User Login Service")
public class UserLoginServiceTest {

    @Mock
    private JwtTokenHandler jwtTokenHandler;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserLoginService userLoginService;
    // creates a real instance of the class being tested and injects the @Mock fields automatically

    @Test
    @DisplayName("should return jwt token when credentials are fine")
    void shouldReturnJwtTokenWhenCredentialsAreValid() {
        when(jwtTokenHandler.generateJwtToken(UserTestConstants.FAKE_EMAIL))
                .thenReturn(UserTestConstants.FAKE_JWT_TOKEN);
        String fakeToken= userLoginService.login(
                UserTestConstants.FAKE_EMAIL,
                UserTestConstants.FAKE_ENCODED_PASSWORD);
        assertEquals(UserTestConstants.FAKE_JWT_TOKEN,fakeToken);
    }

    @Test
    @DisplayName("should throw exception when credentials are wrong")
    void shouldPropagateExceptionWhenAuthenticationFails() {
        doThrow(new RuntimeException("wrong credentials"))
                .when(authenticationManager)
                .authenticate(any());
        assertThrows(RuntimeException.class,
                ()-> userLoginService.login(
                        UserTestConstants.FAKE_EMAIL,
                        UserTestConstants.FAKE_ENCODED_PASSWORD));
    }

    @Test
    @DisplayName("should throw exception when authentication is null")
    void shouldThrowWhenAuthenticationIsNull() {
        SecurityContext securityContext= mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(null);
        try (MockedStatic<SecurityContextHolder> mockedHolder=
                     mockStatic(SecurityContextHolder.class)){
            mockedHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);
            assertThrows(UnauthorizedRequestException.class,
                    ()-> userLoginService.checkIfAUserIsLoggedIn());
        }
    }

    @Test
    @DisplayName("should login without execption")
    void shouldNotThrowWhenUserIsAuthenticated() {
        Authentication authentication= mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        SecurityContext securityContext= mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        try (MockedStatic<SecurityContextHolder> mockedHolder=
                     mockStatic(SecurityContextHolder.class)){
            mockedHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);
            assertDoesNotThrow(()-> userLoginService.checkIfAUserIsLoggedIn());
        }
    }

}
