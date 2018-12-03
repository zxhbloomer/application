package com.main.controller;

import com.jack.springcloud.bean.User;
import com.main.feign.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController{

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

		return "{ Shopping Result (购物) : " + result + " }";
	}


	/**
	 * Feign-Get请求多参数传递
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(User user){
		log.info("GetMultiParam : Token = {}",request().getHeader("account_token"));
		return "Get : " + user.toString();
	}

	/**
	 * Feign-Post请求多参数传递
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser(@RequestBody User user){
		log.info("PostMultiParam : Token = {}",request().getHeader("account_token"));
		return "Post : " + user.toString();
	}

	/**
	 * 通过上下文获取Request
	 */
	protected HttpServletRequest request() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}

}
