package com.alvarohdez.econocom.services;

import com.alvarohdez.econocom.exceptions.InvalidSsoLoginCodeException;
import com.alvarohdez.econocom.factories.SsoAuthorizationCodeFactory;
import com.alvarohdez.econocom.models.SsoAuthorizationCode;
import com.alvarohdez.econocom.repositories.FakeRedisSsoAuthorizationCodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SsoAuthorizationCodeService {

    private final SsoAuthorizationCodeFactory ssoAuthorizationCodeFactory;
    private final FakeRedisSsoAuthorizationCodeRepository fakeRedisSsoAuthorizationCodeRepository;

    public SsoAuthorizationCodeService(SsoAuthorizationCodeFactory ssoAuthorizationCodeFactory, FakeRedisSsoAuthorizationCodeRepository fakeRedisSsoAuthorizationCodeRepository) {
        this.ssoAuthorizationCodeFactory = ssoAuthorizationCodeFactory;
        this.fakeRedisSsoAuthorizationCodeRepository = fakeRedisSsoAuthorizationCodeRepository;
    }

    public SsoAuthorizationCode generateAuthorizationCode(String email){
        SsoAuthorizationCode ssoAuthorizationCode=ssoAuthorizationCodeFactory.generateSsoAuthorizationCode(email);
        return fakeRedisSsoAuthorizationCodeRepository.saveCode(ssoAuthorizationCode);
    }

    public String removeCodeAfterUse(String code){
        SsoAuthorizationCode ssoAuthorizationCode= fakeRedisSsoAuthorizationCodeRepository.findByCode(code)
                .orElseThrow(()->new InvalidSsoLoginCodeException("Invalid sso authorization code."));
        checkCodeExpiration(ssoAuthorizationCode);
        fakeRedisSsoAuthorizationCodeRepository.delete(ssoAuthorizationCode);
        return ssoAuthorizationCode.getEmail();
    }

    private void checkCodeExpiration(SsoAuthorizationCode ssoAuthorizationCode){
        if(ssoAuthorizationCode.getExpirationDate().isBefore(LocalDateTime.now())){
            throw new InvalidSsoLoginCodeException("Sso authorization code is expired.");
        }
    }

}
