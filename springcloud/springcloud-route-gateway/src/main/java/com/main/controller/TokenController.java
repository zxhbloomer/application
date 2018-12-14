package com.main.controller;

import com.main.feign.AuthorizationServerServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TokenController {

    @Autowired
    AuthorizationServerServiceFeign authFeign;

    @GetMapping("/getAccessToken")
    public Object getAccessToken(String username,String password){
        Map<String,String> token = authFeign.getAccessToken(username, password, "password", "all", "gatewayService", "secret");
	    return token == null ? "GetTokenError" : token;
    }

    @GetMapping("/checkToken")
    public Object checkToken(@RequestParam(value = "token") String token) {
        return authFeign.checkToken(token);
    }

	
}
