package test;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.*;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import junit.framework.TestCase;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xinz on 16/10/10.
 */
public class TotalTest extends CommonTestMethodBase {

    @Test
    public void tests(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<20;i++){
            System.out.println("#### "+(20+i)+".");
        }
    }
    @Test
    public void testDrools(){
        String str = "";
        str += "package org.drools.compiler \n";
        str += "import java.math.BigDecimal; \n";
        str += "global java.util.List list \n";
        str += "rule rule1 \n";
        str += "    dialect \"java\" \n";
        str += "when \n";
        str += "    $bd : BigDecimal() \n";
        str += "    eval( $bd.compareTo( BigDecimal.ZERO ) > 0 ) \n";
        str += "then \n";
        str += "    list.add( $bd ); \n";
        str += "end \n";

        KnowledgeBase kbase = loadKnowledgeBaseFromString( str );
        StatefulKnowledgeSession ksession = createKnowledgeSession( kbase );
        List list = new ArrayList();
        ksession.setGlobal( "list",
                list );
        ksession.insert( new BigDecimal( 1.5 ) );

        ksession.fireAllRules();

        System.out.println(list.get(0));
        assertEquals( 1,
                list.size() );
        assertEquals( new BigDecimal( 1.5 ),
                list.get( 0 ) );
    }
    @Test
    public void testGC(){

    }
    @Test
    public void testAtomicBoolean() throws InterruptedException {
        AtomicBoolean b = new AtomicBoolean(false);
        String t = null;
        int i = 0;
        long start = System.nanoTime();
//        while(i < 1000000000){
//            synchronized (this){
//                if(t == null){
//                    t = "1222";
//                }
//            }
//            if(i ++ %100000000 == 0){
//                System.out.println(t.hashCode()+" "+(System.nanoTime() - start));
//            }
//        }

        i=0;
        start = System.nanoTime();
        while(true){
            if(b.compareAndSet(false,true)){
                t = "1222";
            }
            if(i ++ %100000000 == 0){
                System.out.println(t.hashCode()+" "+(System.nanoTime() - start));
            }
        }

    }

    @Test
    public void testList(){
        List<String> arrayList = new ArrayList<String>();
        List<String> linkList = new LinkedList<String>();
        long start = System.nanoTime();
        for(int i=0;i<=1000000;i++){
            linkList.add(i+"t");
            if(i % 100000 == 0){
                System.out.println(i+" time:"+(System.nanoTime() - start));
            }
        }

        start = System.nanoTime();
        for(int i=0;i<=1000000;i++){
            arrayList.add(i+"m");
            if(i % 100000 == 0){
                System.out.println(i+" time:"+(System.nanoTime() - start));
            }
        }

    }

    @Test
    public void testSet0(){

        Integer a = 200;
        a = a & 0xff;

        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        Integer rs = a << 23;
        System.out.println(Integer.toBinaryString(rs));
        System.out.println(rs);
//        System.out.println("000000000000000000000000".length());
    }
    @Test
    public void testFoo(){
        RangeMap<Integer,Integer>
        rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(0, 500), 0);
        rangeMap.put(Range.openClosed(500, 1000), 1);
        rangeMap.put(Range.openClosed(1000, 2000), 2);
        rangeMap.put(Range.openClosed(2000, 3000), 3);
        rangeMap.put(Range.openClosed(3000, 4000), 4);
        rangeMap.put(Range.openClosed(4000, 5000), 5);
        rangeMap.put(Range.openClosed(5000, Integer.MAX_VALUE), 6);

        System.out.println(rangeMap.get(1000));
        System.out.println(rangeMap.get(500));
        System.out.println(rangeMap.get(0));
        System.out.println(rangeMap.get(490));
        System.out.println(rangeMap.get(5001));
        System.out.println(rangeMap.get(4009));
        System.out.println(rangeMap.get(3009));

    }
    @Test
    public void testLocalCache() throws ExecutionException, InterruptedException {
        final LoadingCache<String,Long> timeCache
                //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                        //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                        //设置缓存容器的初始容量为10
                .initialCapacity(100000)
                        //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(10000000)
                        //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<String, Long>() {
                            @Override
                            public Long load(String key) throws Exception {
                                return System.currentTimeMillis();
                            }
                        }
                );

        for(int i=0;i<10000;i++){
            final int ii = i;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        timeCache.get(ii+"");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();

        }
//        while(true){
//            Long rs = studentCache.getIfPresent("ttt");
//            if(null == rs){
//                System.out.println("rs is null");
//                break;
//            }
//            System.out.println(rs);
//            Thread.sleep(1000);
//        }
    }


    AtomicLong stat = new AtomicLong();
    @Test
    public void testSche() throws InterruptedException {

        final Map<String,Long> sendTime = new HashMap<String, Long>();
        final BlockingQueue<String> queue = new ArrayBlockingQueue(10000);
        for(int i=0;i<10000;i++){
            sendTime.put("clientId-msgId"+i,System.currentTimeMillis());
        }

        Thread consum = new Thread(new Runnable() {
            public void run() {
                boolean isRunning = true;
                while(isRunning){
                    String client = queue.poll();
                    if(null == client){
                        continue;
                    }
                    if(System.currentTimeMillis() - sendTime.get(client) < 2){
                        long rs = stat.getAndIncrement();
                        System.out.println(rs);
                    }


                }
            }
        });

        consum.start();

        Thread prod = new Thread(new Runnable() {
            public void run() {

            }
        });

        for(int i=0;i<10000;i++){
            try {
                queue.put("clientId-msgId"+i);
                long random = (long)(Math.random()*4);
                System.out.println(random);
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(10000);
        System.out.println(stat);
    }
    @Test
    public void test6(){
        long timeout = TimeUnit.MILLISECONDS.toNanos(1);
        System.out.println(timeout);
    }
    @Test
    public void test5() throws IOException {
        System.out.println(StopWatch.class.getResource("").getPath());
    }
    @Test
    public void test1() throws InterruptedException {
        for(int i=0;;i++){
            String s = new String("df"+i);
            System.out.println(s);
            Thread.sleep(100);
        }
    }

    @Test
    public void test2() throws InterruptedException {
        Object t = new Object();
        synchronized (t){
            System.out.println("t");
            t.wait();
            System.out.println("tt1");
        }
    }

    @Test
    public void test3(){
        Set<String> s = new CopyOnWriteArraySet<String>();
        s.add("123");
        Iterator iterator = s.iterator();
        while (iterator.hasNext()){
            s.remove("123");
            s.add("3434");
            iterator.next();
        }

    }
}
