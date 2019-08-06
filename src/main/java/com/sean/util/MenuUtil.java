package com.sean.util;

import com.alibaba.fastjson.JSONObject;
import com.sean.config.MenuManager;
import com.sean.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 创建菜单帮助类
 * @package_name: com.sean.util
 * @data: 2019-8-5 11:26
 * @author: Sean
 * @version: V1.0
 */
public class MenuUtil {
    private static Logger logger = LoggerFactory.getLogger(MenuUtil.class);

    /**
     * 创建菜单接口
     */
    public static void createMenu(){
        String createMenuUrl = PropertiesListenerConfig.getProperty("wx_create_menu_url");
        //获取menu
        String menuUrl = MenuManager.getMenu();
        //拼接创建菜单字符串
        createMenuUrl = createMenuUrl.replace("ACCESS_TOKEN", WxService.getAccessToken());
        //发送创建菜单请求
        String result = HttpClientUtil.doPostJson(createMenuUrl,menuUrl);
        //把返回结果转为对象
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(null != jsonObject){
            int errCode = jsonObject.getIntValue("errcode");
            //说明创建菜单成功
            if(0 == errCode){
                logger.info("创建菜单成功!!");
            } else {
                logger.error("创建菜单失败,errcode:{}errmsg:{}",jsonObject.getIntValue("errcode"),jsonObject.getString("errmsg"));
            }
        }
    }
}
