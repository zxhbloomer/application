package com.main.common.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.main.common.base.entity.BaseEntity;
import com.main.common.base.entity.SystemBaseEntity;
import com.main.common.base.mapper.PlusMapper;
import com.main.common.base.service.BaseService;

public class BaseServiceImpl<M extends PlusMapper<T>,T extends BaseEntity> extends ServiceImpl<M,T> implements BaseService<T> {

}
