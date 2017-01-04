package spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by niceday on 16/6/27.
 */
@Component
@Aspect
public class SpringAspect {

    @Around("methodsToBeProfiled() && target(spring.AspectedObject)")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        Class targetClass= pjp.getTarget().getClass();
        Field field = targetClass.getField("t");
        String s = (String)field.get(pjp.getTarget());
        System.out.println(s);
        Object[] args = pjp.getArgs();

        Class[] classes = new Class[pjp.getArgs().length];

        int arglen = args.length;

        for(int i=0;i<arglen;i++){
            classes[i] = args[i].getClass();
        }
        Method targetMethod = targetClass.getMethod(pjp.getSignature().getName(),classes);
        LiveApiLimit limit = targetMethod.getAnnotation(LiveApiLimit.class);
        if(null != limit && limit.limit() > 0){
            System.out.println("good");
        }

        System.out.println();
        try {
            System.out.println("in time");
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }

    @Pointcut("execution(public * *(..))")
    public void methodsToBeProfiled(){}

//
//    @Pointcut("execution(public * *(..))")
//    public void mk(){
//    }
//
//    @Before(value="mk() && args(name,..)")
//    public void foundBefore(String name){
//
//        System.out.println(name);
//    }
//
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/aspect.xml");
        AspectedObject a = (AspectedObject)applicationContext.getBean("aspectedObject");
        a.doSomething("tom");
        a.doSomething2();
    }
//
//    @AfterReturning("mk() && args(name) && @annotation(liveApiLimit)")
//    public void foundAfter(String name,LiveApiLimit liveApiLimit){
//        System.out.println(liveApiLimit.limit());
//        System.out.println("【守护者】抓住了猴子,守护者审问出了猴子的名字叫“"+name+"”...");
//    }

}
