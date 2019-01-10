package com.main.controller;

import com.main.common.config.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController{

    @Autowired
    RedisService redisService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String SESSIONIDS = "sessionIds";
    private static final String PRINCIPALS = "principals";


    /**
     * 登入
     */
    @RequestMapping(value = {"/login","/"})
    public String login(String error, Model model){
        if(error != null && error.length() <0 &&error.equals("true")){ model.addAttribute("loginError",true); }
        return "login";
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

    /**
     *  主页
     */
    @RequestMapping(value = "/index")
    public String index(Model model,HttpServletRequest request) {
        model.addAttribute("sessionId",request.getSession().getId());
        return "index";
    }

    /**
     * 测试界面(所有人可访问)
     */
    @RequestMapping("/home")
    public String test(){
        return "home";
    }


    @GetMapping("/anonymousPath")
    @ResponseBody
    public String anonymousPath(){
        return "无须登录-所有人(登陆之后无法访问)";
    }
    @GetMapping("/permitAllPath")
    @ResponseBody
    public String permitAllPath(){
        return "所有用户(无论登不登录都可访问)";
    }
    @GetMapping("/authenticatedPath")
    @ResponseBody
    public String authenticatedPath(){
        return "登录后-所有用户(必须才可以访问)";
    }


}
