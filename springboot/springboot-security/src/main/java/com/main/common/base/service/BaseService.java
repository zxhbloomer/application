package com.main.common.base.service;


import com.main.common.base.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

	/** list **/
	List<T> listAll();
	List<T> list(T t);
	List<T> listByExample(Object o);
	T getOne(T t);
	T getOneByExample(Object o);
	T getById(Object o);
	int getCount(T t);
	int getCountByExample(Object o);
	/** remove **/
	int remove(T t);
	int removeById(Object o);
	int removeByExample(Object o);
	/** update **/
	int updateByIdSelective(T t);
	int updateById(T t);
	int updateByExample(T t, Object o);
	int updateByExampleSelective(T t, Object o);
	/** save **/
	int save(T t);
	int saveList(List<T> list);
	int saveSelective(T t);
	int saveUseGeneratedKeys(T t);
	
}
