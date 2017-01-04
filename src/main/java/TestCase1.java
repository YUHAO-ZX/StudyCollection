import com.google.common.base.Stopwatch;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.net.*;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * Created by xinz on 2016/9/26.
 */
public class TestCase1 extends TestCase {

    static BigDecimal ratio = new BigDecimal(100);

    public void test9() throws InterruptedException {
        System.out.println("115149864#1610619546#i#3.980".length());
        System.out.println("10.123.0.12"+"#"+"service".hashCode()+"#service#1.0".length());
    }

    public void test8() throws InterruptedException {
        Stopwatch sw = Stopwatch.createStarted();
        Thread.sleep(1000);
        sw.stop();
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
    public void test7(){

        BigDecimal b =  new BigDecimal("0.01");
        System.out.println(b);
        BigDecimal totalReturn = new BigDecimal(0);
        totalReturn = totalReturn.add(b);
        System.out.println(totalReturn);
        System.out.println(totalReturn.compareTo(new BigDecimal("0.01")));
    }
    public void test6(){
        BigDecimal b = new BigDecimal(0.01);
        BigDecimal anchorMoney = BigDecimal.valueOf(100).multiply(b).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(anchorMoney);
//        BigDecimal b = new BigDecimal(0.5);
//        Integer anchorVirtualCoin = BigDecimal.valueOf(200).
//                multiply(b).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
//        System.out.println(anchorVirtualCoin);
    }

    public void test5(){
        BigDecimal money = new BigDecimal("-0.349");
        if(null == money || money.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("error");
        }
        System.out.println(money.multiply(ratio).setScale(0,BigDecimal.ROUND_DOWN).intValue());
    }
    public void test4() throws InterruptedException {
        BigDecimal b = new BigDecimal(1.73654);
        b.setScale(0,BigDecimal.ROUND_UP);
        System.out.println(b.intValue());
    }
    public void test3() throws UnknownHostException {
        String s = InetAddress.getLocalHost().getHostAddress();
        System.out.println(s);
    }
    public void test2() throws SocketException {
        NetworkInterface networkInterface = NetworkInterface.getByName("eth2");// .getNetworkInterfaces();

        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
            InetAddress inetAddress = inetAddresses.nextElement();
            if (inetAddress instanceof Inet4Address) {
                String target = inetAddress.getHostAddress();//.getHostName();
                System.out.println(target);
            }
        }
    }
    public void test1(){
        String t = "周秦瑶 92909501\n" +
                "吴桑 107256011\n" +
                "王琦 100238004\n" +
                "陈洪 107266652\n" +
                "孔晓江 99344762\n" +
                "莫小波 109246790\n" +
                "李昌强 110596399\n" +
                "罗玄 103032722\n" +
                "韩炼  98441505\n" +
                "游飞 110350649\n" +
                "65189254\n" +
                "2525575\n" +
                "90780136\n" +
                "83284044\n" +
                "106146165\n" +
                "106714102\n" +
                "7510343\n" +
                "熊洋 48571274\n" +
                "刘志豪 109455206\n" +
                "吴洪 104388214\n" +
                "牟杨敏 40798270\n" +
                "唐珂 109455206\n" +
                "雷云红 41002149\n" +
                "严从现 107313067\n" +
                "孙晓 97976043\n" +
                "陈响 105718453\n" +
                "622616694\n" +
                "50293830\n" +
                "张洪 89846195\n" +
                "何飞 105446621\n" +
                "冯超然 83440572\n" +
                "郭建栋 75316719\n" +
                "蔡勇 98120528\n" +
                "罗文龙 103417361";
        String[] tt = t.split("\\n");
        for(int i=0;i<  tt.length;i++){
            if(tt[i].split(" ").length == 1){
                System.out.print(tt[i].split(" ")[0]+",");
            }else{
                System.out.print(tt[i].split(" ")[1]+",");
            }
        }
    }
}
