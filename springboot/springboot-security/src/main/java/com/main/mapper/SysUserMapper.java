package com.main.mapper;

import com.main.common.base.mapper.BasicMapper;
import com.main.entity.SysUser;
import com.main.entity.vo.SysUsersVo;

public interface SysUserMapper extends BasicMapper<SysUser> {
	SysUsersVo loadByUsername(String username);
}
