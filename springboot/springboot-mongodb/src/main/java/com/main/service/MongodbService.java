package com.main.service;

import com.main.entity.Person;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MongodbService {

	@Autowired
	MongoTemplate template;

	private static final String collectionName = "demo";

	/**
	 * 插入
	 */
	public Boolean insert(Person p){
		p.setCreateTime(new Date());
		template.insert(p,collectionName);
		return true;
	}

	/**
	 * 查询全部
	 */
	public List<Person> findAll(){
		return template.findAll(Person.class,collectionName);
	}

	/**
	 * 根据ID查询
	 */
	public Person findById(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		return template.findOne(query,Person.class,collectionName);
	}

	/**
	 * 根据主键更新对象(如果值是null也会设置成null)
	 */
	public UpdateResult updateById (Person p){
		//先查询出来
		Query query = new Query(Criteria.where("_id").is(p.get_id()));
		Update update = new Update();

		if(p != null){
			if(p.getUsername() != null && p.getUsername().length() != 0){
				update.set("username",p.getUsername());
			}
			if(p.getPassword() != null && p.getPassword().length() != 0){
				update.set("password",p.getPassword());
			}
			if(p.getAge() != null){
				update.set("age",p.getAge());
			}
			if(p.getCreateTime() != null){
				update.set("createTime",p.getCreateTime());
			}
		}

		//更新查询出来的第一条数据
		return template.updateFirst(query,update,Person.class,collectionName);
	}

	/**
	 * 根据条件删除
	 */
	public DeleteResult delete(Person p){
		return template.remove(p,collectionName);
	}

	/**
	 * 根据ID删除
	 */
	public DeleteResult deleteById(String id){
		//FindOne
		Person person = findById(id);
		//DeleteByCondition
		return delete(person);
	}

}
