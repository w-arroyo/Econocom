package com.alvarohdez.econocom.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login/")

public class LoginController {

    @PostMapping("authenticate/")
    public void login(){

    }

}
