package security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
public class AESUtils {

	private final static String AES="AES";
	/**
	 * Description 根据键值进行加密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		return String.valueOf(Base64.getEncoder().encode(bt));
	}

	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws java.io.IOException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws IOException, Exception {
		if (data == null){
			return null;
		}
		byte[] buf = Base64.getDecoder().decode(data);
		byte[] bt = decrypt(buf,key.getBytes());
		return new String(bt);
	}

	/**
	 * Description 根据键值进行加密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {

		KeyGenerator kgen = KeyGenerator.getInstance(AES);
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
		secureRandom.setSeed(key);
		kgen.init(128, secureRandom);
		byte[] enCodeFormat =  kgen.generateKey().getEncoded();
		SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat,AES);
		Cipher cipher = Cipher.getInstance(AES);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
		byte[] result = cipher.doFinal(data);
		return result; // 加密
	}
	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance(AES);
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
		secureRandom.setSeed(key);
		kgen.init(128, secureRandom);
		byte[] enCodeFormat =  kgen.generateKey().getEncoded();
		SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, AES);
		Cipher cipher = Cipher.getInstance(AES);// 创建密码器
		//Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
		cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
		return cipher.doFinal(data);     //解密
	}
}