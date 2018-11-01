package com.main.controller;

import com.main.feign.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController {

	/**
	 * 获取Github里面的属性
	 */
	@Value("${foo}")
	private String foo;

	@Autowired
	ConsumerService consumerService;

	@RequestMapping("/receiverGoods")
	public String receiverOrder(String goodsName){
		System.err.println("OrderService : 接收到 [用户服务器] 购买 "+goodsName+" 物品的请求");

		HashMap order = new HashMap();
		order.put("price",15000);
		order.put("number",50);
		order.put("createTime",new Date());
		order.put("goodName",goodsName);
		String result = consumerService.sendMessage(order.toString());

		return "{ Shopping Result : " + result + " }";
	}


}
