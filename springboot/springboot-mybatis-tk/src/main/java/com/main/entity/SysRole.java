package com.main.entity;

import com.main.common.base.entity.SystemBaseEntity;
import lombok.Data;

/**
 *  系统角色表
 */
@Data
public class SysRole extends SystemBaseEntity {

	/** 角色名称 **/
	private String roleName;
	/** 角色描述 **/
	private String roleDesc;
	/** 角色权限 **/
	private String roleAuth;

}
