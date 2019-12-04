package com.dmc.dao;

import com.dmc.model.mockInfoEntity;
import org.springframework.stereotype.Component;


public interface mockInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(mockInfoEntity record);

    int insertSelective(mockInfoEntity record);

    mockInfoEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(mockInfoEntity record);

    int updateByPrimaryKeyWithBLOBs(mockInfoEntity record);

    int updateByPrimaryKey(mockInfoEntity record);
}
