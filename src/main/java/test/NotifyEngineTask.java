package test;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xinz on 2016/12/29.
 */
@Component
public class NotifyEngineTask {



    private static final Logger logger = LoggerFactory.getLogger(NotifyEngineTask.class);

    private volatile boolean isRun = true;
    private volatile boolean sign = false;

    private List<String>  notifyEnv = new ArrayList<String>();
    private ReentrantLock lock = new ReentrantLock();
    /*
    通知引擎
     */
    public void notifyEngine(List<String> envs,String ruleIds){
        try {
            logger.info("NotifyEngineTask lock start <{}>,<{}>", JSON.toJSON(envs),ruleIds);
            lock.lock();
            logger.info("NotifyEngineTask lock acquired <{}>,<{}>", JSON.toJSON(envs),ruleIds);
            if(!notifyEnv.containsAll(envs)){
                notifyEnv.addAll(envs);
            }
            if(sign){
                logger.info("NotifyEngineTask exist task <{}>,<{}>", JSON.toJSON(envs),ruleIds);
                return;
            }
            sign = true;
        }catch (Throwable throwable){
            logger.error("NotifyEngineTask error <{}>,<{}>,<{}>", JSON.toJSON(envs), ruleIds, throwable);
        }finally {
            lock.unlock();
            logger.info("NotifyEngineTask lock unlocked <{}>,<{}>", JSON.toJSON(envs),ruleIds);
        }
    }

    @PostConstruct
    void init(){
        try{
            /*
            异步通知引擎节点更新任务
             */
            Thread task = new Thread(new Runnable() {
                @Override
                public void run() {
                    logger.info("NotifyEngineTask start.....");
                    while(true){
                        if(!isRun){
                            sleep(1000);
                        }
                        if(sign){
                            sign = false;
                            try {
                                Thread.sleep(3000);
                                logger.info("do business...................."+JSON.toJSONString(notifyEnv));
                            } catch (Throwable e) {
                            }
                        }else{
                            Thread.yield();
                        }

                    }
                }
            });
            task.start();
        }catch (Throwable throwable){
            logger.error("NotifyEngineTask stop thread <{}>", throwable);
        }
    }

    public void stop(){
        isRun = false;
    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
