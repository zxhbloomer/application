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
		return "Hello";
	}
}
