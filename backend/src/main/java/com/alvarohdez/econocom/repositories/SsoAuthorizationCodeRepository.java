package com.alvarohdez.econocom.repositories;

import com.alvarohdez.econocom.models.SsoAuthorizationCode;

import java.util.Optional;

public interface SsoAuthorizationCodeRepository {

    Optional<SsoAuthorizationCode> findByCode(String code);

    SsoAuthorizationCode saveCode(SsoAuthorizationCode ssoAuthorizationCode);

    void delete(SsoAuthorizationCode ssoAuthorizationCode);

}
