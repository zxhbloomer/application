package com.main.feign.fallback;

import com.main.feign.ConsumerService;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceFallback implements ConsumerService {
	@Override
	public String sendMessage(String order) {
		return "处理订单 " + order + " 失败";
	}
}
