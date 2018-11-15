package com.main;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 熔断器页面:			http://localhost:9301/hystrix
 * hystrix-stream:		http://localhost:9301/actuator/hystrix.stream	(放入熔断器页面的输入框里)
 * turbine-stream:		http://localhost:9301/turbine.stream 			(放入熔断器页面的输入框里)
 * 注意必须在配置文件里面配置/actuator/hystrix.stream路径,这个页面默认是显示Loading的
 * 如果想要看到数据必须使用Feign去访问客户端才能看到数据
 *
 * 如果需要Zipkin的数据持久化到MySql的话需要在 Docker对server端给予参数进行配置
 * 命令:docker run -d -p 9411:9411 -e STORAGE_TYPE=mysql -e MYSQL_HOST=192.168.31.12 -e MYSQL_TCP_PORT=3306 -e MYSQL_DB=db_zipkin -e MYSQL_USER=root -e MYSQL_PASS=root -e zipkin.collector.rabbitmq.addresses=39.108.82.13:5672 -e zipkin.collector.rabbitmq.username=demo -e zipkin.collector.rabbitmq.password=123456 openzipkin/zipkin
 */
@SpringCloudApplication    //这个注解包括了开启熔断器和开启Client的注解
@EnableHystrixDashboard    //熔断器的监控
@EnableTurbine             //在一个地方开启 "监控多个应用" 的功能就行了
@EnableFeignClients        //启动Feign远程调用
@ComponentScan(value = {"com.jack.springcloud","com.main"}) //扫描一些公共的配置(还要把自己也扫进来哦)
@RestController
public class PersonalClientApplication {

	public static void main(String[] args){
		SpringApplication.run(PersonalClientApplication.class);
	}


	@Autowired
	private RestTemplate restTemplate;

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
		return restTemplate.getForObject("http://localhost:9302/orderOne", String.class);
	}
	@RequestMapping("/personTow")
	public String personTow(){
		System.err.println("Method : personTow");
		return "ServiceName=service-client-personal";
	}

}
