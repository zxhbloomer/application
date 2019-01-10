package com.main.controller;

import com.jack.springcloud.common.util.SimpleUserUtil;
import com.main.feign.PersonalService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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


	@ApiImplicitParams({
			@ApiImplicitParam(name = "order", value = "订单信息", paramType = "query", required = true, dataType = "string"),
	})
	@ApiOperation(value = "发送订单消息到用户服务", notes = "接收来自OrderService的订单信息,处理过后反馈信息给PersonalService", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = String.class)
	@GetMapping("/sendMessage")
	public String sendMessage(@RequestParam String order){
		log.info("ConsumerService : 接收到 [订单服务器] 订单为 "+order+" 的订单处理请求");
		String result = personalService.receiverMessage(order);
		return success(result);
	}

	private String success(String data){
		Map map = new HashMap<>();
		map.put("code",200);
		map.put("msg","ConsumerService");
		map.put("data",data);
		return map.toString();
	}

}
