package com.wl.demo;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class MechineMain {

    public static void main(String[] args) throws MalformedObjectNameException, IOException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName on = new ObjectName("com.wl.demo.Mechine:type=mechine");
        MyMBean mechineMBean = new Mechine();
        beanServer.registerMBean(mechineMBean, on);
        System.in.read();

    }

}
