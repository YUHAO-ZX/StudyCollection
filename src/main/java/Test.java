import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import test.T;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by xinz on 2016/6/12.
 */
public class Test {
    static final String UGC = "UGC";
    static final String VIDEO = "VIDEO";
    private static ExecutorService executor = Executors.newCachedThreadPool();
    public static void main(String[] args) throws ParseException, InterruptedException, ExecutionException {
        List<GlobalRequestTask> subTasks = new ArrayList<GlobalRequestTask>();
        subTasks.add(new GlobalRequestTask(UGC));
        subTasks.add(new GlobalRequestTask(VIDEO));

        List<Future<Object>> rs = executor.invokeAll(subTasks, 2500, TimeUnit.MILLISECONDS);
        long start = System.currentTimeMillis();
        for(Future<Object> ele : rs){
            Object object = ele.get();
            System.out.println((String)object);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static class GlobalRequestTask implements Callable<Object> {

        String type;
        GlobalRequestTask(String type){
            this.type = type;
        }
        @Override
        public Object call() throws Exception {
            if(type.equals(UGC)){
                System.out.println("in ugc");
                Thread.sleep(1000);
            }else if(type.equals(VIDEO)){
                Thread.sleep(2000);
                System.out.println("in video");
            }
            return "tt";
        }
    }

}
