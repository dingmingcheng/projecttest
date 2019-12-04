package com.dmc.service.Impl;

import com.dmc.dao.UserTableMapper;
import com.dmc.model.UserTable;
import com.dmc.service.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午4:28 2018/2/9
 * @Modifyed By:
 */
@Service
public class UserTableServiceImpl implements UserTableService {

    @Autowired
    UserTableMapper userTableMapper;

    @Override
    public void testInsert() {
        UserTable userTable = new UserTable();
        userTable.setName("rua");
        userTableMapper.insertSelective(userTable);
    }
}
