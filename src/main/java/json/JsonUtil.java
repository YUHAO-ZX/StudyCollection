package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 快捷获取json字段信息工具
 例子：
 {
 "code":0,
 "message":"",
 "channelInfo":[
 {
 "channel_id":"16093104850683364807",
 "channel_name":"哦哦哦哦哦哦哦",
 "channel_describe":"create channel",
 "channel_status":"1",
 "upstream_list":[
 {
 "sourceName":"hls and rtmp",
 "sourceID":"2119_7864df5a292f11e6b91fa4dcbef5e35a",
 "sourceType":"1",
 "sourceAddress":"rtmp://2119.livepush.myqcloud.com/live/2119_7864df5a292f11e6b91fa4dcbef5e35a?bizid=2119"
 }
 ],
 "player_id":"345",
 "rtmp_downstream_address":"rtmp://2119.liveplay.myqcloud.com/live/2119_7864df5a292f11e6b91fa4dcbef5e35a",
 "flv_downstream_address":"http://2119.liveplay.myqcloud.com/live/2119_7864df5a292f11e6b91fa4dcbef5e35a.flv",
 "hls_downstream_address":"http://2119.liveplay.myqcloud.com/2119_7864df5a292f11e6b91fa4dcbef5e35a.m3u8"
 }
 ]
 }
 ************************例如需要获取：sourceAddress     调用：JsonUtil.getString(json, "channelInfo[0].upstream_list[0].sourceAddress")

 * Created by xinz on 2016/6/2.
 */
public final class JsonUtil {
    //自定义字符集
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String   DOT = ".";
    //操作类型
    private static final String OP_GET = "G";
    private static final String OP_CHANGE = "C";
    //对象类型
    private static final String TYPE_OBJ = "O";
    private static final String TYPE_ARRAY = "A";
    //结束符
    private static final String END = "$";


    public static Integer getInt(String json,String path){
        return TypeUtils.castToInt(getData(json,path));
    }
    public static String getString(String json,String path){
        return TypeUtils.castToString(getData(json,path));
    }
    public static BigDecimal  getBigDecimal(String json,String path){
        return TypeUtils.castToBigDecimal(getData(json,path));
    }
    public static BigInteger  getBigInteger(String json,String path){
        return TypeUtils.castToBigInteger(getData(json,path));
    }
    public static Boolean getBoolean(String json,String path){
        return TypeUtils.castToBoolean(getData(json,path));
    }
    public static Date getDate(String json,String path){
        return TypeUtils.castToDate(getData(json,path));
    }


    private static Object getData(String json,String path){
        if(StringUtils.isBlank(path)){
            return null;
        }
        Object jsonResult ;
        if(json.charAt(0) == LEFT_BRACKET.charAt(0)){
            jsonResult = JSONObject.parseArray(json);
        }else{
            jsonResult = JSONObject.parse(json);
        }

        Queue<String> op = new LinkedList<String>();
        Queue<String> data = new LinkedList<String>();
        op.add((jsonResult instanceof JSONArray) ? TYPE_ARRAY : TYPE_OBJ);
        compile(path, op, data);

        while(op.peek() != null){
            String obType = op.poll();
            String nextOb = op.peek();
            if(obType.equals(TYPE_ARRAY)){
                if(nextOb.equals(OP_GET)){
                    op.poll();
                    String index = op.poll();
                    return ((JSONArray)jsonResult).get(Integer.valueOf(index));
                }else if(nextOb.equals(OP_CHANGE)){
                    op.poll();
                    String index = op.poll();
                    jsonResult = ((JSONArray)jsonResult).get(Integer.valueOf(index));
                }
            }else if(obType.equals(TYPE_OBJ)){
                if(nextOb.equals(TYPE_OBJ)){
                    String key = data.poll();
                    jsonResult = ((JSONObject)jsonResult).getJSONObject(key);
                }else if(nextOb.equals(OP_CHANGE)){
                    op.poll();
                    String index = op.poll();
                    String key = data.poll();
                    jsonResult = ((JSONObject)jsonResult).getJSONArray(key).get(Integer.valueOf(index));
                }else if(nextOb.equals(OP_GET)){
                    String key = data.poll();
                    return ((JSONObject)jsonResult).get(key);
                }else if(nextOb.equals(TYPE_ARRAY)){
                    String key = data.poll();
                    jsonResult = ((JSONObject)jsonResult).getJSONArray(key);
                }
            }
        }
        return null;
    }

    private static void compile(String path,Queue<String> op,Queue<String> data){
        path += END;
        int length = path.length();
        StringBuilder nameRecorder  = new StringBuilder();
        StringBuilder indexRecorder = new StringBuilder();
        boolean isIndex = false;
        for(int i = 0;i < length ;i++){
            char c = path.charAt(i);
            char nextc = path.charAt(i+1);
            boolean end = nextc == END.charAt(0) ? true: false;
            boolean ignore = false;
            //下一个字符左括号：表示当前类型为数组对象
            if(nextc == LEFT_BRACKET.charAt(0)){
                op.add(TYPE_ARRAY);
            }

            //当前为左括号，记录角标
            if(c == LEFT_BRACKET.charAt(0)){
                isIndex = true;
                ignore = true;
            }

            //当前为右括号，记录角标
            if(c == RIGHT_BRACKET.charAt(0)){
                ignore = true;
            }

            //当前字符句号，队列写入名称
            if(c == DOT.charAt(0)){
                String name = nameRecorder.toString();
                data.add(name.toString());
                nameRecorder = new StringBuilder();
                ignore = true;
            }

            if(!ignore){
                if(isIndex){
                    indexRecorder.append(c);
                }else{
                    nameRecorder.append(c);
                }
            }

            //下一个字符为右括号：写入操作符，写入角标
            if(nextc == RIGHT_BRACKET.charAt(0)){
                String index = indexRecorder.toString();
                if(path.charAt(i+2) == END.charAt(0)){
                    op.add(OP_GET);
                    op.add(index);
                    data.add(nameRecorder.toString());
                    break;
                }else{
                    op.add(OP_CHANGE);
                }
                op.add(index);
                indexRecorder = new StringBuilder();
                isIndex = false;
            }

            //下一个字符句号：表示当前类型为普通对象
            if(nextc == DOT.charAt(0)){
                op.add(TYPE_OBJ);
            }

            //以字符结束，写入操作类型和key
            if(end){
                op.add(OP_GET);
                data.add(nameRecorder.toString());
                break;
            }
        }
    }

}
