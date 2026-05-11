package com.alvarohdez.econocom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="sso")
public class SsoConfig {

    private static String ssoClientId;
    private static String ssoRedirectUri;
    private static String ssoProviderUrl;
    private static String ssoClientIdParam;
    private static String ssoRedirectUriParam;
    private static String ssoResponseTypeParam;
    private static String ssoCodePrefix;

    @Value("${sso.client_id}")
    private void setClientId(String value){
        ssoClientId=value;
    }

    @Value("${sso.redirect_uri}")
    private void setRedirectUri(String value){
        ssoRedirectUri=value;
    }

    @Value("${sso.provider_url}")
    private void setProviderUrl(String value){
        ssoProviderUrl=value;
    }

    @Value("${sso.client_id_param}")
    private void setSsoClientIdParam(String value){
        ssoClientIdParam=value;
    }

    @Value("${sso.redirect_uri_param}")
    private void setSsoRedirectUriParam(String value){
        ssoRedirectUriParam=value;
    }

    @Value("${sso.response_type_param}")
    private void setSsoResponseTypeParam(String value){
        ssoResponseTypeParam=value;
    }

    @Value("${sso.code_prefix}")
    private void setSsoCodePrefix(String value){
        ssoCodePrefix=value;
    }

    public static String getSsoClientId(){
        return ssoClientId;
    }

    public static String getSsoRedirectUri() {
        return ssoRedirectUri;
    }

    public static String getSsoProviderUrl() {
        return ssoProviderUrl;
    }

    public static String getSsoClientIdParam() {
        return ssoClientIdParam;
    }

    public static String getSsoRedirectUriParam() {
        return ssoRedirectUriParam;
    }

    public static String getSsoResponseTypeParam() {
        return ssoResponseTypeParam;
    }

    public static String getSsoCodePrefix() {
        return ssoCodePrefix;
    }

}
