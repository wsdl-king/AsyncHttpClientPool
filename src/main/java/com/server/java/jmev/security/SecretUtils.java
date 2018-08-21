package com.server.java.jmev.security;

import com.server.java.util.config.ConfigUtils;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.lang.StringUtils;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by FM on 2018/1/24.
 */
public class SecretUtils {
    /**
     * 访问接口必传参数
     */
    private static final String AES_KEY = ConfigUtils.getPropValues("AES_KEY");

    /**
     * 加密算法
     */
    public static String encrypt(String content) {
        try {
            if (StringUtils.isBlank(content))
                return "";

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(AES_KEY.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encode(result); // 加密
        } catch (Exception ignored) {

        }
        return null;
    }


    /**
     * 解密算法
     */
    public static String decrypt(String str) {
        try {
            byte[] content = Base64.decode(str);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(AES_KEY.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result); // 加密
        } catch (Exception ignored) {


        }
        return "";
    }
}
