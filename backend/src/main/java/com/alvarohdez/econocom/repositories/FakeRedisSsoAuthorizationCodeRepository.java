package com.alvarohdez.econocom.repositories;

import com.alvarohdez.econocom.exceptions.InvalidSsoLoginCodeException;
import com.alvarohdez.econocom.models.SsoAuthorizationCode;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FakeRedisSsoAuthorizationCodeRepository implements SsoAuthorizationCodeRepository{

    private final Map<String,SsoAuthorizationCode> authorizationCodeMap;

    public FakeRedisSsoAuthorizationCodeRepository() {
        this.authorizationCodeMap=new ConcurrentHashMap<>();
    }

    @Override
    public Optional<SsoAuthorizationCode> findByCode(String code) {
        return Optional.ofNullable(authorizationCodeMap.get(code));
    }

    @Override
    public SsoAuthorizationCode saveCode(SsoAuthorizationCode ssoAuthorizationCode) {
        SsoAuthorizationCode foundCode=authorizationCodeMap.putIfAbsent(ssoAuthorizationCode.getCode(),ssoAuthorizationCode);
        if(foundCode!=null){
            throw new InvalidSsoLoginCodeException("Invalid SSO login code");
        }
        return ssoAuthorizationCode;
    }

    @Override
    public void delete(SsoAuthorizationCode ssoAuthorizationCode) {
        authorizationCodeMap.remove(ssoAuthorizationCode.getCode());
    }

}
