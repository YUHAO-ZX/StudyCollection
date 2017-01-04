package charset;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

/**
 * Created by xinz on 16/7/8.
 */
public class BinaryChange {
    public static void main(String[] args) throws ParseException, InterruptedException, ExecutionException, UnsupportedEncodingException {
        System.out.println(toBinaryString("严","UTF-8"));
        System.out.println(toBinaryString("中", "GBK"));
        System.out.println(toBinaryString("中d","UTF-8"));
        System.out.println(toBinaryString("中d","GBK"));
        //测试表明:不同编码方式对ASC2范围内的字符的编码都是一样的
        System.out.println(toBinaryString("d","UTF-8"));
        System.out.println(toBinaryString("d","GBK"));
        System.out.println(toBinaryString("d","GB2312"));

        Integer a = 0x6C49;
        System.out.println(Integer.toBinaryString(a));

        System.out.println('\u0033');



    }

    //字符串转二进制
    public static String toBinaryString(String str,String charset) throws UnsupportedEncodingException {
        String temp = new String(str.getBytes(),charset);
        char[] t = temp.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<t.length;i++){
            sb.append(Integer.toBinaryString(t[i]));
        }
        return sb.toString();
    }

    public static String binary2String(String binary,String charset){
        return null;
    }
}
