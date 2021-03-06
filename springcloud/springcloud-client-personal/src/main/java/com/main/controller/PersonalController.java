package com.main.controller;

import com.jack.springcloud.bean.People;
import com.jack.springcloud.bean.RequestUser;
import com.jack.springcloud.bean.User;
import com.jack.springcloud.common.util.SimpleUserUtil;
import com.main.feign.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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



	@ApiImplicitParams({
			@ApiImplicitParam(name = "message", value = "处理信息", paramType = "query", required = true, dataType = "string"),
	})
	@ApiOperation(value = "接收消费服务的处理信息", notes = "ConsumerService处理完成之后会调用此方法来反馈用户的购物处理结果", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = String.class)
	@GetMapping("/receiverMessage")
	public String receiverMessage(@RequestParam String message){
		log.info("购物处理结果 = {}",message);
		return success("ReceivedSuccess");
	}


	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsName", value = "物品名称", paramType = "query", required = true, dataType = "string"),
	})
	@ApiOperation(value = "购买物品", notes = "用户的购物请求", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = String.class)
	@GetMapping("/buyGoods")
	public String buyGoods(@RequestParam String goodsName){
		log.info("购买物品 : goodsName = {}",goodsName);
		String result = orderService.receiverOrder(goodsName);
		return success(result);
	}

	private String success(String data){
		Map map = new HashMap<>();
		map.put("code",200);
		map.put("msg","PersonalService");
		map.put("data",data);
		return map.toString();
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

}
