package security;

import java.security.MessageDigest;

/** 
* 
* 描述: MD5工具类.
* @author haow
*
*/

public class MD5Util {
	// 缓存大小
	public static final int BUFF_SIZE = 256 * 1024;
	public static final int HEX_DEF = 0XFF;

	/**
	 * 对字符串进行MD5加密.
	 * @param value 字符串
	 * @return 加密结果
	 */
	public static String encrypt(String value) {
		try {
			// 将字符串转换成字节数组
			byte[] strTemp = value.getBytes();

			// 调用消息摘要算法，执行加密为md5
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();

			return byte2hex(md).toLowerCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字节数组转换成字符串.
	 * @param bt 字节数组
	 * @return 字符串
	 */
	public static String byte2hex(byte[] bt) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < bt.length; n++) {
			stmp = Integer.toHexString(bt[n] & HEX_DEF);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	public static void main(String[] args) {
		String string="1406201600";//微博消息同步来源
		String str = MD5Util.encrypt(string);//key
		System.out.println(str);
	}
}
