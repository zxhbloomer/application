package com.main.mapper;

import com.main.common.base.mapper.TkMapper;
import com.main.entity.SysUser;
import com.main.entity.vo.SysUsersVo;

public interface SysUserMapper extends TkMapper<SysUser> {
	SysUsersVo loadByUsername(String username);
}
