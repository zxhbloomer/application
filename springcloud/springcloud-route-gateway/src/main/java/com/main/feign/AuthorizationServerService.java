package com.main.feign;

import com.main.feign.fallback.AuthorizationServerServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "service-oauth2-authorization-server",fallback = AuthorizationServerServiceFallback.class)
public interface AuthorizationServerService {

    /**
     * 获取Token
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST )
    Map<String,String> getAccessToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("grant_type") String grantType,
            @RequestParam("scope") String scope,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret);

    /**
     * 校验Token
     */
    @RequestMapping(value = "/oauth/check_token",method = RequestMethod.GET)
    Map<String, ?> checkToken(@RequestParam("token") String token);

    /**
     * 使Token过期
     */
    @RequestMapping(value = "/oauth/remove_token",method = RequestMethod.GET)
    Map<String,Object> removeToken(@RequestParam("token") String token);

}
