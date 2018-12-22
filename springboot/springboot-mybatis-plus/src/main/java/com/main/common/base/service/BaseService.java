package com.main.common.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.main.common.base.entity.BaseEntity;
import com.main.common.base.entity.SystemBaseEntity;

public interface BaseService<T extends BaseEntity> extends IService<T> {

}
