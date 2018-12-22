package com.main.service;

import com.main.common.base.service.BaseService;
import com.main.entity.SysResource;

import java.util.List;

public interface SysResourceService extends BaseService<SysResource> {
    List<SysResource> selectSimpleAll();
}
