package com.utils;

import javax.crypto.Cipher;  
import javax.crypto.spec.SecretKeySpec;  
  
/** 
 * This program generates a AES key, retrieves its raw bytes, and then 
 * reinstantiates a AES key from the key bytes. The reinstantiated key is used 
 * to initialize a AES cipher for encryption and decryption. 
 */  
  
public class EncrypAES {  
      
    private static final String AES = "AES";  
  
    private static final String CRYPT_KEY = "YUUAtestYUUAtest";  
  
    /** 
     * ���� 
     *
     * @param encryptStr 
     * @return 
     */  
    public static byte[] encrypt(byte[] src, String key) throws Exception {  
        Cipher cipher = Cipher.getInstance(AES);  
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);  
        cipher.init(Cipher.ENCRYPT_MODE, securekey);//������Կ�ͼ�����ʽ  
        return cipher.doFinal(src);  
    }  
  
    /** 
     * ���� 
     *  
     * @param decryptStr 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decrypt(byte[] src, String key)  throws Exception  {  
        Cipher cipher = Cipher.getInstance(AES);  
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);//���ü���Key  
        cipher.init(Cipher.DECRYPT_MODE, securekey);//������Կ�ͽ�����ʽ  
        return cipher.doFinal(src);  
    }  
      
    /** 
     * ������תʮ������ַ� 
     *  
     * @param b 
     * @return 
     */  
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1)  
                hs = hs + "0" + stmp;  
            else  
                hs = hs + stmp;  
        }  
        return hs.toUpperCase();  
    }  
  
    /** 
     * ʮ�����ת�������ַ� 
     *  
     * @param b 
     * @return 
     */
    public static byte[] hex2byte(byte[] b) {  
        if ((b.length % 2) != 0)  
            throw new IllegalArgumentException("���Ȳ���ż��");  
        byte[] b2 = new byte[b.length / 2];  
        for (int n = 0; n < b.length; n += 2) {  
            String item = new String(b, n, 2);  
            b2[n / 2] = (byte) Integer.parseInt(item, 16);  
        }  
        return b2;  
    }  
      
    /** 
     * ���� 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public final static String decrypt(String data) {  
        try {  
            return new String(decrypt(hex2byte(data.getBytes()),  
                    CRYPT_KEY));  
        } catch (Exception e) {  
        }  
        return null;  
    }  
  
    /** 
     * ���� 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public final static String encrypt(String data) {  
        try {  
            return byte2hex(encrypt(data.getBytes(), CRYPT_KEY));  
        } catch (Exception e) {  
        }  
        return null;  
    }  
      
  
    public static void main(String[] args) {  
        String ID = "b25e4b3503b835856cead2636a3e53b5";  
          
        String idEncrypt = encrypt(ID);  
        System.out.println(idEncrypt);  
        String idDecrypt = decrypt("b25e4b3503b835856cead2636a3e53b5");  
        System.out.println("idDecrypt:"+idDecrypt);  
    }  
      
}  