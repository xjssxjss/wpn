package com.sean.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Sean on 2019-8-3.
 */
public class WxUtil {
    public static boolean wxSignInCheck(String timestamp,String nonce,String signature){
        //获取token
        String token = PropertiesListenerConfig.getProperty("token");
        System.out.println(token);

        //1、将token,timestamp,nonce三个参数进行字典排序
        String[] strs = new String[]{token,timestamp,nonce};
        //对string数组进行排序
        Arrays.sort(strs);

        //2、将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String digestResult = sha1Digest(str);
        System.out.println("加密结果>>>>>>"+digestResult);
        System.out.println("signature>>>>"+signature);

        //3、获得加密后的字符串与signature对比，标识该请求来源于微信
        if(digestResult.equalsIgnoreCase(signature)){
            return true;
        }
        return false;
    }

    /**
     *
     * @param src
     * @return
     */
    private static String sha1Digest(String src){
        StringBuilder sb = new StringBuilder();
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //进行加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

            //处理加密结果
            for (byte b : digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
