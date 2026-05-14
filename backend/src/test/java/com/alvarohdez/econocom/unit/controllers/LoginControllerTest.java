package com.alvarohdez.econocom.unit.controllers;

import com.alvarohdez.econocom.controllers.LoginController;
import com.alvarohdez.econocom.dto.LoginRequestDTO;
import com.alvarohdez.econocom.dto.SuccessfulAuthenticationResponse;
import com.alvarohdez.econocom.services.UserLoginService;
import com.alvarohdez.econocom.services.UserService;
import com.alvarohdez.econocom.services.UserSsoLoginService;
import com.alvarohdez.econocom.unit.utils.constants.UserTestConstants;
import com.alvarohdez.econocom.utils.generators.RequestResponseGenerator;
import com.alvarohdez.econocom.utils.validators.GlobalValidator;
import com.alvarohdez.econocom.utils.validators.UserValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TEST - Login Controller")
public class LoginControllerTest {

    @Mock
    private UserLoginService userLoginService;

    @Mock
    private UserService userService;

    @Mock
    private UserSsoLoginService userSsoLoginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    @DisplayName("should return HttpStatus.OK and a JWT token when credentials are valid")
    void shouldReturn200WithJwtTokenWhenCredentialsAreValid(){
        LoginRequestDTO loginRequestDTO= new LoginRequestDTO(UserTestConstants.FAKE_EMAIL,UserTestConstants.FAKE_PLAIN_PASSWORD);
        when(userLoginService.login(UserTestConstants.FAKE_EMAIL,UserTestConstants.FAKE_PLAIN_PASSWORD)
        ).thenReturn(UserTestConstants.FAKE_JWT_TOKEN);
        try (MockedStatic<UserValidator> mockedValidator=
                     mockStatic(UserValidator.class)){
            mockedValidator.when(()->
                            UserValidator.checkLoginEmptyFields(loginRequestDTO))
                    .thenAnswer(invocation-> null);
            ResponseEntity<SuccessfulAuthenticationResponse> response= loginController.login(loginRequestDTO);
            assertEquals(HttpStatus.OK,response.getStatusCode());
            SuccessfulAuthenticationResponse body= (SuccessfulAuthenticationResponse) response.getBody();
            assertNotNull(body);
            assertEquals(UserTestConstants.FAKE_JWT_TOKEN, body.getToken());
        }
    }

    @Test
    @DisplayName("should throw exception when empty fields are detected")
    void shouldThrowWhenLoginFieldsAreEmpty(){
        LoginRequestDTO loginRequestDTO= new LoginRequestDTO("", "");
        try (MockedStatic<UserValidator> mockedValidator=
                     mockStatic(UserValidator.class)){
            mockedValidator.when(()->
                            UserValidator.checkLoginEmptyFields(loginRequestDTO))
                    .thenThrow(new RuntimeException("Empty fields are not allowed."));
            assertThrows(RuntimeException.class,
                    ()-> loginController.login(loginRequestDTO));
            verifyNoInteractions(userLoginService);
        }
    }

    @Test
    @DisplayName("should return HttpStatus.FOUND with correct Location header when email is valid")
    void shouldReturn302WithLocationHeaderWhenEmailIsValid(){
        when(userSsoLoginService.startSsoLogin(UserTestConstants.FAKE_EMAIL))
                .thenReturn(UserTestConstants.FAKE_REDIRECT_URL);
        try (MockedStatic<GlobalValidator> mockedValidator= mockStatic(GlobalValidator.class);
             MockedStatic<RequestResponseGenerator> mockedGenerator=
                     mockStatic(RequestResponseGenerator.class)){
            mockedValidator.when(()->
                            GlobalValidator.isNullOrEmpty(UserTestConstants.FAKE_EMAIL))
                    .thenAnswer(invocation-> null);
            ResponseEntity<Void> fakeRedirect= ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(URI.create(UserTestConstants.FAKE_REDIRECT_URL))
                    .build();
            mockedGenerator.when(()->
                            RequestResponseGenerator.generateSsoLoginRequestResponse(UserTestConstants.FAKE_REDIRECT_URL))
                    .thenReturn(fakeRedirect);
            ResponseEntity<Void> response=
                    loginController.starSsoLogin(UserTestConstants.FAKE_EMAIL);
            assertAll(
                    ()-> assertEquals(HttpStatus.FOUND,response.getStatusCode()),
                    ()-> assertEquals(
                            URI.create(UserTestConstants.FAKE_REDIRECT_URL),
                            response.getHeaders().getLocation()));
        }
    }

}
