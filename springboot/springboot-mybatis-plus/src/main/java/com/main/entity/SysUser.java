package com.main.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.main.common.base.entity.SystemBaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户表
 */
@Data
public class SysUser extends SystemBaseEntity {

	/** 用户名 **/
	private String username;
	/** 昵称 **/
	private String name;
	/** 密码 **/
	private String password;
	/** 最后一次登录时间 **/
	private Date lastLoginTime;
	/** 退出登录时间 **/
	private Date outLoginTime;
	/** 登录IP **/
	private String loginIp;
	/** Email **/
	private String email;
	/** 证书 **/
	private Integer credentialsNonExpired;
	/** 账户是否过期 **/
	private Integer accountNonExpired;
	/** 是否锁定 **/
	private Integer accountNonLocked;

	@TableField(exist = false)
	/** 权限名称 **/
	private String roleName;
	@TableField(exist = false)
	/** 权限ID **/
	private Long roleId;

}
