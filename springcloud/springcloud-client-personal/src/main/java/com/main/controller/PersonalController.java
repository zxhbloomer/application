package com.main.controller;

import com.main.feign.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
		return "{ Buy Result = " + result + " }";
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


}
