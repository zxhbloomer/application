package com.main.common.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * 基础实体类
 */
@Data
public class SystemBaseEntity extends BaseEntity {

	/** 禁用状态:0=禁用(false),1=可用(true) **/
	private Boolean enabled;
	/** 创建者 **/
	private String createUserName;
	/** 更新者 **/
	private String updateUserName;

}
