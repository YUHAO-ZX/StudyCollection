package redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinz on 2016/6/12.
 */
public class RHashMap {
    public final static Jedis jedis = RedisOperation.getJedis();

    public static void main(String[] args) {
//        hmget();
        hmset();
    }
    public static void hmget(){
        List<String> rs = jedis.hmget("test1","t","t1");
        System.out.println(rs == null);
        System.out.println(rs.size());
        System.out.println(rs.get(0));
        System.out.println(rs.get(1));
        System.out.println(rs.get(2));

    }
    public static void hmset(){
//        Map<String,String> param = new HashMap<String, String>();
        String msg = "00000000000000000000000000000000000000000000000000";


        System.out.println(msg);
        for(int i=0;i<600000;i++){
            jedis.hset("hello","t"+i,msg);
        }
//        String rs = jedis.hmset("test1", param);
//        System.out.println(rs);
//        Long rs1 = jedis.hincrBy("test1","t1",10L);
//        System.out.println(rs1);
//        List<String> rs2 = jedis.hmget("test1","t1");
//        System.out.println(rs2.get(0));
    }
}
