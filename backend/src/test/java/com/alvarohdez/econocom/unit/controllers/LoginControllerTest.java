package com.alvarohdez.econocom.unit.controllers;

import com.alvarohdez.econocom.controllers.LoginController;
import com.alvarohdez.econocom.services.UserLoginService;
import com.alvarohdez.econocom.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("TEST - Login Controller")
public class LoginControllerTest {

    @Mock
    private UserLoginService userLoginService;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

}
