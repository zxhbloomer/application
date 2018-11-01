package com.main.feign;

import com.main.feign.fallback.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
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


}
