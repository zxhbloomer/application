package com.main.entity;

import com.main.common.base.entity.SystemBaseEntity;
import lombok.Data;

/**
 * Slave数据源:用户表
 */
@Data
public class SysPerson extends SystemBaseEntity {

	/** 用户名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 年龄 **/
	private Integer age;
	/** 性别:0=女,1=男 **/
	private Integer sex;

}
