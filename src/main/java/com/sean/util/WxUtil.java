package com.sean.util;

import com.alibaba.fastjson.JSONObject;
import com.sean.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sean on 2019-8-3.
 */
public class WxUtil {
    
    private static Logger logger = LoggerFactory.getLogger(WxUtil.class);

    /**
     * 微信接入
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
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


    /**
     * 根据openId获取用户信息
     * @param openId
     * @return
     */
    public static String getUserInfoByOpenId(String openId){
        StringBuilder builder = new StringBuilder();
        String token = WxService.getAccessToken();
        //获取用户信息基本地址
        String getUserInfoUrl = PropertiesListenerConfig.getProperty("wx_get_user_info_url");
        getUserInfoUrl = getUserInfoUrl.replace("ACCESS_TOKEN",token).replace("OPENID",openId);

        String result = HttpClientUtil.doGet(getUserInfoUrl);
        JSONObject jsonObject = JSONObject.parseObject(result);
        boolean sex = jsonObject.getBooleanValue("sex");
        builder.append("地址："+jsonObject.getString("country")+"-"+jsonObject.getString("province")+"-"+jsonObject.getString("city")+"\n");

        builder.append("性别："+(sex?"男":"女") +"\n");
        builder.append("头像："+jsonObject.getString("headimgurl")+"\n");
        builder.append("备注:"+jsonObject.getString("remark")+"\n");

        logger.info("我的信息:"+result);
        return builder.toString();
    }


    /**
     * 获取临时ticket
     */
    public static String genImageTicket(){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("expire_seconds",7200);
        paramMap.put("action_name","QR_STR_SCENE");

        Map<String,Object> scene = new HashMap<>();
        scene.put("scene_str","cczid");
        paramMap.put("action_info",scene);

        String jsonStr = JSONObject.toJSONString(paramMap);
        //获取生成ticket的url
        String genImageTicketUrl = PropertiesListenerConfig.getProperty("wx_gen_image_ticket_url");
        genImageTicketUrl = genImageTicketUrl.replace("TOKEN",WxService.getAccessToken());

        logger.info("jsonStr:"+jsonStr);
        String result = HttpClientUtil.doPostJson(genImageTicketUrl,jsonStr);

        JSONObject resultJson = JSONObject.parseObject(result);
        return resultJson.getString("ticket");
    }


}
