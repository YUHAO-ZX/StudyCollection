package jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by xinz on 2016/8/17.
 */
public class HelloAgent {
    public static void main(String[] args) {
        try {
            System.out.println(String.class.getName());
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();

            ObjectName helloName = new ObjectName("zhangxin:name=HelloWorld");

            server.registerMBean(new Hello(),helloName);

            ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");

            HtmlAdaptorServer adaptor = new HtmlAdaptorServer();

            server.registerMBean(adaptor,adapterName);

            adaptor.start();

            System.out.println("start.........");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }

    }
}
