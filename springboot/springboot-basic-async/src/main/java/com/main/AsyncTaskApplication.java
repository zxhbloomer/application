package com.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableAsync	//开启异步任务调用
@SpringBootApplication
public class AsyncTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncTaskApplication.class, args);
	}

	@Autowired
	private AsyncTask asyncTask;

	@GetMapping("/task")
	public String task() throws Exception{
		log.info("执行 Controller 任务");
		Long start = System.currentTimeMillis();
		asyncTask.doTaskOne();
		asyncTask.doTaskTwo();
		asyncTask.doTaskThree();
		log.info("完成 Controller 任务,耗时：{} 毫秒",System.currentTimeMillis() - start);
		return "success";
	}
}
