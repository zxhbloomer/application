package com.main.common.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.main.common.base.entity.BasicEntity;

public interface BasicMapper <T extends BasicEntity> extends ParentMapper,BaseMapper<T> {

}
