package test;

/**
 * Created by xinz on 2016/7/26.
 */
public class Test2 {
    public static void main(String[] args) {
            System.out.println("ZREVRANGEBYSCORE".toLowerCase());
        String t = "109728415\n" +
                "88979474\n" +
                "109683251\n" +
                "109622347\n" +
                "109216584\n" +
                "109695804\n" +
                "109403350\n" +
                "109329003\n" +
                "109322573\n" +
                "109330558\n" +
                "99434822\n" +
                "108155770\n" +
                "109274197\n" +
                "109322650\n" +
                "108018019\n" +
                "93928671\n" +
                "104571871\n" +
                "109410489\n" +
                "101228197\n" +
                "109669712\n" +
                "79413179\n" +
                "101513294\n" +
                "109288049\n" +
                "109084447\n" +
                "109421848\n" +
                "3309443\n" +
                "109420050\n" +
                "109384750\n" +
                "109243921\n" +
                "95166852\n" +
                "83413262\n" +
                "109330867\n" +
                "109332147\n" +
                "109547308\n" +
                "109461565\n" +
                "13906715\n" +
                "99362293\n" +
                "16805931\n" +
                "109313873\n" +
                "109526190\n" +
                "109435890\n" +
                "109423004\n" +
                "109325680\n" +
                "109682538\n" +
                "109422808\n" +
                "109332749\n" +
                "109334939\n" +
                "109503394\n" +
                "108309297\n" +
                "108360226\n" +
                "109375011\n" +
                "109334893\n" +
                "109361033\n" +
                "109321351\n" +
                "66410446\n" +
                "109579308\n" +
                "107996439\n" +
                "76376775\n" +
                "97242366\n" +
                "101644705\n" +
                "109623769\n" +
                "20605579\n" +
                "109439876\n" +
                "88387922\n" +
                "80749689\n" +
                "90740526\n" +
                "109550862\n" +
                "109418636\n" +
                "109381645\n" +
                "98699417\n" +
                "109423831\n" +
                "87486647\n" +
                "87663487\n" +
                "88434363\n" +
                "51842474\n" +
                "84154308\n" +
                "109331268\n" +
                "109061400\n" +
                "96567637\n" +
                "22606639\n" +
                "109434137\n" +
                "109297844\n" +
                "109335207\n" +
                "109263972\n" +
                "109227720\n" +
                "88769679\n" +
                "109376843\n" +
                "109310758\n" +
                "109363740\n" +
                "109276250\n" +
                "109104554\n" +
                "86970549\n" +
                "109341782\n" +
                "109336005\n" +
                "109402281\n" +
                "105727318\n" +
                "109483974\n" +
                "109289108\n" +
                "109219792\n" +
                "109251862\n" +
                "109318630\n" +
                "7114937\n" +
                "1089533\n" +
                "65189254\n" +
                "24165307\n" +
                "109244151\n" +
                "109499532\n" +
                "109346780\n" +
                "109216708\n" +
                "109328908\n" +
                "94923613\n" +
                "88580985\n" +
                "109339006\n" +
                "108155770\n" +
                "107454697\n" +
                "104912157\n" +
                "87662640\n" +
                "88466618\n" +
                "94157267\n" +
                "108390598";
        String[] m = t.split("\n");
        for(String ele:m){
            System.out.print("\""+ele+"\",");
        }
    }
}
