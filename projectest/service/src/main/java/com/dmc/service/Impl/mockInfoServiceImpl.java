package com.dmc.service.Impl;


import com.dmc.dao.TesttableMapper;
import com.dmc.dao.mockInfoMapper;

import com.dmc.dao.UserTableMapper;
import com.dmc.model.TesttableEntity;
import com.dmc.model.mockInfoEntity;
import com.dmc.service.TesttableService;
import com.dmc.service.mockInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@yuntu-inc.com
 * @Description
 * @Date: Created in 下午8:34 2017/12/13
 * @Modifyed By:
 */
@Service
public class mockInfoServiceImpl implements mockInfoService {

    private static Log log = LogFactory.getLog(mockInfoServiceImpl.class);

    @Autowired
    mockInfoMapper mockInfoMapper;

    @Autowired
    TesttableService testtableService;

    @Autowired
    TesttableMapper testtableMapper;

    @Autowired
    UserTableMapper userTableMapper;

    @Override
    @Transactional
    public Integer insert(mockInfoEntity entity, TesttableEntity entity2) {
        mockInfoMapper.insertSelective(entity);
        testtableMapper.insertSelective(entity2);
        return 1;
    }

    @Override
    public Integer batchInsert(List<TesttableEntity> entities) {
        testtableMapper.batchInsert(entities);
        return 1;
    }

    @Override
    public void MockInfoInsert(mockInfoEntity entity) {
        System.out.println("mockinfo table insert");
        mockInfoMapper.insert(entity);
    }

    @Override
    public void TestTableInsert(TesttableEntity entity) {
        System.out.println("test table insert");
        testtableMapper.insert(entity);
        throw new RuntimeException("try");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testTrans() {

        mockInfoEntity entity = new mockInfoEntity();
        entity.setAlias("none");
        entity.setBind(1234);
        entity.setCompleteUrl("baidu.com");
        entity.setDomain("domain");
        entity.setFilename("a.txt");
        entity.setJson("rua:rua");
        entity.setPort(4321);
        entity.setProto("rua");
        entity.setStatus(2);
        entity.setUrl("baidu.com");

        this.MockInfoInsert(entity);

        testtableService.tableInsert();
        throw new RuntimeException("asd");
    }
}
