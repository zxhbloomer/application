package com.main.controller;

import com.main.feign.AuthorizationServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GatewayTokenController {

    @Autowired
    AuthorizationServerService authFeign;

    @GetMapping("/getAccessToken")
    public Object getAccessToken(String username,String password){
        Map<String,String> token = authFeign.getAccessToken(username, password, "password", "all", "gatewayService", "secret");
        HashMap map = new HashMap<>();
        if(token == null){
	        map.put("code","400");
	        map.put("msg","GetTokenFailed");
	        return map;
        }
        map.put("code","200");
        map.put("msg","success");
        map.put("data",token);
        return map;
    }

    @GetMapping("/checkToken")
    public Object checkToken(@RequestParam(value = "token") String token) {
        return authFeign.checkToken(token);
    }

    @GetMapping("/removeToken")
    public Object removeToken(@RequestParam(value = "token") String token) {
        return authFeign.removeToken(token);
    }

}
