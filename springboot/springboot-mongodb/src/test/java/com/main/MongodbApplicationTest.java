package com.main;

import com.main.entity.Person;
import com.main.service.MongodbService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongodbApplication.class)
@Slf4j
public class MongodbApplicationTest {

	@Autowired
	MongodbService service;

	@Test
	public void test01(){

		Person p = new Person();
		p.setUsername("梁金龙");
		p.setPassword("1397856722");
		p.setAge(21);
		service.insert(p);
		System.out.println(p);

	}

	@Test
	public void test02(){
		System.out.println(service.findAll());
	}

	@Test
	public void test03(){
		System.out.println(service.findById("5bb030ee711d675f60727b83"));
	}

	@Test
	public void test04(){
		Person p = new Person();
		p.setUsername("天下无敌");
		p.set_id(new ObjectId("5bb030ee711d675f60727b83"));
		service.updateById(p);
	}

	@Test
	public void test05(){
		System.out.println(service.deleteById("5bb030ee711d675f60727b83"));
	}
}
