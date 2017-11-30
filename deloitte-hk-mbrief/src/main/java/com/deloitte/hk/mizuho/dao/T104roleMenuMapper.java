package com.deloitte.hk.mizuho.dao;

import com.deloitte.hk.mizuho.dao.model.T104roleMenu;

public interface T104roleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(T104roleMenu record);

    int insertSelective(T104roleMenu record);

    T104roleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T104roleMenu record);

    int updateByPrimaryKey(T104roleMenu record);
}