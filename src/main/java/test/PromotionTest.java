package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.annotations.VisibleForTesting;
import com.jumei.parrot.service.util.ParrotBeanFactory;
import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xinz on 2016/12/7.
 */
public class PromotionTest extends TestCase {

    public static void main(String[] args) {
        Long t = 14888160001L;
                 long m = 1483412643L;
    }
    public void testttttt() throws InterruptedException {
        String t = "POP测试品牌请勿擅动01 满5件打8折,满6件打7折,满7件打6折".replaceAll("[^\\x00-\\xff]","xx");
        System.out.println(t.length());
    }
    public void testGetChar(){
        String[] t ;

        //BIG_ENDIAN    BIG_ENDIAN    BIG_ENDIAN
        ByteBuffer byteBuffer =
                ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);

        byteBuffer.put (0, (byte)0);
        byteBuffer.put (1, (byte)'H');
        byteBuffer.put (2, (byte)0);
        byteBuffer.put (3, (byte)'i');
        byteBuffer.put (4, (byte)0);
        byteBuffer.put(5, (byte) '!');
        byteBuffer.put(6, (byte) 0);
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getChar());
    }
    public void testAsCharBuff(){
        //BIG_ENDIAN    BIG_ENDIAN    BIG_ENDIAN
        ByteBuffer byteBuffer =
                ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        CharBuffer charBuffer = byteBuffer.asCharBuffer( );

        byteBuffer.put (0, (byte)0);
        byteBuffer.put (1, (byte)'H');
        byteBuffer.put (2, (byte)0);
        byteBuffer.put (3, (byte)'i');
        byteBuffer.put (4, (byte)0);
        byteBuffer.put(5, (byte)'!');
        byteBuffer.put(6, (byte)0);
        System.out.println(byteBuffer);
        System.out.println(charBuffer);
    }
    public void test直接分配内存(){
        //直接分配内存，非jvm堆栈，分配速度更快，只有ByteBuffer有次功能，应为最终IO是和ByteBuffer打交道
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);

    }
    public void testEndianOrder(){//获取本机字节顺序
        System.out.println(ByteOrder.nativeOrder());
        ByteBuffer buffer = ByteBuffer.allocate (8);//ByteBuffer 默认BIG ENDIAN
        System.out.println(buffer.order());
    }
    public void testslice(){
        CharBuffer buffer = CharBuffer.allocate (8);
        buffer.position (3).limit (5);
        CharBuffer sliceBuffer = buffer.slice();//sliceBuffer 是buffer剩余部分的映射，同样共享数据
        System.out.println();
    }
    public void testDuplicate(){
        CharBuffer buffer = CharBuffer.allocate (8);
        buffer.position (3).limit (6).mark( ).position (5);//指定位置，limit，mark
        CharBuffer dupeBuffer = buffer.duplicate( );//共享数据
        buffer.clear();
        System.out.println();
    }

    public void test内存分配() throws UnsupportedEncodingException {

        char [] myArray = new char [100];
        CharBuffer charbuffer = CharBuffer.wrap(myArray);//自己提供的容器做分配
        CharBuffer charbuffer1 = CharBuffer.wrap(myArray,12,42);//position 12，limit 54，这两个参数仅仅设置了初始值
        CharBuffer charbuffer2 = CharBuffer.wrap("sdfsfsdfsdfsd");

    }
    public void testBatchFill(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte) 'h').put((byte) 'e').put((byte)'l').put((byte)'l').put((byte) 'o');
        byteBuffer.flip();
        byte[] t = new byte[100];
