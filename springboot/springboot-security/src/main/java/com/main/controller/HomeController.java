package com.main.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        if(StringUtils.isNotEmpty(error)&&error.equals("true")){ model.addAttribute("loginError",true); }
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

    /**
     * 特定IP地址可访问
     */
    @RequestMapping("/address")
    @ResponseBody
    public String address(){
        return "特定IP地址可访问";
    }

    /**
     * 传入一个Session使这个Session过期
     */
    @RequestMapping("/getInfo")
    @ResponseBody
    public Object getInfo(String sessionId){
//        BoundHashOperations<String, String, SessionInformation> s = template.boundHashOps(SESSIONIDS);
//        BoundHashOperations<String, String, Set<String>> p = template.boundHashOps(PRINCIPALS);
//
////        Long sd = s.delete(sessionId);
////        Long pd = p.delete("15277578023");
//
//        Set<String> sk =  s.keys();
//        List<SessionInformation> sv = s.values();
//
//        Set<String> pk =  p.keys();
//        List<Set<String>> pv = p.values();
//
//        Long ss = s.size();
//        Long ps = p.size();
//
//        SessionInformation sg = s.get(sessionId);
//        Set<String> pg = p.get("15277578023");
//
//        Set<String> keys = template.keys("*");
//        Set<String> skeys = s.keys();
//        Set<String> pkeys = p.keys();

        return "Success";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request){
//        BoundHashOperations<String, String, SessionInformation> s = template.boundHashOps(SESSIONIDS);
//        BoundHashOperations<String, String, Set<String>> p = template.boundHashOps(PRINCIPALS);
//
//        Long num1 = s.delete(sessionId);
//        Long num2 = p.delete("15277578023");
        String key1 = "spring:session:sessions:"+request.getSession().getId();
        String key2 = "spring:session:sessions:expires:"+request.getSession().getId();
        String key3 = "spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:15277578023";


        BoundHashOperations<String, String, Set<String>> hp = redisTemplate.boundHashOps(PRINCIPALS);
        BoundHashOperations<String, String, Set<String>> hs = redisTemplate.boundHashOps(SESSIONIDS);

        Long num1 = hp.delete("15277578023");
        Long num2 = hs.delete(request.getSession().getId());


        Boolean b1 = stringRedisTemplate.delete(key1);
        Boolean b2 = stringRedisTemplate.delete(key2);
        Boolean b3 = stringRedisTemplate.delete(key3);



        return "Delete";
    }
}
