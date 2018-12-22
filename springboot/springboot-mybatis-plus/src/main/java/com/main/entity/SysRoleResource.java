package com.main.entity;

import com.main.common.base.entity.SystemBaseEntity;
import lombok.Data;

/**
 * 权限映射资源表
 */
@Data
public class SysRoleResource extends SystemBaseEntity {

	/** 角色ID **/
	private Long roleId;
	/** 资源ID **/
	private Long resourceId;
	/** 此角色允许以什么Request方法来访问此资源 **/
	private Integer methods;

}
