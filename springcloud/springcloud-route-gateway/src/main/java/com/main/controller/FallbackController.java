package com.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	@GetMapping("/fallback")
	public String fallback() {
		return "Gateway[manage]熔断器处理";
	}
}
