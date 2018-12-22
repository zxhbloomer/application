package com.main.entity;

import com.main.common.base.entity.SystemBaseEntity;
import lombok.Data;

/**
 * 用户映射权限表
 */
@Data
public class SysUserRole extends SystemBaseEntity {

	/** 角色ID **/
	private Long roleId;
	/** 用户ID **/
	private Long userId;

}
