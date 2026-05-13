package com.alvarohdez.econocom.utils.generators;

import com.alvarohdez.econocom.config.SsoConfig;
import org.springframework.stereotype.Component;

@Component
public final class SsoRedirectUrlGenerator {

    public String generateSsoRedirectUrl(String code){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(SsoConfig.getSsoProviderUrl());
        stringBuilder.append(SsoConfig.getSsoClientIdParam());
        stringBuilder.append(SsoConfig.getSsoClientId());
        stringBuilder.append(SsoConfig.getSsoRedirectUriParam());
        stringBuilder.append(SsoConfig.getSsoRedirectUri());
        stringBuilder.append(SsoConfig.getSsoResponseTypeParam());
        stringBuilder.append(SsoConfig.getSsoCodeParam());
        stringBuilder.append(code);
        return stringBuilder.toString();
    }

}
