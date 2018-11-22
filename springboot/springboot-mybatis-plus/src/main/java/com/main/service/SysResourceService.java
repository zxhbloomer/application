package com.main.service;

import com.main.common.base.service.BasicService;
import com.main.entity.SysResource;

import java.util.List;

public interface SysResourceService extends BasicService<SysResource> {
    List<SysResource> selectSimpleAll();
}
