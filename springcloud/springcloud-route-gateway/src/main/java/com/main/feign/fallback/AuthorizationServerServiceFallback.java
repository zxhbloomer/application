package com.main.feign.fallback;

import com.main.feign.AuthorizationServerService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthorizationServerServiceFallback implements AuthorizationServerService {

    @Override
    public Map<String, String> getAccessToken(String username, String password, String grantType, String scope, String clientId, String clientSecret) {
        return null;
    }

    @Override
    public Map<String, ?> checkToken(String token) {
        return null;
    }

    @Override
    public Map<String, Object> removeToken(String token) {
        return null;
    }
}
