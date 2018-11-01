package com.main.feign.fallback;

import com.main.feign.PersonalService;
import org.springframework.stereotype.Component;

@Component
public class PersonalServiceFallback implements PersonalService {
	@Override
	public String receiverMessage(String message) {
		return "发送订单消息 " + message + "失败";
	}
}
