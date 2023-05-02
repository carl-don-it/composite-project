package com.don.demo.basic.mbean;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * todo
 *
 * @author Carl Don
 * @Date 2021/1/15  10:34
 * @Version 1.0
 */
public class MbeanTest {

    public static void main(String[] args) throws InterruptedException {
        try {
            ObjectName objectName = new ObjectName("com.baeldung.tutorial:type=basic,name=game");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(new Game(), objectName);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e) {
            // handle exceptions
        }
        while (true) {
            Thread.sleep(10000);
        }
    }
}
