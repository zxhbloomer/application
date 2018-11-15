package com.main;

import com.main.entity.User;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.async.RedisAsyncCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisApplicationTest {

	/**
	 * 注入String类型的模板
	 */
	@Autowired
	private StringRedisTemplate stringTemplate;

	/**
	 * 注入自定义类型的操作模板
	 */
	@Autowired
	private RedisTemplate<String,Serializable> redisTemplate;


	/**
	 * 常用操作
	 */
	@Test
	public void test01(){
//		//Set
//		stringTemplate.opsForValue().set("username:simple:code:15277578023","126354",60,TimeUnit.SECONDS);
		//Get
		String code = stringTemplate.opsForValue().get("username:simple:code:15277578023");

		System.err.println(code);

//		//SetObject
//		redisTemplate.opsForValue().set("obj:user:0", new User("梁金龙","1397856722",true,21),60,TimeUnit.SECONDS);
//		//GetObject
//		final User user = (User) redisTemplate.opsForValue().get("obj:user:0");
	}

	/**
	 * 线程安全测试
	 */
	@Test
	public void test02() throws Exception{
		ExecutorService exec = Executors.newFixedThreadPool(1000);
		IntStream.range(0,1000).forEach(i ->
				exec.execute(() -> stringTemplate.opsForValue().increment("sum",1))
		);
		exec.shutdown();
		//休眠阻止JUnit终结JVM
		Thread.sleep(5000);
	}

	/**
	 * 订阅 和 发布
	 */
	@Test
	public void test03() throws Exception{
		ExecutorService exec = Executors.newFixedThreadPool(200);
		for(int i=0;i<200;i++){
			exec.execute(() -> {
				while (true){
					//订阅:subscribe news,发布: publish news "msg"
					stringTemplate.convertAndSend("news",new Random().nextLong() + "");
				}
			});
		}
		Thread.sleep(10000);
	}

	/**
	 * 测试使用Lettuce
	 */
	@Test
	public void test004() throws Exception{
		/** 构建连接 **/
		RedisURI redisURI = RedisURI.create("redis://39.108.82.13");
		redisURI.setPassword("mypassword");
		RedisClient client = RedisClient.create(redisURI);
		RedisAsyncCommands<String,String> commands = client.connect().async();
		/** 同步操作 **/
		RedisFuture<String> future = commands.get("username");
		//设置超时
		String value = future.get(10, TimeUnit.SECONDS);
		System.out.println("同步 : " + value);
		/** 异步操作 **/
		future.thenAccept(new Consumer<String>() {
			@Override
			public void accept(String value) {
				System.out.println("异步 : " + value);
			}
		});

	}


}
