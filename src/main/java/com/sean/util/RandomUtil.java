package com.sean.util;

/**
 * @description: 随机数Util类
 * @package_name: com.sean.util
 * @data: 2019-8-7 9:14
 * @author: Sean
 * @version: V1.0
 */
public class RandomUtil {

    /**
     * 生成一个六位随机数
     * @return
     */
    public static String randomSixCode(){
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }
}