//        byteBuffer.get(t);   BufferUnderflowException
        byteBuffer.get(t, 0, byteBuffer.remaining());
        for(int i=0;i<t.length;i++){
            System.out.print((char) t[i]);
        }
    }
    public void testMark(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);//从堆上分配
        byteBuffer.put((byte) 'h').put((byte) 'e').put((byte)'l').put((byte)'l').put((byte) 'o');
        byteBuffer.flip();
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        byteBuffer.mark();//标记
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        byteBuffer.reset();//回到标记
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
    }
    public void testCompact(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte) 'h').put((byte) 'e').put((byte)'l').put((byte)'l').put((byte) 'o');
        byteBuffer.flip();
        byteBuffer.get();
        byteBuffer.compact();//压缩，将未读数据从0开始放置，然后变为可写状态（非可读）
        System.out.println((char)byteBuffer.get());//读到了脏数据
    }
    public void testBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte) 'h').put((byte) 'e').put((byte)'l').put((byte)'l').put((byte)'o');

        byteBuffer.put(0,(byte)'w').put((byte)'t');//可以强行替换某位置元素

        byteBuffer.flip();//buffer 填充完毕，将其变成可读的buffer

        byteBuffer.rewind();//重新将buffer变成写入状态

        byteBuffer.hasRemaining();//告诉你是否还有可读数据

        System.out.println((char)byteBuffer.get());//读取数据

        byteBuffer.clear();//用完后清理掉
    }
    public void test111() {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        String str = "中文大龙燚fdas ";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        System.out.println("共有 " + count + "个 ");
    }
    public void testAdd() throws ClassNotFoundException {



        try {
            ParrotBeanFactory c = new ParrotBeanFactory();
            Properties p = new Properties();
            p.put("client.protocol","binary");
            p.put("client.servers","172.20.16.133:9111");
            p.put("client.heartbeat-timeout","20s");
            PromotionAdminService promotionAdminService = (PromotionAdminService)ParrotBeanFactory.clientInit(false, "test.PromotionAdminService", p);
            String rs = promotionAdminService.addRule("{\"booth\":\"deal,mall\",\"brandIds\":\"123\",\"creator\":\"qiwa\",\"editorUsername\":\"qiwa\",\"endTime\":1479175199,\"exceptHashIds\":\"sdffs\",\"exceptProductIds\":\"\",\"groups\":\"group_Baby\",\"isWholeBooth\":0,\"linkSpecialId\":\"\",\"productIds\":\"123\",\"promotionType\":\"discount_2nd_piece\",\"ruleDescription\":\"\",\"ruleId\":\"1541620740\",\"ruleName\":\"新增接口测试"+System.currentTimeMillis()+"\",\"shopId\":\"\",\"specialIds\":\"\",\"startTime\":1478916000}"
                    ,"[{\"promotionType\":\"gift\",\"conditionAmount\":\"100\",\"displayContent\":\"托尔斯泰dfgfdg\",\"url\":\"http://www.baidu.com\",\"h5Url\":\"http://www.baidu.com\",\"mobileUrl\":\"http://www.baidu.com\",\"content\":\"dsfsdfasdfasdf\",\"itemValue\":[{\"deal_hash_id\":\"ht160728p222550178t9\",\"sku_no\":\"702916098\"}]}]",
                    "0");
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testPromotion() throws ClassNotFoundException {



        try {
            ParrotBeanFactory c = new ParrotBeanFactory();
            Properties p = new Properties();
            p.put("client.protocol","binary");
//            p.put("client.servers","172.20.16.133:9001");
            p.put("client.heartbeat-timeout","20s");
            p.put("client_connect_strategy","weight");
            PromotionService promotionService = (PromotionService)ParrotBeanFactory.clientInit("Promotion.engine.servers",false, "test.PromotionService", p);
            for(int i= 0;i<100;i++){
                String rs = promotionService.pricing("{\"stage\":\"publish\",\"site\":\"cd\",\"platform\":\"www\",\"uid\":\"2000000694\",\"memberLevel\":\"1\",\"isFirstOrder\":\"0\",\"step\":\"1\",\"orders\":[{\"orderKey\":\"retail_global/2310/\",\"itemList\":[{\"itemKey\":\"702915138_ht150306p222400909t1\",\"productIds\":\"745\",\"dealHashId\":\"\",\"itemPrice\":\"150\",\"quantity\":\"1\",\"extends\":\"{\"saleType\":\"0\",\"brandIds\":\"275\",\"categoryIds\":\"0\"}\"}]}]}");
//                String rs = promotionService.historyPricing("{\"stage\":\"publish\",\"site\":\"cd\",\"platform\":\"www\",\"uid\":\"2000000694\",\"memberLevel\":\"1\",\"isFirstOrder\":\"0\",\"step\":\"1\",\"orders\":[{\"orderKey\":\"retail_global/2310/\",\"itemList\":[{\"itemKey\":\"702915138_ht150306p222400909t1\",\"productIds\":\"745\",\"dealHashId\":\"\",\"itemPrice\":\"150\",\"quantity\":\"1\",\"extends\":\"{\"saleType\":\"0\",\"brandIds\":\"275\",\"categoryIds\":\"0\"}\"}]}]}");
                System.out.println(rs);
            }

//            String rs = promotionAdminService.addRule("{\"booth\":\"deal,mall\",\"brandIds\":\"123\",\"creator\":\"qiwa\",\"editorUsername\":\"qiwa\",\"endTime\":1479175199,\"exceptHashIds\":\"sdffs\",\"exceptProductIds\":\"\",\"groups\":\"group_Baby\",\"isWholeBooth\":0,\"linkSpecialId\":\"\",\"productIds\":\"123\",\"promotionType\":\"discount_2nd_piece\",\"ruleDescription\":\"\",\"ruleId\":\"1541620740\",\"ruleName\":\"新增接口测试"+System.currentTimeMillis()+"\",\"shopId\":\"\",\"specialIds\":\"\",\"startTime\":1478916000}"
//                    ,"[{\"promotionType\":\"gift\",\"conditionAmount\":\"100\",\"displayContent\":\"托尔斯泰dfgfdg\",\"url\":\"http://www.baidu.com\",\"h5Url\":\"http://www.baidu.com\",\"mobileUrl\":\"http://www.baidu.com\",\"content\":\"dsfsdfasdfasdf\",\"itemValue\":[{\"deal_hash_id\":\"ht160728p222550178t9\",\"sku_no\":\"702916098\"}]}]",
//                    "0");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
