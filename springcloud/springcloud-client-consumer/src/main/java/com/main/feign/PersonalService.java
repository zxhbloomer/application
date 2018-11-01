package com.main.feign;

import com.main.feign.fallback.PersonalServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-client-personal",fallback = PersonalServiceFallback.class)
public interface PersonalService {

	@RequestMapping(value = "/personal/receiverMessage",method = RequestMethod.GET)
	String receiverMessage(@RequestParam(value = "message")  String message);

}
