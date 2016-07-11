package security;

/**
 * Created by xinz on 2016/6/1.
 */
public class Tester {
    final static String key = "8e2bf219";
    public static void main(String[] args) {
//        String password = "123456";
//        try {
//            String rs = AESUtils.encrypt(password,key);
//            System.out.println(rs);
//            rs = AESUtils.decrypt(rs,key);
//            System.out.println(rs);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(getSign());
    }

    private static String getSign(){

        StringBuilder buf = new StringBuilder();
        buf.append("carts=[{\"buyer_id\":\"1\",\"extra\":\"\",\"weibo_iid\":\"1\"}]");
        buf.append("9a58577aae3b2e878876b160e4a49b8e");
//        buf.append("buyer_id=" + "1344513317");
//        buf.append("&buyer_source=" + "sina_weibo");
//        buf.append("&items=" + "[{\"sku_no\":\"df2768694050176987\",\"unique_product_id\":\"deal_df160627p2768694\",\"quantity\":\"1\",\"seller_type\":\"sina_weibo\",\"seller_id\":\"1305672987\",\"seller_product_id\":\"110013056729870003033509\",\"seller_extra\":\"\",\"sellType\":\"WeiboLive\",\"sellLabel\":123,\"add_extra\":\"\",\"add_time\":1467342794}]");
//        buf.append("27243547mefdg92hh35626p1b7s9fz7y");
        System.out.println(buf.toString());
        return MD5Util.encrypt(buf.toString());
    }
}
