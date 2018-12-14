package com.main.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;

/**
 * 往Token里面添加一些自定义的信息
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organization", new Random().nextInt(100000));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//        ((DefaultOAuth2AccessToken) accessToken).setExpiration(new Date());
        return accessToken;
    }
}
