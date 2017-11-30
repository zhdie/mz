package com.deloitte.hk.mizuho.dao;

import java.util.List;

import com.deloitte.hk.mizuho.dao.model.T101user;
import com.deloitte.hk.mizuho.dao.model.T102menu;

public interface T101userMapper {
    int deleteByPrimaryKey(Long id);

    int insert(T101user record);

    int insertSelective(T101user record);

    T101user selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T101user record);

    int updateByPrimaryKey(T101user record);
	
	T101user getByUserid(String userid);
	
	List<T102menu> queryMenu(String userid);
	
	void delByUserid(String userid);
}