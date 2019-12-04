package com.dmc.rmi;

import java.rmi.Remote;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 12:05 PM 2019/9/1
 * @Modified By:
 */
public interface ITestService extends Remote {

    void test(String param);
}
