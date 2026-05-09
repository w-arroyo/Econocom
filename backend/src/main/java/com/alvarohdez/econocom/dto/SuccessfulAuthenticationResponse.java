package com.alvarohdez.econocom.dto;

public class SuccessfulAuthenticationResponse {

    private String token;

    public SuccessfulAuthenticationResponse(String token){
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
