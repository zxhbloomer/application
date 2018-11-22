package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Personal : http://localhost:9501/service/user/personal/buyGoods?goodsName=iPhone&token=123
 * Order 	: http://localhost:9501/service/indent/order/receiverGoods?goodsName=iPhone&token=123
 * Consumer : http://localhost:9501/service/manage/consumer/sendMessage?order=iPhone&token=123
 */
@SpringCloudApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
