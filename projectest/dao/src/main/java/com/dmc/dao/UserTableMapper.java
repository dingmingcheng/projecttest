package com.dmc.dao;

import com.dmc.model.UserTable;

public interface UserTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTable record);

    int insertSelective(UserTable record);

    UserTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTable record);

    int updateByPrimaryKey(UserTable record);
}