package jmx;

import java.util.Map;

/**
 * Created by xinz on 2016/8/17.
 */
public interface HelloMBean {
    public Map<String,String> getName();

    public void setName(Map<String,String> name);

    public void printHello();

    public void printHello(Map<String,String> whoName);
}
