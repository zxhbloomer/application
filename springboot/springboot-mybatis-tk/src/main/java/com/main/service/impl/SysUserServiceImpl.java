package com.main.service.impl;

import com.main.common.base.service.impl.BasicServiceImpl;
import com.main.entity.SysUser;
import com.main.mapper.SysUserMapper;
import com.main.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BasicServiceImpl<SysUserMapper,SysUser> implements SysUserService {

}
