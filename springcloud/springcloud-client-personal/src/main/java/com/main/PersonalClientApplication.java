package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 熔断器页面:			http://localhost:9301/hystrix
 * hystrix-stream:		http://localhost:9301/actuator/hystrix.stream	(放入熔断器页面的输入框里)
 * turbine-stream:		http://localhost:9301/turbine.stream 			(放入熔断器页面的输入框里)
 * 注意必须在配置文件里面配置/actuator/hystrix.stream路径,这个页面默认是显示Loading的
 * 如果想要看到数据必须使用Feign去访问客户端才能看到数据
 */
@SpringCloudApplication    //这个注解包括了开启熔断器和开启Client的注解
@EnableHystrixDashboard    //熔断器的监控
@EnableTurbine             //在一个地方开启 "监控多个应用" 的功能就行了
@EnableFeignClients        //启动Feign远程调用
@ComponentScan(value = {"com.jack.springcloud","com.main","com.config"}) //扫描一些公共的配置(还要把自己也扫进来哦)
public class PersonalClientApplication {

	public static void main(String[] args){
		SpringApplication.run(PersonalClientApplication.class);
	}

}
