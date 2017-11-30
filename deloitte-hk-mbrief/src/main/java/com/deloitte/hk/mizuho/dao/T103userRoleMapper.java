package com.deloitte.hk.mizuho.dao;

import com.deloitte.hk.mizuho.dao.model.T103userRole;

public interface T103userRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(T103userRole record);

    int insertSelective(T103userRole record);

    T103userRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T103userRole record);

    int updateByPrimaryKey(T103userRole record);
}