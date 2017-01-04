package jmx;

import java.util.Map;

/**
 * Created by xinz on 2016/8/17.
 */
public class Hello implements HelloMBean{
    private Map<String,String> name;
    @Override
    public Map<String,String> getName() {
        return name;
    }

    @Override
    public void setName(Map<String,String> name) {
        this.name = name;
    }

    @Override
    public void printHello() {
        System.out.println("Hello world ,"+name);
    }

    @Override
    public void printHello(Map<String,String> whoName) {
        System.out.println("Hello world ,"+whoName);
    }
}
