package com.main.service;

import com.main.common.base.service.BaseService;
import com.main.entity.SysUser;
import com.main.entity.vo.SysUsersVo;

public interface SysUserService extends BaseService<SysUser> {
	SysUsersVo loadByUsername(String username);
}
