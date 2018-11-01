package com.main;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication    //这个注解包括了开启熔断器和开启Client的注解
@EnableHystrixDashboard    //熔断器的监控
@EnableFeignClients        //启动Feign远程调用
@ComponentScan(value = {"com.jack.springcloud","com.main"}) //扫描一些公共的配置(还要把自己也扫进来哦)
public class ConsumerClientApplication {

	public static void main(String[] args){
		SpringApplication.run(ConsumerClientApplication.class);
	}

}
