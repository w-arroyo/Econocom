package com.alvarohdez.econocom.factories;

import com.alvarohdez.econocom.models.SsoAuthorizationCode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SsoAuthorizationCodeFactory {

    private static final int EXPIRATION_AFTER=5; // if i got enough time i'll move it to the properties file

    public SsoAuthorizationCode generateSsoAuthorizationCode(String email){
        SsoAuthorizationCode ssoAuthorizationCode=new SsoAuthorizationCode();
        ssoAuthorizationCode.setCode(UUID.randomUUID().toString());
        ssoAuthorizationCode.setEmail(email);
        ssoAuthorizationCode.setExpirationDate(LocalDateTime.now().plusMinutes(EXPIRATION_AFTER));
        return ssoAuthorizationCode;
    }

}
