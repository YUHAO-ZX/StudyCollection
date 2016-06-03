package test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


import com.alibaba.fastjson.JSONObject;

/**
 * @department 成都-产品运营-商务智能-java
 * @description
 * @author lianghongl
 * @date 2016年6月2日 上午11:30:30
 */

public class TestTencent {
    static String SecretId = "AKID1K7LGcT51fg99gTK00aY99BSEo6ptnvk";
    static String SecretKey = "Ta3rB0KCZSE7srTT00qpUXlqw5dvxbOn";
    public static void main(String[] args) throws Exception {
        String url = "https://live.api.qcloud.com/v2/index.php?Action=DescribeLVBChannel&channelId=96171715553394807";
//		JSONObject res = new SendSDKUtil().sendPost(url, null);
//		System.out.println(res.toJSONString());
        long time = System.currentTimeMillis();
        String srcStr = assembleSrcStr(time);
        System.out.println(srcStr);
//        String signature = generateSignature(srcStr, SecretKey);
//        String reqUrl = assembleCommonParam(url,signature,time);
//        System.out.println(reqUrl);

    }
    public static String assembleSrcStr(long time){
        return "GETlive.api.qcloud.com/v2/index.php?Action=DescribeLVBChannel&Nonce=1&Region=bj&SecretId=AKID1K7LGcT51fg99gTK00aY99BSEo6ptnvk&Timestamp="+time;
    }
    public static String assembleCommonParam(String url,String signature,long time){
        url = url+"&Region=bj&Timestamp="+time+"&Nonce=1&SecretId="+SecretId+"&Signature="+signature;
        return url;
    }

//    public static String generateSignature (String value,String key) throws Exception {
//        byte[] Sequence = value.getBytes("UTF-8");
//        byte[] keyBytes = key.getBytes();
//        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
//        Mac mac = Mac.getInstance("HmacSHA1");
//        mac.init(signingKey);
//        byte[] Hash = mac.doFinal(Sequence);
//        String Signature = new String(Base64.encodeBase64(Hash));
//        return Signature;
//    }
}

