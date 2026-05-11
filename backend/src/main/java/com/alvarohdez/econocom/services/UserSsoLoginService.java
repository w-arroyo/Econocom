package com.alvarohdez.econocom.services;

import com.alvarohdez.econocom.config.AppConfig;
import com.alvarohdez.econocom.config.SsoConfig;
import com.alvarohdez.econocom.exceptions.InvalidSsoLoginCodeException;
import com.alvarohdez.econocom.handlers.JwtTokenHandler;
import com.alvarohdez.econocom.utils.generators.SsoRedirectUrlGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserSsoLoginService {

    private final JwtTokenHandler jwtTokenHandler;
    private final SsoRedirectUrlGenerator ssoRedirectUrlGenerator;

    public UserSsoLoginService(JwtTokenHandler jwtTokenHandler, SsoRedirectUrlGenerator ssoRedirectUrlGenerator) {
        this.jwtTokenHandler = jwtTokenHandler;
        this.ssoRedirectUrlGenerator = ssoRedirectUrlGenerator;
    }

    public String generateUrl(){
        return ssoRedirectUrlGenerator.generateSsoRedirectUrl();
    }

    public String processCallbackRequest(String code){
        if(!checkCode(code)){
            throw new InvalidSsoLoginCodeException("Invalid SSO login code.");
        }
        return jwtTokenHandler.generateJwtToken(AppConfig.getFillerUserEmail());
    }

    private boolean checkCode(String code){
        return code!=null && code.startsWith(SsoConfig.getSsoCodePrefix());
    }

}
