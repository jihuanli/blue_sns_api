package com.dabanniu.core.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class OpenSSLDecryptor {
	    private static final int INDEX_KEY = 0;
		private static final int INDEX_IV = 1;
		private static final int ITERATIONS = 1;
	 
		private static final int KEY_SIZE_BITS = 128;
		
		/**
		 * Thanks go to Ola Bini for releasing this source on his blog. The source was obtained from <a
		 * href="http://olabini.com/blog/tag/evp_bytestokey/">here</a> .
		 */
		public static byte[][] EVP_BytesToKey(int key_len, int iv_len, MessageDigest md, byte[] salt, byte[] data, int count) {
			byte[][] both = new byte[2][];
			byte[] key = new byte[key_len];
			int key_ix = 0;
			byte[] iv = new byte[iv_len];
			int iv_ix = 0;
			both[0] = key;
			both[1] = iv;
			byte[] md_buf = null;
			int nkey = key_len;
			int niv = iv_len;
			int i = 0;
			if (data == null) {
				return both;
			}
			int addmd = 0;
			for (;;) {
				md.reset();
				if (addmd++ > 0) {
					md.update(md_buf);
				}
				md.update(data);
				if (null != salt) {
					md.update(salt, 0, 8);
				}
				md_buf = md.digest();
				for (i = 1; i < count; i++) {
					md.reset();
					md.update(md_buf);
					md_buf = md.digest();
				}
				i = 0;
				if (nkey > 0) {
					for (;;) {
						if (nkey == 0)
							break;
						if (i == md_buf.length)
							break;
						key[key_ix++] = md_buf[i];
						nkey--;
						i++;
					}
				}
				if (niv > 0 && i != md_buf.length) {
					for (;;) {
						if (niv == 0)
							break;
						if (i == md_buf.length)
							break;
						iv[iv_ix++] = md_buf[i];
						niv--;
						i++;
					}
				}
				if (nkey == 0 && niv == 0) {
					break;
				}
			}
			for (i = 0; i < md_buf.length; i++) {
				md_buf[i] = 0;
			}
			return both;
		}
	 
		public static byte[] encrypt(byte[] salt, byte[] contents, String pw) throws NoSuchAlgorithmException, NoSuchPaddingException,
		        InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
	 
			final byte[][] keyAndIV = EVP_BytesToKey(KEY_SIZE_BITS / Byte.SIZE, aesCBC.getBlockSize(), md5, salt, pw.getBytes(), ITERATIONS);
			SecretKeySpec key = new SecretKeySpec(keyAndIV[INDEX_KEY], "AES");
			IvParameterSpec iv = new IvParameterSpec(keyAndIV[INDEX_IV]);
	 
			aesCBC.init(Cipher.ENCRYPT_MODE, key, iv);
			return aesCBC.doFinal(contents);
		}
	 
		public static byte[] decrypt(byte[] salt, byte[] encrypted, String pw) throws NoSuchAlgorithmException, NoSuchPaddingException,
		        InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
	 
			final byte[][] keyAndIV = EVP_BytesToKey(KEY_SIZE_BITS / Byte.SIZE, aesCBC.getBlockSize(), md5, salt, pw.getBytes(), ITERATIONS);
			SecretKeySpec key = new SecretKeySpec(keyAndIV[INDEX_KEY], "AES");
			IvParameterSpec iv = new IvParameterSpec(keyAndIV[INDEX_IV]);
	 
			aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
			return aesCBC.doFinal(encrypted);
		}
	 
		
		/**将二进制转换成16进制 
		 * @param buf 
		 * @return 
		 */  
		public static String parseByte2HexStr(byte buf[]) {  
		        StringBuffer sb = new StringBuffer();  
		        for (int i = 0; i < buf.length; i++) {  
		                String hex = Integer.toHexString(buf[i] & 0xFF);  
		                if (hex.length() == 1) {  
		                        hex = '0' + hex;  
		                }  
		                sb.append(hex.toUpperCase());  
		        }  
		        return sb.toString();  
		}  
		/**将16进制转换为二进制 
		 * @param hexStr 
		 * @return 
		 */  
		public static byte[] parseHexStr2Byte(String hexStr) {  
		        if (hexStr.length() < 1)  
		                return null;  
		        byte[] result = new byte[hexStr.length()/2];  
		        for (int i = 0;i< hexStr.length()/2; i++) {  
		                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
		                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
		                result[i] = (byte) (high * 16 + low);  
		        }  
		        return result;  
		}  	
		

		public static void main(String[] args) throws Exception {
//			String pw="!.com_daniuban_8j8l@";
//			String content="100000158@1374015438@30";
//			byte[] salt = "1234567890".getBytes();
//			byte[] encrypted = content.getBytes();
	 
//			byte[] decrypted = encrypt(null, encrypted, pw);
//			String str = Base16.encode(decrypted);
//			String sessionKey=parseByte2HexStr(decrypted);
//			System.out.println(sessionKey);
//			
//			System.out.println(decrypt(null, decrypted, pw));
//			System.out.println(new String(decrypt(null, decrypted, pw),"utf-8"));
//			
//			String sesssionKey="E2B364DC5A9F860A7878AC6F090E0829004375AF441E23BD7EF1B7C845159F2A";
//			
//			System.out.println(new String(decrypt(null, parseHexStr2Byte(sesssionKey), pw),"utf-8"));
//			String answer = new String(decrypt(salt, decrypted, pw));
//			System.out.println(answer);
//			answer = new String(decrypt(salt, Base16.decode(str), pw));
//			System.out.println(answer);
		}
}
