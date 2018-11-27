package com.main.feign.fallback;

import com.jack.springcloud.bean.User;
import com.main.feign.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements OrderService {

	@Override
	public String receiverOrder(String goodsName) {
		return "购买 "+goodsName+" 失败";
	}


	@Override
	public String addUser(User user) {
		return "AddError";
	}

	@Override
	public String updateUser(User user) {
		return "UpdateError";
	}

}
