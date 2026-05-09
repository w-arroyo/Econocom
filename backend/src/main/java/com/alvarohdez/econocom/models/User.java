package com.alvarohdez.econocom.models;

import com.alvarohdez.econocom.enums.UserType;

public class User {

    private String userId;
    private String email;
    private String encodedPassword;
    private UserType userType;

    public User(){

    }

    public User(String id, String email, String password, UserType userType){
        this.userId=id;
        this.email = email;
        this.encodedPassword=password;
        this.userType=userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType=userType;
    }

    public String getUserId() {
        return userId;
    }

}
