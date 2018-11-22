package com.main.mapper;

import com.main.common.base.mapper.BasicMapper;
import com.main.entity.SysResource;

import java.util.List;

public interface SysResourceMapper extends BasicMapper<SysResource> {
	List<SysResource> selectSimpleAll();
}
