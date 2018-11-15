package com.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ResourceController {

	/**
	 * 受保护的资源
	 */
	@RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
	public String getUserByUserId(@PathVariable("userId") String userId){
		log.info("GetUserById : {}",userId);
		return "Jack";
	}

	/**
	 * 不受保护的资源
	 */
	@RequestMapping(value = "/instance/{serviceId}",method = RequestMethod.GET)
	public String getInstanceByServiceId(@PathVariable("serviceId") String serviceId){
		log.info("GetInstanceByServiceId : {}",serviceId);
		return "HelloWorld";
	}

}
