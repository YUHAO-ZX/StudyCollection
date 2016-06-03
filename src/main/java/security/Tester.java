package security;

/**
 * Created by xinz on 2016/6/1.
 */
public class Tester {
    final static String key = "8e2bf219";
    public static void main(String[] args) {
        String password = "123456";
        try {
            String rs = AESUtils.encrypt(password,key);
            System.out.println(rs);
            rs = AESUtils.decrypt(rs,key);
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
