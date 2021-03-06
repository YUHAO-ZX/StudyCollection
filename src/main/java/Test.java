import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.RateLimiter;
import limiter.MyRateLimiter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
/**
 * Created by xinz on 2016/6/12.
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);
    static String roomId = "145157";
    static String t = "{\"1089533\":\"145157_1469452671_108652974,\",\"8411621\":\"145157_1469447411_108652974,\",\"10059271\":\"145157_1469440893_108652974,145157_1469444508_108652974,\",\"12762717\":\"145157_1469447575_108652974,\",\"23268029\":\"145157_1469445424_108652974,\",\"24010325\":\"143752_1469416504_76917967,143752_1469416771_76917967,\",\"24165307\":\"145097_1469440585_76917967,145097_1469440653_76917967,145157_1469449597_108652974,\",\"24398087\":\"145157_1469447696_108652974,\",\"25827327\":\"145157_1469445842_108652974,\",\"42306539\":\"143672_1469426603_105496912,143672_1469428037_105496912,144657_1469433349_76917967,144657_1469433588_76917967,144657_1469433608_76917967,\",\"43804954\":\"143672_1469413638_105496912,143672_1469413658_105496912,143672_1469413748_105496912,143672_1469413754_105496912,143672_1469413778_105496912,143672_1469413894_105496912,143672_1469414006_105496912,143672_1469414111_105496912,143672_1469414171_105496912,143672_1469414174_105496912,143672_1469414475_105496912,143672_1469414576_105496912,143672_1469414589_105496912,143672_1469414619_105496912,143672_1469414684_105496912,143672_1469414814_105496912,143672_1469414856_105496912,143672_1469414887_105496912,143672_1469414889_105496912,143672_1469414915_105496912,143672_1469415077_105496912,143672_1469416139_105496912,143672_1469416531_105496912,\",\"44895211\":\"143672_1469419293_105496912,143672_1469419376_105496912,143672_1469419548_105496912,143672_1469422614_105496912,143672_1469423239_105496912,143672_1469424525_105496912,\",\"46697851\":\"145157_1469444878_108652974,\",\"47688246\":\"145157_1469453081_108652974,\",\"50732879\":\"145157_1469452677_108652974,\",\"65189254\":\"143672_1469426511_105496912,143672_1469426869_105496912,143672_1469428497_105496912,143672_1469428609_105496912,143672_1469428685_105496912,143672_1469428707_105496912,\",\"73814963\":\"145157_1469441816_108652974,\",\"75409496\":\"145157_1469451426_108652974,\",\"76686384\":\"145157_1469442523_108652974,145157_1469443975_108652974,\",\"80086158\":\"145157_1469443929_108652974,\",\"81457106\":\"145157_1469442084_108652974,\",\"82938071\":\"143888_1469418458_108652974,\",\"83304102\":\"143888_1469418504_108652974,\",\"83442433\":\"145157_1469450019_108652974,\",\"86202789\":\"145157_1469446818_108652974,\",\"87266176\":\"143672_1469415982_105496912,143672_1469416017_105496912,143672_1469416068_105496912,143672_1469416233_105496912,143672_1469416368_105496912,143672_1469416526_105496912,143672_1469417741_105496912,143672_1469418743_105496912,143672_1469418781_105496912,143672_1469418867_105496912,143672_1469419056_105496912,143672_1469419073_105496912,143672_1469419164_105496912,143672_1469419215_105496912,143672_1469419728_105496912,143672_1469422731_105496912,143672_1469422806_105496912,143672_1469423567_105496912,143672_1469424218_105496912,143672_1469424383_105496912,143672_1469424519_105496912,\",\"87685689\":\"145157_1469446698_108652974,\",\"89564771\":\"145157_1469449900_108652974,145157_1469450332_108652974,\",\"89849874\":\"145157_1469450752_108652974,\",\"92972178\":\"143888_1469418452_108652974,\",\"93028172\":\"145157_1469445661_108652974,\",\"94610217\":\"145157_1469452511_108652974,145157_1469452559_108652974,145157_1469452897_108652974,\",\"97216655\":\"145157_1469443679_108652974,\",\"97248333\":\"143752_1469416325_76917967,143752_1469416335_76917967,\",\"97389084\":\"145157_1469449818_108652974,\",\"97997801\":\"145157_1469442394_108652974,\",\"100393002\":\"143888_1469418509_108652974,143888_1469418599_108652974,\",\"100563286\":\"145157_1469454182_108652974,\",\"101378252\":\"145157_1469444687_108652974,\",\"102896902\":\"145157_1469447843_108652974,\",\"103214521\":\"145157_1469452854_108652974,145157_1469453526_108652974,\",\"104312726\":\"143672_1469415321_105496912,143672_1469415489_105496912,\",\"104501435\":\"143672_1469413440_105496912,143672_1469413561_105496912,143672_1469413574_105496912,143672_1469413756_105496912,143672_1469413785_105496912,143672_1469415497_105496912,143672_1469415563_105496912,143672_1469418418_105496912,\",\"104501452\":\"143672_1469426194_105496912,143672_1469426236_105496912,143672_1469426239_105496912,143672_1469426336_105496912,143672_1469427796_105496912,143672_1469427883_105496912,143672_1469427886_105496912,143672_1469428253_105496912,143672_1469428259_105496912,143672_1469428415_105496912,143672_1469429404_105496912,143672_1469429523_105496912,143672_1469432033_105496912,143672_1469432112_105496912,143672_1469432171_105496912,143672_1469432248_105496912,143672_1469432273_105496912,143672_1469432685_105496912,143672_1469432950_105496912,143672_1469433135_105496912,143672_1469433452_105496912,143672_1469433469_105496912,143672_1469433509_105496912,143672_1469433658_105496912,\",\"104570443\":\"143888_1469418846_108652974,\",\"105188237\":\"144445_1469431138_76917967,145157_1469445441_108652974,\",\"105244032\":\"145157_1469454405_108652974,\",\"105397639\":\"145157_1469446180_108652974,\",\"105682953\":\"145157_1469448441_108652974,\",\"105808793\":\"145157_1469452104_108652974,145157_1469452480_108652974,145157_1469452492_108652974,145157_1469452592_108652974,145157_1469453394_108652974,\",\"105834769\":\"145157_1469453669_108652974,\",\"105856552\":\"145157_1469448129_108652974,\",\"105939182\":\"143672_1469423370_105496912,143672_1469423525_105496912,143672_1469423624_105496912,143672_1469423739_105496912,143672_1469423771_105496912,143672_1469423851_105496912,143672_1469424096_105496912,\",\"106002685\":\"143672_1469413272_105496912,143672_1469413278_105496912,143672_1469413548_105496912,143672_1469413597_105496912,143672_1469413794_105496912,143672_1469414080_105496912,143672_1469414394_105496912,143672_1469416895_105496912,143672_1469416908_105496912,143672_1469416949_105496912,143672_1469416993_105496912,143672_1469417114_105496912,143672_1469417209_105496912,143672_1469417280_105496912,143672_1469417292_105496912,143672_1469417506_105496912,143672_1469417645_105496912,143672_1469418620_105496912,143672_1469423298_105496912,143672_1469423483_105496912,143672_1469423692_105496912,143672_1469432380_105496912,\",\"106024929\":\"143672_1469415239_105496912,143672_1469416804_105496912,143672_1469425497_105496912,\",\"106062168\":\"145157_1469451701_108652974,\",\"106124732\":\"145157_1469448932_108652974,\",\"106386471\":\"145157_1469448662_108652974,\",\"106424161\":\"145157_1469440918_108652974,\",\"106822380\":\"145157_1469446089_108652974,\",\"108923858\":\"145157_1469446271_108652974,\",\"109206214\":\"145157_1469446997_108652974,145157_1469446999_108652974,145157_1469447215_108652974,145157_1469448357_108652974,\",\"109221154\":\"143888_1469418442_108652974,\",\"109223469\":\"145157_1469445349_108652974,\",\"109225824\":\"145157_1469442592_108652974,\",\"109229889\":\"145157_1469440901_108652974,145157_1469441291_108652974,\",\"109237296\":\"143888_1469418435_108652974,\",\"109239537\":\"145157_1469445121_108652974,\",\"109242458\":\"145157_1469444334_108652974,\",\"109244116\":\"145157_1469444287_108652974,\",\"109245655\":\"145157_1469442534_108652974,\",\"109246428\":\"145157_1469446743_108652974,\",\"109248113\":\"145157_1469442592_108652974,145157_1469442915_108652974,145157_1469444731_108652974,\",\"109249990\":\"143888_1469418683_108652974,\",\"109251730\":\"145157_1469442901_108652974,\",\"109252902\":\"145157_1469448603_108652974,\",\"109259274\":\"143888_1469418450_108652974,\",\"109260525\":\"145157_1469441588_108652974,\",\"109271053\":\"143672_1469421187_105496912,\",\"109273633\":\"145157_1469445401_108652974,145157_1469445876_108652974,\",\"109275212\":\"145157_1469445706_108652974,\",\"109276489\":\"145157_1469442916_108652974,\",\"109278312\":\"145157_1469447217_108652974,145157_1469447219_108652974,\",\"109278587\":\"145157_1469442483_108652974,145157_1469442574_108652974,\",\"109285385\":\"145157_1469445155_108652974,\",\"109290061\":\"143888_1469418594_108652974,\",\"109293947\":\"145157_1469442960_108652974,145157_1469442961_108652974,\",\"109297954\":\"145157_1469453632_108652974,\",\"109303508\":\"145157_1469443137_108652974,\",\"109312810\":\"145157_1469449646_108652974,\",\"109316127\":\"145157_1469445720_108652974,\",\"109318258\":\"145157_1469454202_108652974,\",\"109320000\":\"145157_1469454369_108652974,\",\"109323771\":\"145157_1469450185_108652974,\",\"109324223\":\"145157_1469447003_108652974,\",\"109324304\":\"145157_1469454365_108652974,145157_1469454671_108652974,\",\"109324950\":\"143888_1469418928_108652974,\",\"109326271\":\"145157_1469451892_108652974,\",\"109326586\":\"145157_1469450864_108652974,145157_1469451911_108652974,\",\"109332356\":\"145157_1469441374_108652974,\",\"109339006\":\"145157_1469442484_108652974,\",\"109348299\":\"145157_1469444225_108652974,\",\"109349095\":\"145157_1469445930_108652974,\",\"109352996\":\"145157_1469451632_108652974,\",\"109353378\":\"145157_1469443362_108652974,\",\"109353410\":\"145157_1469442628_108652974,\",\"109356206\":\"143888_1469418571_108652974,\",\"109364952\":\"145157_1469447330_108652974,\",\"109371937\":\"145157_1469449116_108652974,\",\"109384286\":\"145157_1469450964_108652974,\"}";
    public static void main(String[] args) throws ParseException, InterruptedException, ExecutionException, IOException {
        File f = new File("d:\\1.txt");

        InputStream inputStream = new FileInputStream(f);
        byte[] temp = new byte[100];
        StringBuilder sb = new StringBuilder();
        while(true){
            if(inputStream.read(temp)==-1){
                break;
            }
            sb.append(new String(temp));
        }
        sb.append(new String(temp));
        inputStream.close();

        Map<String,Integer> range = Maps.newHashMap();
        String[] items = sb.toString().split("\\r|\\n");
        for(int i=0;i<items.length;i++){
            if(StringUtils.isBlank(items[i])){
                continue;
            }
            String[] info = items[i].split("\\t| ");
            if(info.length < 4){
                continue;
            }
            if(range.get(info[0]) == null){
//                System.out.println("======="+items[i]);
                range.put(info[0],Integer.valueOf(info[3]));
            }else{
                range.put(info[0],range.get(info[0]) > Integer.valueOf(info[3]) ? range.get(info[0]) : Integer.valueOf(info[3]));
            }
        }

        Set<String> result = Sets.newHashSet();
        Map<String,List<String>> rs = Maps.newHashMap();
        for(int i=0;i<items.length;i++){

            String[] info = items[i].split("\\t| ");
            if(info.length < 4){
                continue;
            }
            if(range.get(info[0]) >= 10000 && Integer.valueOf(info[3]) >= 10000 ||
                    range.get(info[0]) <= 5100 && range.get(info[0]) >= 5000 && Integer.valueOf(info[3]) <= 5100 && Integer.valueOf(info[3]) >= 5000||
                    range.get(info[0]) <= 1040 && range.get(info[0]) >= 1000 && Integer.valueOf(info[3]) <= 1040 && Integer.valueOf(info[3]) >= 1000||
                    range.get(info[0]) <= 520 && range.get(info[0]) >= 500 && Integer.valueOf(info[3]) <= 520 && Integer.valueOf(info[3]) >= 500||
                    range.get(info[0]) <= 110 && range.get(info[0]) >= 100 && Integer.valueOf(info[3]) <= 110 && Integer.valueOf(info[3]) >= 100||
                    range.get(info[0]) <= 15 && range.get(info[0]) >= 10 && Integer.valueOf(info[3]) <= 15 && Integer.valueOf(info[3]) >= 10){
                result.add(info[1]);
                rs.put(info[0],add(info[1],rs.get(info[0])));
            }
        }

        int i =0;
        for(String ele:result){
            if(i ++  % 500 == 0){
                System.out.println();
            }
            System.out.print(ele + ",");
        }
//        for(String key:rs.keySet()){
//            System.out.println(key+" "+rs.get(key).size());
//        }
        System.out.println();
        System.out.println(result.size());


    }
    private static List<String> add(String i,List<String> list){
        if(null == list){
            list = new ArrayList<String>();
        }
        list.add(i);
        return list;
    }
    final static int SUCCESS = 0;
    final static int ERROR = -1;
    static class Result<T>{
        int code;
        String msg;
        T data;
        Result(int code,String msg,T data){
            this.code = code;
            this.msg = msg;
            this.data = data;
        }
        public static <T> Result success(T data){
            return new Result(SUCCESS,"",data);
        }

        public static Result error(String errorMsg){
            return new Result(ERROR,errorMsg,null);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static String errorInfo(Throwable throwable){
        Map<String,String> errorInfoMap = Maps.newHashMap();
        SimpleDateFormat sd = new SimpleDateFormat("MM-dd HH:mm:ss");
        errorInfoMap.put("time",sd.format(new Date()));
        StringBuilder sb = new StringBuilder();
        int loop = 4;
        while(throwable != null && loop > 0){
            loop -- ;
            sb.append(throwable.getClass().getSimpleName()).append(" method:").append(throwable.getStackTrace()[0].getClassName()).append(".")
                    .append(throwable.getStackTrace()[0].getMethodName()).append(" line:").append(throwable.getStackTrace()[0].getLineNumber()).append("&");
            throwable = throwable.getCause();
        }
        errorInfoMap.put("errorInfo",sb.toString());
        return JSON.toJSONString(errorInfoMap);
    }
    static class LiveResult{
        public static int CODE_OK = 0;
        public static int CODE_ERROR = -1;


        public static int FN_NONSUPPORT = -2;
        public static String ACTION_TOAST = "toast";
        private int code;//响应结果，0为正常，其它值为异常
        private String action = "";//终端收到的响应类型
        private String message = "";//响应消息
        private Object data;
        public LiveResult() {
        }
        /**
         * Created with IntelliJ IDEA.
         * <p>功能:传正常数据,code默认为正常code</p>
         * Author:<a href="mailto:longl@jumei.com">lilong</a> <br/>
         * Date:16/3/18 下午7:40 <br/>
         */
        public LiveResult(Object data) {
            this.code = CODE_OK;
            this.data = data;
        }

        public LiveResult(int code) {
            this.code = code;
        }
        /**
         * Created with IntelliJ IDEA.
         * <p>功能:传错误消息,code默认为错误code</p>
         * Author:<a href="mailto:longl@jumei.com">lilong</a> <br/>
         * Date:16/3/18 下午7:40 <br/>
         */
        public LiveResult(String errorMessage) {
            this.code = CODE_ERROR;
            this.message = errorMessage;
            this.action=ACTION_TOAST;
        }
        public LiveResult(int code, String message) {
            this.code = code;
            this.message = message;
        }
        public LiveResult(int code, Object data,String message) {
            this.code = code;
            this.message = message;
            this.data=data;
        }
        public LiveResult(int code, String action, String message) {
            this.code = code;
            this.action = action;
            this.message = message;
        }
        public LiveResult(int code, String action, String message, Object data) {
            this.code = code;
            this.action = action;
            this.message = message;
            this.data = data;
        }
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }



        /**
         * Created with IntelliJ IDEA.
         * <p>功能:将LiveResult对象转换为JSON字符串</p>
         * Author:<a href="mailto:longl@jumei.com">lilong</a> <br/>
         * Date:16/3/18 下午7:40 <br/>
         */
        public String toJson() {
            return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
        }

        public byte[] toUtf8Bytes() {
            try {
                return JSON.toJSONString(this).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
