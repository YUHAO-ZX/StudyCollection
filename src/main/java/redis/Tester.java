package redis;

import redis.clients.jedis.Jedis;
import java.util.concurrent.*;

/**
 * Created by xinz on 2016/5/31.
 */
public class Tester {
//    public static Jedis jedis = new Jedis("localhost");
    public final static Jedis jedis = RedisOperation.getJedis();
    public static int resource = 0;
    public static void main(String[] args) throws InterruptedException {
        //分布式事务锁
        lockTest();
    }


    public static void lockTest(){
        final String lock = "lock1";
        ExecutorService t = new ThreadPoolExecutor(2,1000,10000, TimeUnit.HOURS,new LinkedBlockingDeque<Runnable>());
        for(int i=0;i <=1000 ;i++){
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean locked = false;
                    try{

                        Jedis jedis = RedisOperation.getJedis();
                        if(acquireLock(lock,jedis,200)){
                            locked = true;
                            resource ++;
                            System.out.println(resource);
                        }else{
                            System.out.println("conflict");
                        }
                    }catch (Throwable t){
                        t.printStackTrace();
                    }finally {
                        if(locked)
                            releaseLock(lock,jedis);
                    }
                }
            });
            t.submit(th);
        }
    }
    //释放锁
    public static void releaseLock(String lock, Jedis jedis) {
        // 避免删除非自己获取得到的锁
        String lockValue = jedis.get(lock);
        if (lockValue != null) {
            jedis.del(lock);
        }
    }

    //获取锁
    public static boolean acquireLock(String lock, Jedis jedis,int expiredTime){
        // 1. 通过SETNX试图获取一个lock
        boolean success = false;
        long acquired = jedis.setnx(lock, lock);
        // SETNX成功，则成功获取一个锁
        if (acquired == 1) {
            jedis.expire(lock,expiredTime);
            success = true;
        }else{
            String remoteValue = jedis.get(lock);
            if(null != remoteValue){
                if(-1 == jedis.ttl(lock)){
                    jedis.del(lock);
                }
            }
        }
        return success;
    }

}
