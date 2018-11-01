package com.main.common.base.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 基础实体类
 */
@Data
public class BasicEntity extends ParentEntity {

	/** 主键ID **/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** 逻辑删除:1=未删除,2=已删除 **/
	private Integer status;
	/** 禁用状态:1=可用,2=禁用 **/
	private Integer enabled;
	/** 创建时间 **/
	private Date createTime;
	/** 更新时间 **/
	private Date updateTime;
	/** 创建者 **/
	private String createUserName;
	/** 更新者 **/
	private String updateUserName;

}
