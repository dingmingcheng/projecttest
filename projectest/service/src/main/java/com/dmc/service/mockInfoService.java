package com.dmc.service;

import com.dmc.model.TesttableEntity;
import com.dmc.model.mockInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@yuntu-inc.com
 * @Description
 * @Date: Created in 下午8:34 2017/12/13
 * @Modifyed By:
 */
public interface mockInfoService {
    Integer insert(mockInfoEntity entity, TesttableEntity entity2);

    Integer batchInsert(List<TesttableEntity> entities);

    void testTrans();

    void MockInfoInsert(mockInfoEntity entity);

    void TestTableInsert(TesttableEntity entity);
}
