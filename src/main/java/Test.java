import limiter.MyRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.concurrent.*;
/**
 * Created by xinz on 2016/6/12.
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) throws ParseException, InterruptedException, ExecutionException {
//        RateLimiter r = RateLimiter.create(10);
        MyRateLimiter r = new MyRateLimiter(7);
        for(int i=0;i<20;i++){
            System.out.println(r.tryAcquire());
            Thread.sleep(100);
        }
    }
}
