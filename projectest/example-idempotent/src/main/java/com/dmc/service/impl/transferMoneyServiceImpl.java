package com.dmc.service.impl;

import com.dmc.dao.TesttableMapper;
import com.dmc.model.TesttableEntity;
import com.dmc.service.transferMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 上午9:53 2017/12/29
 * @Modifyed By:
 */
@Service
public class transferMoneyServiceImpl implements transferMoneyService {

    @Autowired
    TesttableMapper testtableMapper;

    @Override
    @Transactional
    public String none(Long id, Integer money) {
        Long startTime = System.currentTimeMillis();
        Integer currentMoney = testtableMapper.selectByPrimaryKey(id.intValue()).getNum();

        System.out.println("currentMoney is ......" + currentMoney);
        currentMoney -= money;
        TesttableEntity entity = new TesttableEntity();
        entity.setId(id.intValue());
        entity.setNum(currentMoney);
        testtableMapper.updateByPrimaryKeySelective(entity);
        Long endTime = System.currentTimeMillis();
        return String.valueOf(endTime - startTime);
    }

    @Override
    public Boolean optimistic(Long id, Integer money) {
        return null;
    }

    @Override
    public Boolean redis(Long id, Integer money) {
        return null;
    }

    @Override
    public Boolean index(Long id, Integer money) {
        return null;
    }
}
