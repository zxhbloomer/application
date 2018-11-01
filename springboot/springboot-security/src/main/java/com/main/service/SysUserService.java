package com.main.service;

import com.main.common.base.service.BasicService;
import com.main.entity.SysUser;
import com.main.entity.vo.SysUsersVo;

public interface SysUserService extends BasicService<SysUser> {
	SysUsersVo loadByUsername(String username);
}
