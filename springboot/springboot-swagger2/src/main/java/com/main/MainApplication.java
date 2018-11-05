package com.main;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.main.annotation.EnableSimpleLoggerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * UI页面路径:
 * 		http://localhost:8080/doc.html
 * 		http://localhost:8080/swagger-ui.html
 */
@SpringBootApplication
@EnableSimpleLoggerFilter //启用注解导入配置
@EnableSwaggerBootstrapUI //启用SwaggerBootstrapUi提供的增强功能.详情请查看SwaggerBootstrapUi增强功能说明
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
