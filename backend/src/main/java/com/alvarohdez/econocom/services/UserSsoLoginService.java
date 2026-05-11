package com.alvarohdez.econocom.services;

import com.alvarohdez.econocom.config.AppConfig;
import com.alvarohdez.econocom.config.SsoConfig;
import com.alvarohdez.econocom.exceptions.InvalidSsoLoginCodeException;
import com.alvarohdez.econocom.exceptions.UserDoesNotExistException;
import com.alvarohdez.econocom.handlers.JwtTokenHandler;
import com.alvarohdez.econocom.models.SsoAuthorizationCode;
import com.alvarohdez.econocom.repositories.FillerUserRepository;
import com.alvarohdez.econocom.utils.generators.SsoRedirectUrlGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserSsoLoginService {

    private final JwtTokenHandler jwtTokenHandler;
    private final SsoRedirectUrlGenerator ssoRedirectUrlGenerator;
    private final SsoAuthorizationCodeService ssoAuthorizationCodeService;
    private final FillerUserRepository fillerUserRepository;

    public UserSsoLoginService(JwtTokenHandler jwtTokenHandler, SsoRedirectUrlGenerator ssoRedirectUrlGenerator, SsoAuthorizationCodeService ssoAuthorizationCodeService, FillerUserRepository fillerUserRepository) {
        this.jwtTokenHandler = jwtTokenHandler;
        this.ssoRedirectUrlGenerator = ssoRedirectUrlGenerator;
        this.ssoAuthorizationCodeService = ssoAuthorizationCodeService;
        this.fillerUserRepository = fillerUserRepository;
    }

    public String startSsoLogin(String email){
        checkIfUserExists(email);
        SsoAuthorizationCode ssoAuthorizationCode= ssoAuthorizationCodeService.generateAuthorizationCode(email);
        return generateCallBackUrl(ssoAuthorizationCode);
    }

    public String completeSsoLogin(String code){
        String email=ssoAuthorizationCodeService.removeCodeAfterUse(code);
        return jwtTokenHandler.generateJwtToken(email);
    }

    private String generateCallBackUrl(SsoAuthorizationCode ssoAuthorizationCode){
        return ssoRedirectUrlGenerator.generateSsoRedirectUrl(ssoAuthorizationCode.getCode());
    }

    private void checkIfUserExists(String email){
        if(fillerUserRepository.isEmailAvailable(email)){
            throw new UserDoesNotExistException("There is no user associated to the email provided.");
        }
    }

}
