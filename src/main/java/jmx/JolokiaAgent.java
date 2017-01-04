package jmx;

import org.jolokia.client.J4pClient;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;

import java.util.Map;

/**
 * Created by xinz on 2016/8/17.
 */
public class JolokiaAgent {
    public static void main(String[] args) {
        try {
            J4pClient j4pClient = new J4pClient("http://localhost:8082/ViewObjectRes");
            J4pReadRequest req = new J4pReadRequest("zhangxin:name=HelloWorld",
                    "Name");
            J4pReadResponse resp = j4pClient.execute(req);
            Map<String,Long> vals = resp.getValue();
            long used = vals.get("used");
            long max = vals.get("max");
            long usage = (used * 100 / max);
            System.out.println("Memory usage: used: " + used +
                    " / max: " + max + " = " + usage + "%");
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

    }
}
