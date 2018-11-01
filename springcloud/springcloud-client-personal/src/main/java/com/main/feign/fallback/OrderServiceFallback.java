package com.main.feign.fallback;

import com.main.feign.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements OrderService {

	@Override
	public String receiverOrder(String goodsName) {
		return "购买 "+goodsName+" 失败";
	}

}
