package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@SpringBootApplication
public class AdminClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminClientApplication.class, args);
	}

	@RequestMapping("/test")
	public String test(){
		String str = "";

		for(int i=0;i<10000;i++){
			str = str + Math.abs(new Random().nextLong());
		}

		return str;
	}
}
