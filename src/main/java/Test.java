import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.T;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xinz on 2016/6/12.
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) throws ParseException, InterruptedException, ExecutionException {
//        logger.error("testfff");

        Stopwatch stopwatch = Stopwatch.createStarted();
        RateLimiter r = RateLimiter.create(10);
        for(int i=0;i<10;i++){
            System.out.println(r.tryAcquire(5));
            if(i==0){
                Thread.sleep(700);
            }
        }
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
