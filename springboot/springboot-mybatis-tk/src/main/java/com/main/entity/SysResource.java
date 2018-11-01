package com.main.entity;

import com.main.common.base.entity.BasicEntity;
import lombok.Data;

/**
 *  系统资源表
 */
@Data
public class SysResource extends BasicEntity {

	/** 资源排序号 **/
	private Integer orderNo;
	/** 资源等级 **/
	private Integer resourceLevel;
	/** 资源上级ID **/
	private Long resourceParent;
	/** 资源名称 **/
	private String resourceName;
	/** 资源描述 **/
	private String resourceDesc;
	/** 资源路径 **/
	private String resourcePath;
	/** 资源图标 **/
	private String resourceIcon;

}
