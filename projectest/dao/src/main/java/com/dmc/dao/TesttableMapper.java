package com.dmc.dao;

import com.dmc.model.TesttableEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface TesttableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TesttableEntity record);

    int insertSelective(TesttableEntity record);

    TesttableEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TesttableEntity record);

    int updateByPrimaryKey(TesttableEntity record);

    int batchInsert(@Param("entities") List<TesttableEntity> entities);
}