package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@SpringBootApplication
public class DockerSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerSimpleApplication.class, args);
	}

	@RequestMapping("/index")
	public String index(){
		StringBuilder sb = new StringBuilder();

		for(int i=0;i<10000;i++){
			sb.append(Math.abs(new Random().nextLong()) + "\n\r");
		}

		return sb.toString();
	}
}
