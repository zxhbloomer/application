package com.main.common.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 基础实体类
 */
@Data
public class BasicEntity extends ParentEntity {

	/** 主键ID **/
	@TableId(value="id",type = IdType.ID_WORKER)
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
