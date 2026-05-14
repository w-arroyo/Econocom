package com.alvarohdez.econocom.unit.utils.constants;

import com.alvarohdez.econocom.enums.UserType;

public final class UserTestConstants {

    private UserTestConstants(){

    }

    public static final String FAKE_USER_ID= "fake_id";
    public static final String FAKE_EMAIL = "fake@rodeo.es";
    public static final String FAKE_PLAIN_PASSWORD="fake_text_password";
    public static final String FAKE_ENCODED_PASSWORD= "fake_encoded_password";
    public static final UserType FAKE_USER_TYPE= UserType.CLIENT;
    public static final String FAKE_JWT_TOKEN="fake_jwt_token";
    public static final String FAKE_SSO_CODE= "fake_sso_code";
    public static final String FAKE_REDIRECT_URL= "http://fake/fake";

}
