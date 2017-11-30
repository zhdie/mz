package com.deloitte.hk.mizuho.dao;

import com.deloitte.hk.mizuho.dao.model.T102menu;

public interface T102menuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(T102menu record);

    int insertSelective(T102menu record);

    T102menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T102menu record);

    int updateByPrimaryKey(T102menu record);
}