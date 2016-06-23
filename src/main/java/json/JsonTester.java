package json;
/**
 * Created by xinz on 2016/6/2.
 */
public class JsonTester {

    static String json = "{\"code\":100,\"message\":\"\",\"channelInfo\":[{\"channel_id\":\"16093104850683349639\",\"channel_name\":\"2015-02-03 01:22:22\",\"channel_describe\":\"create channel\",\"channel_status\":\"1\",\"upstream_list\":[{\"sourceName\":\"hls and rtmp\",\"sourceID\":\"2119_016b571928a111e6b91fa4dcbef5e35a\",\"sourceType\":\"1\",\"sourceAddress\":\"rtmp://2119.livepush.myqcloud.com/live/2119_016b571928a111e6b91fa4dcbef5e35a?bizid=2119\"}],\"player_id\":\"345\",\"resolution\":null,\"password\":null,\"rtmp_downstream_address\":\"rtmp://2119.liveplay.myqcloud.com/live/2119_016b571928a111e6b91fa4dcbef5e35a\",\"flv_downstream_address\":\"http://2119.liveplay.myqcloud.com/live/2119_016b571928a111e6b91fa4dcbef5e35a.flv\",\"hls_downstream_address\":\"http://2119.liveplay.myqcloud.com/2119_016b571928a111e6b91fa4dcbef5e35a.m3u8\"}]}";
    static{
        JsonUtil.getString(json,"channelInfo[0].channel_id");
    }
    static String json1 = "{\"a\":[[1,2,3],[4,5,{\"mdf\":\"jsonjjj\"}]]}";
    static String json2 = "{\"a\":{\"b\":[\"t1\",\"t2\"]}}";
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println(JsonUtil.getString(json, "channelInfo[0].upstream_list[0].sourceAddress"));
        System.out.println(JsonUtil.getString(json, "channelInfo[0].rtmp_downstream_address"));
        System.out.println(System.currentTimeMillis() - start);

    }
}
