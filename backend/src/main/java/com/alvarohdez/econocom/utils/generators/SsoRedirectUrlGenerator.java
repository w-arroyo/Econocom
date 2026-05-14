package com.alvarohdez.econocom.utils.generators;

import com.alvarohdez.econocom.config.SsoConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public final class SsoRedirectUrlGenerator {

    public String generateSsoRedirectUrl(String code){
        return UriComponentsBuilder.fromHttpUrl(SsoConfig.getSsoProviderUrl())
                .queryParam("client_id",SsoConfig.getSsoClientId())
                .queryParam("redirect_uri",SsoConfig.getSsoRedirectUri())
                .queryParam("response_type","code")
                .queryParam("code",code)
                .toUriString();
    }

}
