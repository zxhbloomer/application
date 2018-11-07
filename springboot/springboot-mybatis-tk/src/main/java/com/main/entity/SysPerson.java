package com.main.entity;

import com.main.common.base.entity.BasicEntity;
import lombok.Data;

/**
 * Slave数据源:用户表
 */
@Data
public class SysPerson extends BasicEntity {

	/** 用户名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 年龄 **/
	private Integer age;
	/** 性别:1=男,2=女 **/
	private Integer sex;
}
