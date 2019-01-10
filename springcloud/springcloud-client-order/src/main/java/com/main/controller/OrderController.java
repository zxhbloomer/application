package com.main.controller;

import com.jack.springcloud.bean.User;
import com.jack.springcloud.common.util.SimpleUserUtil;
import com.main.feign.ConsumerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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

	@ApiImplicitParams({
			@ApiImplicitParam(name = "order", value = "订单信息", paramType = "query", required = true, dataType = "string"),
	})
	@ApiOperation(value = "发送物品的库存信息到消费服务", notes = "一般来说接收PersonalService的购物请求", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = String.class
	)
	@GetMapping("/receiverGoods")
	public String receiverOrder(@RequestParam String goodsName){
		log.info("接收到购物请求 : goodsName = {}",goodsName);
		HashMap order = new HashMap();
		order.put("price",15000);
		order.put("balance",50);
		order.put("createTime",new Date());
		order.put("goodName",goodsName);
		String result = consumerService.sendMessage(order.toString());
		return success(result);
	}

	private String success(String data){
		Map map = new HashMap<>();
		map.put("code",200);
		map.put("msg","OrderService");
		map.put("data",data);
		return map.toString();
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
