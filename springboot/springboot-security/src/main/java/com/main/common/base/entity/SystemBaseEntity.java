package com.main.common.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * 基础实体类
 */
@Data
public class SystemBaseEntity extends BaseEntity {

	/** 逻辑删除:0=已删除,1=未删除 **/
	private Boolean status;
	/** 禁用状态:0=禁用,1=可用 **/
	private Boolean enabled;
	/** 创建时间 **/
	private Date createTime;
	/** 更新时间 **/
	private Date updateTime;
	/** 创建者 **/
	private String createUserName;
	/** 更新者 **/
	private String updateUserName;

}
