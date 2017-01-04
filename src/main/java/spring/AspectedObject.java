package spring;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by niceday on 16/6/27.
 */
@Component
public class AspectedObject {
    public String t = "123";
    @LiveApiLimit(limit = 200)
    public void doSomething(String name){
        System.out.println("in doing"+name);
    }

    public void doSomething2(){
        System.out.println("in doing 2");
    }

    public static void main(String[] args) {
        System.out.println(AspectedObject.class.getTypeName());

    }
}
