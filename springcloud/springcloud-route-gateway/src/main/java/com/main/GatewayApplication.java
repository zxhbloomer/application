package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Personal : http://localhost:9501/service/user/personal/buyGoods?goodsName=iPhone&token=123
 * Order 	: http://localhost:9501/service/indent/order/receiverGoods?goodsName=iPhone&token=123
 * Consumer : http://localhost:9501/service/manage/consumer/sendMessage?order=iPhone&token=123
 */
@SpringCloudApplication
@ComponentScan(value = {"com.main","com.config"}) //扫描一些公共的配置(还要把自己也扫进来哦)
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
