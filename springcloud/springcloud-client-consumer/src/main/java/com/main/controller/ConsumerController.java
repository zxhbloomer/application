package com.main.controller;

import com.main.feign.PersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	/**
	 * 获取Github里面的属性
	 */
	@Value("${foo}")
	private String foo;

	@Autowired
	PersonalService personalService;

	@RequestMapping("/sendMessage")
	public String sendMessage(String order){
		System.err.println("ConsumerService : 接收到 [订单服务器] 订单为 "+order+" 的订单处理请求");
		String result = personalService.receiverMessage("你购买的商品 " + order + "成功");
		return "{ Done Result (订单) = " + result + "}";
	}

}
