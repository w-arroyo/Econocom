package com.alvarohdez.econocom.utils.generators;

import com.alvarohdez.econocom.config.SsoConfig;
import org.springframework.stereotype.Component;

@Component
public class SsoRedirectUrlGenerator {

    public String generateSsoRedirectUrl(){
        return SsoConfig.getSsoProviderUrl()+
                SsoConfig.getSsoClientIdParam()+SsoConfig.getSsoClientId()+
                SsoConfig.getSsoRedirectUriParam()+SsoConfig.getSsoRedirectUri()+
                SsoConfig.getSsoResponseTypeParam();
    }

}
