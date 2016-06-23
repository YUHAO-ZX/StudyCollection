package math;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 将无符号类型转换为有符号类型
 * Created by xinz on 2016/6/6.
 */
public class UnsignLong {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sd.parse("2016-05-31 19:03:55").getTime());
//        System.out.println(checkChannelId("-2353318346054690492"));
    }

    public static String checkChannelId(String channelId){
        if(StringUtils.isBlank(channelId)){
            return null;
        }

        if(!channelId.startsWith("-")){
            return channelId;
        }

        BigDecimal l = new BigDecimal(channelId);
        Long channelLong = l.longValue();
        if(channelLong >= 0){
            return channelId;
        }
        channelLong = channelLong & 0x7fffffffffffffffL;
        BigDecimal rs = new BigDecimal(channelLong).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));
        return rs.toString();
    }

}
