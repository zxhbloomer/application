package com.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication    //这个注解包括了开启熔断器和开启Client的注解
@EnableHystrixDashboard    //熔断器的监控
@EnableFeignClients        //启动Feign远
@ComponentScan(value = {"com.jack.springcloud","com.main","com.config"}) //扫描一些公共的配置(还要把自己也扫进来哦)
@RestController
public class OrderClientApplication {

	public static void main(String[] args){
		SpringApplication.run(OrderClientApplication.class);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/orderTow")
	public String orderTow(){
		System.err.println("Method : OrderTow");
		return "ServiceName=service-client-order";
	}

	@RequestMapping("/orderOne")
	public String orderOne(){
		System.err.println("Method : OrderOne");
		return restTemplate.getForObject("http://SERVICE-CLIENT-PERSONAL/personTow",String.class);
	}

}
