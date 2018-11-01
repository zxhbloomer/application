package com.mian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/getInfo")
	public String getInfo(){
		return "Hello";
	}

}
