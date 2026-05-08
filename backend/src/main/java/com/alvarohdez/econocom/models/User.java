package com.alvarohdez.econocom.models;

import com.alvarohdez.econocom.enums.UserType;

public class User {

    private String userId;
    private String userName;
    private String encodedPassword;
    private UserType userType;

    public User(){

    }

    public User(String id, String userName, String password, UserType userType){
        this.userId=id;
        this.userName=userName;
        this.encodedPassword=password;
        this.userType=userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
