package com.main.common.base.mapper;

import com.main.common.base.entity.BaseEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TkMapper<T extends BaseEntity> extends Mapper<T>,MySqlMapper<T>{

}
