package com.sean.util;

import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @description: 图片中文字识别
 * @package_name: com.sean.util
 * @data: 2019-8-6 9:11
 * @author: Sean
 * @version: V1.0
 */
public class AipOcrUtil {

    private static AipOcr client = null;

    /**
     * 图片文字识别(远程)
     * @param url
     * @return
     */
    public static String imageUrlOcr(String url){
        //实例化StringBuilder对象
        StringBuilder stringBuilder = new StringBuilder();

        // 初始化一个AipOcr
        if(null == client){
            client = new AipOcr(
PropertiesListenerConfig.getProperty("ai_app_id"),
                                PropertiesListenerConfig.getProperty("ai_api_key"),
                                PropertiesListenerConfig.getProperty("ai_secret_key"));
        }

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 本地调用接口
        //JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        //System.out.println(res.toString(2));

        // 通用文字识别（含位置信息版）, 图片参数为远程url图片
        //获取jsonObject
        org.json.JSONObject jsonObject = client.generalUrl(url, new HashMap<>());

        try {
            if (null != jsonObject){
                //把json字符串转化为对象
                JSONObject aliJsonObject = JSONObject.parseObject(jsonObject.toString());

                //获取返回结果的json数组
                JSONArray jsonArray = aliJsonObject.getJSONArray("words_result");

                if(null != jsonArray && jsonArray.size()>0){
                    //对JSONArray数组进行迭代循环
                    Iterator iterator = jsonArray.iterator();
                    while(iterator.hasNext()){
                        //获取内层JSONObject
                        JSONObject object = (JSONObject) iterator.next();
                        //把对象中的识别的文字进行拼接
                        stringBuilder.append(object.get("words")+"\n");
                    }
                } else {
                    stringBuilder.append("图片中未检测到文字，来换张试试~~~");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return "图片解析失败:失败原因{}"+e.getMessage();
        }
        return stringBuilder.toString();
    }
}
