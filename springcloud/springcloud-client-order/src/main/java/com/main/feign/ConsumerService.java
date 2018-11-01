package com.main.feign;

import com.main.feign.fallback.ConsumerServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-client-consumer",fallback = ConsumerServiceFallback.class)
public interface ConsumerService {

	/**
	 * 发送订单给消费端
	 * @param order 订单
	 * @return 处理状态
	 */
	@RequestMapping(value = "/consumer/sendMessage",method = RequestMethod.GET)
	String sendMessage(@RequestParam(value = "order") String order);

}
