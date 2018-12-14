package com.main.feign;

import com.main.feign.fallback.AuthorizationServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "service-oauth2-authorization-server",fallback = AuthorizationServerFallback.class)
public interface AuthorizationServerServiceFeign {

    /**
     * 获取Token
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    Map<String,String> getAccessToken(@RequestParam("username") String username,
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

}
