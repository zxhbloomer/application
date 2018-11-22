package com.main.service.impl;

import com.main.common.base.service.impl.BasicServiceImpl;
import com.main.mapper.SysResourceMapper;
import com.main.entity.SysResource;
import com.main.service.SysResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysResourceServiceImpl extends BasicServiceImpl<SysResourceMapper,SysResource> implements SysResourceService {

    @Override
    public List<SysResource> selectSimpleAll() {
        return baseMapper.selectSimpleAll();
    }
}
