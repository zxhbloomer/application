package com.main.controller;

import com.jack.springcloud.bean.People;
import com.jack.springcloud.bean.User;
import com.main.feign.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RefreshScope   //刷新此类里面的@Value配置可通过 http://localhost:9301/actuator/bus-refresh 路径刷新
@RestController
@RequestMapping("/personal")
public class PersonalController {

	/**
	 * 获取Github里面的属性
	 */
	@Value("${foo}")
	private String foo;

	@Autowired
	OrderService orderService;


	@RequestMapping("/receiverMessage")
	public String receiverMessage(String message){
		System.err.println("PersonalService : 接收到 [消费服务器] 消息 "+message+" 的处理消息");
		return "Success";
	}

	@GetMapping("/buyGoods")
	public String buyGoods(String goodsName){
		System.err.println("Controller : 购买物品-" + goodsName);
		String result = orderService.receiverOrder(goodsName);
		return "{ Buy Result (用户) = " + result + " }";
	}

	@GetMapping("/testMemory")
	public String testMemory()throws Exception{
		String str = "";
		for(int i=0;i<1000;i++){str = str +  Math.abs(new Random().nextLong());}

		ArrayList<String> list = new ArrayList<>();

		for(int i=0;i<1000;i++){
			list.add(i + "=" + str);
		}
		Thread.sleep(10000);

		return "success";
	}

	/**
	 * 用于演示Feign的Get请求多参数传递
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(){
		return orderService.addUser(getInstance());
	}

	/**
	 * 用于演示Feign的Post请求多参数传递
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateUser(){
		return orderService.updateUser(getInstance());
	}

	public User getInstance(){
		User user = new User();
		user.setUsername("梁金龙");
		user.setPassword("6661918");
		user.setLikes(new String[]{"玩游戏"});
		user.setSex(true);
		user.setAccountList(new ArrayList<>(){{add(123L);}});

		People p1 = new People();
		p1.setNickname("One");
		p1.setDegree(1);

		People p2 = new People();
		p2.setNickname("Two");
		p2.setDegree(2);

		People p3 = new People();
		p3.setNickname("Three");
		p3.setDegree(3);

		user.setPeople(p1);
		user.setPeopleList(new ArrayList<>(){{add(p2);}});
		user.setPeoples(new People[]{p3});
		return user;
	}


	/**
	 * 使用熔断器
	 */
	@HystrixCommand(fallbackMethod = "defaultPersonal")
	@RequestMapping(value = "/gerPersonal",method = RequestMethod.GET)
	public String getPersonal(String name) throws Exception{
		if("jack".equals(name)){
			return "success";
		}else{
			throw new Exception();
		}
	}

	public String defaultPersonal(String name){
		return "你的名字不叫jack!!!";
	}

}
