package com.main.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "用户实体",description = "类用户详细信息")
public class Person {

	@ApiModelProperty(value="ID编号",name="id",required = true)
	private Long id;
	@ApiModelProperty(value="用户名",name="username",required = true)
	private String username;
	@ApiModelProperty(value="密码",name="password")
	private String password;
	@ApiModelProperty(value="年龄",name="age")
	private Integer age;
	@ApiModelProperty(value="性别",name="sex")
	private Boolean sex;

	@ApiModelProperty(value="游戏账号",name="gameAccount")
	private String[] gameAccount;

}
