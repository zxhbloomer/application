package com.main.manager;


import com.main.common.base.mapper.TkMapper;
import com.main.entity.SysPerson;

import java.util.List;

/**
 * Slave数据源:用户服务
 */
public interface SysPersonManager extends TkMapper<SysPerson> {

    List<SysPerson> findAll();

}
