package test;

import com.google.common.collect.Maps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by xinz on 2016/7/25.
 */
public class Test1 {
    public static void main(String[] args) throws ParseException {
        String accept=
                "11:49:07  100393002\n" +
                "11:49:34  109356206\n" +
                "11:52:05  109249990\n" +
                "11:54:09  104570443\n" +
                "11:55:31  109324950\n" +
                "18:04:21  106424161\n" +
                "18:08:36  109229889\n" +
                "18:09:39  109332356\n" +
                "18:13:29  109260525\n" +
                "18:17:44  73814963\n" +
                "18:21:47  81457106\n" +
                "18:26:55  97997801\n" +
                "18:28:08  109339006\n" +
                "18:28:55  109245655\n" +
                "18:29:41  109278587\n" +
                "18:29:59  109225824\n" +
                "18:30:32  109353410\n" +
                "18:35:12  109251730\n" +
                "18:35:25  109276489\n" +
                "18:36:14  109293947\n" +
                "18:39:20  109303508\n" +
                "18:42:57  109353378\n" +
                "18:48:05  97216655\n" +
                "18:52:25  80086158\n" +
                "18:53:15  76686384\n" +
                "18:57:11  109348299\n" +
                "18:58:13  109244116\n" +
                "18:59:00  109242458\n" +
                "19:02:04  10059271\n" +
                "19:04:55  101378252\n" +
                "19:05:34  109248113\n" +
                "19:08:28  46697851\n" +
                "19:12:14  109239537\n" +
                "19:12:36  109285385\n" +
                "19:15:56  109223469\n" +
                "19:17:06  23268029\n" +
                "19:17:24  105188237\n" +
                "19:21:22  93028172\n" +
                "19:21:47  109275212\n" +
                "19:22:01  109316127\n" +
                "19:24:22  25827327\n" +
                "19:24:39  109273633\n" +
                "19:25:37  109349095\n" +
                "19:28:15  106822380\n" +
                "19:29:42  105397639\n" +
                "19:37:05  108923858\n" +
                "19:38:21  87685689\n" +
                "19:39:07  109246428\n" +
                "19:40:21  86202789\n" +
                "19:43:42  109324223\n" +
                "19:47:16  109278312\n" +
                "19:49:06  109364952\n" +
                "19:50:33  8411621\n" +
                "19:52:57  12762717\n" +
                "19:55:05  24398087\n" +
                "19:57:25  102896902\n" +
                "20:02:11  105856552\n" +
                "20:05:58  109206214\n" +
                "20:07:25  105682953\n" +
                "20:10:42  109252902\n" +
                "20:11:03  106386471\n" +
                "20:15:34  106124732\n" +
                "20:18:40  109371937\n" +
                "20:28:14  109312810\n" +
                "20:30:38  97389084\n" +
                "20:31:42  89564771\n" +
                "20:33:49  83442433\n" +
                "20:36:28  109323771\n" +
                "20:38:54  89564771\n" +
                "20:45:56  89849874\n" +
                "20:47:46  109326586\n" +
                "20:49:27  109384286\n" +
                "20:57:08  75409496\n" +
                "21:00:38  109352996\n" +
                "21:01:46  106062168\n" +
                "21:04:54  109326271\n" +
                "21:05:13  109326586\n" +
                "21:08:26  105808793\n" +
                "21:16:45  105808793\n" +
                "21:18:01  50732879\n" +
                "21:21:43  94610217\n" +
                "21:24:44  47688246\n" +
                "21:29:57  105808793\n" +
                "21:32:08  103214521\n" +
                "21:33:55  109297954\n" +
                "21:34:31  105834769\n" +
                "21:43:05  100563286\n" +
                "21:43:26  109318258\n" +
                "21:46:11  109320000\n" +
                "21:46:47  105244032\n" +
                "21:51:24  109324304";

        String quit = "11:54:05  109249990\n" +
                "11:55:27  104570443\n" +
                "11:56:41  109324950\n" +
                "18:08:10  106424161\n" +
                "18:09:32  109229889\n" +
                "18:13:07  109332356\n" +
                "18:16:54  109260525\n" +
                "18:21:23  73814963\n" +
                "18:26:33  81457106\n" +
                "18:28:02  97997801\n" +
                "18:28:42  109339006\n" +
                "18:29:34  109245655\n" +
                "18:30:27  109225824\n" +
                "18:35:00  109353410\n" +
                "18:35:59  109276489\n" +
                "18:38:57  109293947\n" +
                "18:42:41  109303508\n" +
                "18:47:58  109353378\n" +
                "18:52:09  97216655\n" +
                "18:52:54  80086158\n" +
                "18:57:04  76686384\n" +
                "18:58:06  109348299\n" +
                "18:58:53  109244116\n" +
                "19:01:47  109242458\n" +
                "19:04:47  10059271\n" +
                "19:05:30  101378252\n" +
                "19:07:56  109248113\n" +
                "19:12:01  46697851\n" +
                "19:12:34  109239537\n" +
                "19:15:49  109285385\n" +
                "19:17:21  23268029\n" +
                "19:21:00  105188237\n" +
                "19:21:46  93028172\n" +
                "19:21:59  109275212\n" +
                "19:24:02  109316127\n" +
                "19:24:36  25827327\n" +
                "19:28:07  109349095\n" +
                "19:29:39  106822380\n" +
                "19:38:15  108923858\n" +
                "19:39:01  87685689\n" +
                "19:40:15  109246428\n" +
                "19:43:15  86202789\n" +
                "19:46:53  109324223\n" +
                "19:48:48  109278312\n" +
                "19:50:11  109364952\n" +
                "19:52:55  8411621\n" +
                "19:54:55  12762717\n" +
                "19:57:22  24398087\n" +
                "20:02:07  102896902\n" +
                "20:10:02  105682953\n" +
                "20:11:00  109252902\n" +
                "20:15:31  106386471\n" +
                "20:18:33  106124732\n" +
                "20:31:04  97389084\n" +
                "20:36:02  83442433\n" +
                "20:38:50  109323771\n" +
                "20:45:50  89564771\n" +
                "20:47:40  89849874\n" +
                "20:47:52  109326586\n" +
                "20:57:05  109384286\n" +
                "21:00:08  75409496\n" +
                "21:01:37  109352996\n" +
                "18:29:50  109278587\n" +
                "19:16:40  109223469\n" +
                "20:19:06  109371937\n" +
                "21:16:07  94610217\n" +
                "21:20:44  50732879\n" +
                "21:20:45  50732879\n" +
                "21:04:50  106062168\n" +
                "21:07:08  109326586\n" +
                "21:10:09  105808793\n" +
                "21:29:50  47688246\n" +
                "21:32:02  105808793\n" +
                "21:33:48  103214521\n" +
                "21:34:27  109297954\n" +
                "21:37:09  105834769\n" +
                "21:46:04  109318258\n" +
                "21:46:44  109320000\n" +
                "21:50:11  105244032\n" +
                "21:51:42  109324304";

        Map<String,String> quitMap = timeUid(quit);
        Map<String,String> acceptMap = timeUid(accept);

        for(String uid:acceptMap.keySet()){
            if(quitMap.get(uid) != null){
                String actime = "2016-07-25 "+acceptMap.get(uid);
                String qutime = "2016-07-25 "+quitMap.get(uid);

                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date ac = sd.parse(actime);
                Date qu = sd.parse(qutime);
                if(qu.getTime()/1000 - ac.getTime()/1000 <= 15 && qu.getTime()/1000 - ac.getTime()/1000 > 0 ){
                    System.out.println("连线者："+uid+"  startTime:"+acceptMap.get(uid)+"  endTime:"+quitMap.get(uid)+"  cost:"+(qu.getTime()/1000 - ac.getTime()/1000)+" 秒");
                }
            }
        }


    }

    private static Map<String,String> timeUid(String ele){
        String[] t = ele.split("\n");
        Map<String,String> tt = Maps.newHashMap();
        for(String e:t){
            tt.put(e.split("  ")[1],e.split("  ")[0]);
        }
        return tt;
    }
}
