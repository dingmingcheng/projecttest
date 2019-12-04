package com.dmc.service.Impl;

import com.dmc.dao.TesttableMapper;
import com.dmc.model.TesttableEntity;
import com.dmc.service.TesttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午3:14 2018/2/8
 * @Modifyed By:
 */
@Service
public class TesttableServiceImpl implements TesttableService {

    @Autowired
    TesttableMapper testtableMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void tableInsert() {
        TesttableEntity entity2 = new TesttableEntity();
        entity2.setName("tn");
        entity2.setNum(123);
        testtableMapper.insert(entity2);
    }
}
