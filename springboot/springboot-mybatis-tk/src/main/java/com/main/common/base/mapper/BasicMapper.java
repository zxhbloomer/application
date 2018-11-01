package com.main.common.base.mapper;

import com.main.common.base.entity.BasicEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BasicMapper<T extends BasicEntity> extends ParentMapper,Mapper<T>,MySqlMapper<T>{

}
