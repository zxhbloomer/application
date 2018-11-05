package com.main.demo.vqo;

import com.main.demo.annotation.GreaterValue;
import com.main.demo.annotation.LessValue;
import com.main.demo.annotation.EqualCondition;
import com.main.demo.annotation.LikeCondition;
import lombok.Data;

import java.util.Date;

@Data
public class SysResourceVqo extends BasicVqo {

	/** 资源排序号 **/
	@LikeCondition
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
	@EqualCondition
	private String resourceIcon;

	@GreaterValue(value = "create_time")
	private Date createTimeGreater;
	@LessValue(value = "create_time")
	private Date createTimeLess;

}
