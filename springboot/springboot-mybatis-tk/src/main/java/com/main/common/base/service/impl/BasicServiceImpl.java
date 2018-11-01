package com.main.common.base.service.impl;


import com.main.common.base.entity.BasicEntity;
import com.main.common.base.mapper.BasicMapper;
import com.main.common.base.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BasicServiceImpl<M extends BasicMapper<T>,T extends BasicEntity> implements BasicService<T>{

	@Autowired
	private M baseMapper;

	@Override
	public List<T> listAll() {
		return baseMapper.selectAll();
	}

	@Override
	public List<T> list(T t) {
		return baseMapper.select(t);
	}

	@Override
	public List<T> listByExample(Object o) {
		return baseMapper.selectByExample(o);
	}

	@Override
	public T getOne(T t) {
		return baseMapper.selectOne(t);
	}

	@Override
	public T getOneByExample(Object o) {
		return baseMapper.selectOneByExample(o);
	}

	@Override
	public T getByPrimaryKey(Object o) {
		return baseMapper.selectByPrimaryKey(o);
	}

	@Override
	public int getCount(T t) {
		return baseMapper.selectCount(t);
	}

	@Override
	public int getCountByExample(Object o) {
		return baseMapper.selectCountByExample(o);
	}

	@Override
	public int remove(T t) {
		return baseMapper.delete(t);
	}

	@Override
	public int removeByPrimaryKey(Object o) {
		return baseMapper.deleteByPrimaryKey(o);
	}

	@Override
	public int removeByExample(Object o) {
		return baseMapper.deleteByExample(o);
	}

	@Override
	public int updateByPrimaryKeySelective(T t) {
		return baseMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByPrimaryKey(T t) {
		return baseMapper.updateByPrimaryKey(t);
	}

	@Override
	public int updateByExample(T t, Object o) {
		return baseMapper.updateByExample(t,o);
	}

	@Override
	public int updateByExampleSelective(T t, Object o) {
		return baseMapper.updateByExample(t,o);
	}

	@Override
	public int save(T t) {
		return baseMapper.insert(t);
	}

	@Override
	public int saveList(List<T> list) {
		return baseMapper.insertList(list);
	}

	@Override
	public int saveSelective(T t) {
		return baseMapper.insertSelective(t);
	}

	@Override
	public int saveUseGeneratedKeys(T t) {
		return baseMapper.insertUseGeneratedKeys(t);
	}
}
