package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class AuthorizationTokenController {

    @Autowired
    RedisTokenStore redisTokenStore;

    @RequestMapping(value = "/remove_token")
    public Object removeToken(String token){

        //删除RefreshToken
        OAuth2AccessToken accessToken = redisTokenStore.readAccessToken(token);
        if(accessToken != null && accessToken.getRefreshToken() != null){
            redisTokenStore.removeRefreshToken(accessToken.getRefreshToken().getValue());
        }
        //删除AccessToken信息
        redisTokenStore.removeAccessToken(token);


        HashMap map = new HashMap<>();
        map.put("code","200");
        map.put("msg","success");

        return map;
    }

    @RequestMapping(value = "/refresh_token")
    public Object refreshToken(String refreshToken){
        return redisTokenStore.readAuthenticationForRefreshToken(refreshToken);
    }

}
