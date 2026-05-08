package com.alvarohdez.econocom.app_config;

import org.springframework.beans.factory.annotation.Value;

public class AppConfig {

    private static String fillerUsername;
    private static String fillerUserPassword;

    @Value("{app.filler_username}")
    private void setFillerUsername(String value){
        fillerUsername=value;
    }

    @Value("{app.filler_user_password}")
    private void setFillerUserPassword(String value){
        fillerUserPassword=value;
    }

    public static String getFillerUsername(){
        return fillerUsername;
    }

    public static String getFillerUserPassword(){
        return fillerUserPassword;
    }

}
