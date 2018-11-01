package com.main.common.base.service;


import java.util.List;

public interface BasicService<T> extends ParentService{

	/** list **/
	List<T> listAll();
	List<T> list(T t);
	List<T> listByExample(Object o);
	T getOne(T t);
	T getOneByExample(Object o);
	T getByPrimaryKey(Object o);
	int getCount(T t);
	int getCountByExample(Object o);
	/** remove **/
	int remove(T t);
	int removeByPrimaryKey(Object o);
	int removeByExample(Object o);
	/** update **/
	int updateByPrimaryKeySelective(T t);
	int updateByPrimaryKey(T t);
	int updateByExample(T t, Object o);
	int updateByExampleSelective(T t, Object o);
	/** save **/
	int save(T t);
	int saveList(List<T> list);
	int saveSelective(T t);
	int saveUseGeneratedKeys(T t);
	
}
