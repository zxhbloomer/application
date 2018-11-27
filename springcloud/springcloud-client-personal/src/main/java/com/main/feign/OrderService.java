package com.main.feign;

import com.jack.springcloud.bean.User;
import com.main.feign.fallback.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-client-order",fallback = OrderServiceFallback.class)
public interface OrderService {

	/**
	 * 发送购物请求给订单服务器
	 * @param goodsName 商品名称
	 * @return 处理状态
	 */
	@RequestMapping(value = "/order/receiverGoods",method = RequestMethod.GET)
	String receiverOrder(@RequestParam(value = "goodsName") String goodsName);

	/**
	 * FeignGet多参数传递 (经过测试,Get请求无法传递数组类型或者是ArrayList的Bean对象,只能传递单个)
	 * @param user 实体对象
	 */
	@RequestMapping(value = "/order/add", method = RequestMethod.GET)
	String addUser(User user);

	/**
	 * FeignPost多参数传递
	 * @param user 实体对象
	 */
	@RequestMapping(value = "/order/update", method = RequestMethod.POST)
	String updateUser(@RequestBody User user);


}
