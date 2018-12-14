package com.main.feign.fallback;


import com.main.feign.AuthorizationServerServiceFeign;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthorizationServerFallback implements AuthorizationServerServiceFeign {

    @Override
    public Map<String,String> getAccessToken(String username, String password, String grant_type, String scope, String client_id, String client_secret) {
        return null;
    }

    @Override
    public Map<String, ?> checkToken(String token) {
        return null;
    }
}
