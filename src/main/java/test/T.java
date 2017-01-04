package test;

import platform.SizeOfObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinz on 2016/6/22.
 */
public class T {
    private int a = 1;
    private int b = 2;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        int i = 0;
        Map<String,T> map = new HashMap<String,T>();
        while (true){
            T t = new T();
            t.a = i++;
            t.b = i;
            map.put(i+"",t);
        }
    }
}
