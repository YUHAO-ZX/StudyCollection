import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import limiter.MyRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
/**
 * Created by xinz on 2016/6/12.
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) throws ParseException, InterruptedException, ExecutionException {
        List<String> list = new ArrayList<String>();
        for(int i=0;i<15;i++){
            list.add(""+i);
        }

        List<String> subString = list.subList(0,5);
        System.out.println(subString.size());
    }
}
