package com.main.service.impl;

import com.main.common.base.service.impl.BasicServiceImpl;
import com.main.entity.SysUser;
import com.main.entity.vo.SysUsersVo;
import com.main.mapper.SysUserMapper;
import com.main.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BasicServiceImpl<SysUserMapper,SysUser> implements SysUserService {

	@Override
	public SysUsersVo loadByUsername(String username) {
		return baseMapper.loadByUsername(username);
	}
}
