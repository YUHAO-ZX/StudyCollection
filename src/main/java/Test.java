import org.apache.commons.lang.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by xinz on 2016/6/12.
 */
public class Test {
    static BlockingQueue<String> queue = new ArrayBlockingQueue(10);
    private static  Comparator comparator;


    public static void main(String[] args) {
        System.out.println("asdf".equals(null));
//        String s;
//        String m = "";
//    String[] t = s.split("\\n");
//        int k=0;
//        for(int i=0;i<t.length;i++){
//            if(StringUtils.isBlank(t[i])){
//                continue;
//            }
//            k++;
//            m+="\""+t[i]+"\",";
//        }
//        System.out.println(m);
//        System.out.println("k"+k);
//        System.out.println("len"+t.length);





//        Integer i =-100;
//        System.out.println(i);
//        System.out.println(i & 0xffffffffL);
//        System.out.println(comparator.compare("1","2"));
//
//        comparator.compare("","");
//        System.out.println(ByteOrder.nativeOrder());
//        Map<T1,Boolean> map = new HashMap<T1, Boolean>();
//        T1 t1 = new T1();
//        T1 t2 = new T1();
//        t1.setRoomId(1);
//        t2.setRoomId(1);
//        map.put(t1,false);
//        map.put(t2,true);
//        System.out.println();

//        int ASYN_LIMIT = 5;
//        int target = 11;
//        int size = target%ASYN_LIMIT==0?target/ASYN_LIMIT:target/ASYN_LIMIT+1;
//        System.out.println(size);
//        for(int i=0;i<size;i++){
//            int fromIndex = i*ASYN_LIMIT;
//            int endIndex  = (fromIndex + ASYN_LIMIT) > target ? target  : (fromIndex + ASYN_LIMIT );
//            System.out.println(fromIndex+"-"+endIndex);
//        }


//
//        List<String> s = new ArrayList<String>();
//        s.add("tt");
//        List<String> s2 = s.subList(0,1);
//        System.out.println(s2.get(0));



//        String topic        = "MQTT Examples";
//        String content      = "Message from MqttPublishSample";
//        int qos             = 2;
//        String broker       = "tcp://iot.eclipse.org:1883";
//        String clientId     = "JavaSample";
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        try {
//            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(true);
//            System.out.println("Connecting to broker: "+broker);
//            sampleClient.connect(connOpts);
//            System.out.println("Connected");
//            System.out.println("Publishing message: "+content);
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            sampleClient.publish(topic, message);
//            System.out.println("Message published");
//            sampleClient.disconnect();
//            System.out.println("Disconnected");
//            System.exit(0);
//        } catch(MqttException me) {
//            System.out.println("reason "+me.getReasonCode());
//            System.out.println("msg "+me.getMessage());
//            System.out.println("loc "+me.getLocalizedMessage());
//            System.out.println("cause "+me.getCause());
//            System.out.println("excep "+me);
//            me.printStackTrace();
//        }
    }

}


    class T1{
    int roomId;
    public boolean equals(Object t){
        return t == null ? false : ((T1)t).getRoomId() == this.roomId;
    }

    public int hashCode() {
        return this.getRoomId();
    }
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}

class BigCompa implements Comparator<String>{

    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

class LittleCompa implements Comparator<String>{

    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}

class Setter {

}
