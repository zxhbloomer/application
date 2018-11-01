package com.main.common.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.main.common.base.entity.BasicEntity;
import com.main.common.base.mapper.BasicMapper;
import com.main.common.base.service.BasicService;

public class BasicServiceImpl<M extends BasicMapper<T>,T extends BasicEntity> extends ServiceImpl<M,T> implements BasicService<T> {

}
