package com.main.mapper;

import com.main.common.base.mapper.PlusMapper;
import com.main.entity.SysResource;

import java.util.List;

public interface SysResourceMapper extends PlusMapper<SysResource> {
	List<SysResource> selectSimpleAll();
}
