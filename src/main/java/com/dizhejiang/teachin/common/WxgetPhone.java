package com.dizhejiang.teachin.common;

import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * @Author wuqi
 * @Date 2019/11/1
 */
public class WxgetPhone {
    public Object getPhoneNumber(String encryptedData, String session_key, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(session_key);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWxMiniPhone(String sessionkey, String iv, String encryptedData)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {

        byte[] encrypData = Base64Utils.decodeFromString(encryptedData);
        byte[] ivData = Base64Utils.decodeFromString(iv);
        byte[] sessionKey = Base64Utils.decodeFromString(sessionkey);
        String resultString = null;
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        SecretKeySpec keySpec = new SecretKeySpec(sessionKey, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding","BC");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            resultString = new String(cipher.doFinal(encrypData), "UTF-8");
        } catch (Exception e) {
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            } catch (NoSuchProviderException e1) {
                e1.printStackTrace();
            }
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            resultString = new String(cipher.doFinal(encrypData), "UTF-8");
        }
        JSONObject object = JSONObject.parseObject(resultString);
        // 拿到手机号码
        String phone = object.getString("phoneNumber");
        return phone;
    }

}
