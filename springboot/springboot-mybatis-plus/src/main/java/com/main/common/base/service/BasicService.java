package com.main.common.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.main.common.base.entity.BasicEntity;

public interface BasicService <T extends BasicEntity> extends ParentService,IService<T> {

}
