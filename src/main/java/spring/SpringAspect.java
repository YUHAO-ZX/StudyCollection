package spring;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by niceday on 16/6/27.
 */
@Aspect
public class SpringAspect {
    @Pointcut("execution(public * *(..))")
    public void mk(){
    }

    @Before(value="mk() && args(name,..)")
    public void foundBefore(String name){
        System.out.println(name);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/aspect.xml");
        AspectedObject a = (AspectedObject)applicationContext.getBean("aspectedObject");
        a.doSomething("tom");
        a.doSomething2();
    }
    @AfterReturning("mk() && args(name)")
    public void foundAfter(String name){
        System.out.println("【守护者】抓住了猴子,守护者审问出了猴子的名字叫“"+name+"”...");
    }

}
