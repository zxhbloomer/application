package com.main.controller;

import brave.sampler.Sampler;

import com.main.feign.GitHubFeignService;
import com.main.feign.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    GitHubFeignService helloFeignService;

    @Autowired
    OrderService orderService;


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @RequestMapping("/personOne")
    public String personOne(){
        System.err.println("Method : PersonOne");
        return restTemplate.getForObject("http://SERVICE-CLIENT-ORDER/orderOne", String.class);
    }
    @RequestMapping("/personTow")
    public String personTow(){
        System.err.println("Method : personTow");
        return "ServiceName=service-client-personal";
    }

    /**
     * [GZIP] 直接返回 ResponseEntity<byte[]> 类型即可(不用转String)
     */
    @GetMapping("/githubQueryRepo")
    public ResponseEntity<byte[]> gitHubTest(){
        return helloFeignService.searchRepo("spring-cloud-dubbo");
    }


}
