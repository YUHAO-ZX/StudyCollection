package spring;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by niceday on 16/6/27.
 */
public class AspectedObject {
    public void doSomething(String name){
        System.out.println("in doing"+name);
    }

    public void doSomething2(){
        System.out.println("in doing 2");
    }
}
