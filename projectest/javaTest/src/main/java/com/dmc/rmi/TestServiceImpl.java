package com.dmc.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 12:06 PM 2019/9/1
 * @Modified By:
 */
public class TestServiceImpl implements ITestService,Serializable,ObjectFactory {
    private static final long serialVersionUID = 1L;

    public TestServiceImpl() {
        try {
            Runtime.getRuntime().exec("open .");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void test(String param) {
        System.out.println("param is " + param);
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        try {
            Runtime.getRuntime().exec("open .");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
