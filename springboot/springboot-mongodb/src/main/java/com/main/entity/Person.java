package com.main.entity;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

@Data
public class Person implements Serializable {

	private static final long serialVersionUID = 8596392701331679567L;

	/** Mongodb主键 **/
	private ObjectId _id;

	private String username;
	private String password;
	private Integer age;

	private Date createTime;
	private Date updateTime;
}
