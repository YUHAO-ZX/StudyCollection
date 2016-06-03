package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class ComplicJson {

    static String json = "{\"code\":0,\"message\":\"\",\"channelInfo\":[{\"channel_id\":\"16093104850683349639\",\"channel_name\":\"哈哈哈哈哈哈\",\"channel_describe\":\"create channel\",\"channel_status\":\"1\",\"upstream_list\":[{\"sourceName\":\"hls and rtmp\",\"sourceID\":\"2119_016b571928a111e6b91fa4dcbef5e35a\",\"sourceType\":\"1\",\"sourceAddress\":\"rtmp://2119.livepush.myqcloud.com/live/2119_016b571928a111e6b91fa4dcbef5e35a?bizid=2119\"}],\"player_id\":\"345\",\"resolution\":null,\"password\":null,\"rtmp_downstream_address\":\"rtmp://2119.liveplay.myqcloud.com/live/2119_016b571928a111e6b91fa4dcbef5e35a\",\"flv_downstream_address\":\"http://2119.liveplay.myqcloud.com/live/2119_016b571928a111e6b91fa4dcbef5e35a.flv\",\"hls_downstream_address\":\"http://2119.liveplay.myqcloud.com/2119_016b571928a111e6b91fa4dcbef5e35a.m3u8\"}]}";
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        JSONObject object = JSONObject.parseObject(json);
        int code = object.getInteger("code");
        String channel = ((JSONObject)object.getJSONArray("channelInfo").get(0)).getString("resolution");
        System.out.println(code+"--"+channel);
        System.out.println(getIndex("[12]"));

    }
    public static int getIndex(String element){
        int l = element.indexOf("[") + 1;
        int r = element.indexOf("]");
        return Integer.valueOf(element.substring(l,r));
    }
}
