package test;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by xinz on 2016/7/26.
 */
public class Test2 {
    static final String unSupportedChar = "^.*[”|“|\"|\\\\].*$";
    static Pattern uscPattern = Pattern.compile(unSupportedChar);
    public static void main(String[] args) throws InterruptedException {
            System.out.println("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop".length());
    }
        public static String getBoothInOrder(String booth){
                if(StringUtils.isNotBlank(booth)){
                        String[] boothes = booth.split(",");
                        return getBoothInOrder(boothes);
                }
                return "";
        }

        public static String getBoothInOrder(String[] boothes){
                if(null == boothes || boothes.length <= 0){
                        return "";
                }
                Arrays.sort(boothes);
                StringBuilder sb = new StringBuilder();
                for(String ele : boothes){
                        sb.append(ele).append(",");
                }
                String rs = sb.toString();
                if(rs.endsWith(",")){
                        return rs.substring(0,rs.length()-1);
                }else{
                        return rs;
                }
        }
}
