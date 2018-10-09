package com.main.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体,用于序列化存储到Redis,需要实现序列化接口
 */
@Data
public class User implements Serializable {

	public User() {}

	public User(String username, String password, Boolean sex, Integer age) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
	}

	private static final long serialVersionUID = 8655851615465363473L;

	private String username;
	private String password;
	private Boolean sex;
	private Integer age;
}
