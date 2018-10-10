package com.main;

import lombok.Data;

@Data
public class Person implements Comparable<Person>{

	public Person(String username, String password, Integer age) {
		this.username = username;
		this.password = password;
		this.age = age;
	}

	private String username;
	private String password;
	private Integer age;

	@Override
	public int compareTo(Person o) {
		return this.age - o.getAge();
	}
}
