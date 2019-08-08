package com.sean.util;

import com.alibaba.fastjson.JSONObject;
import com.sean.common.GlobalConstant;
import com.sean.model.template.MiniProgram;
import com.sean.model.template.Template;
import com.sean.model.template.TemplateData;
import com.sean.model.template.WxTeamplte;
import com.sean.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 微信模板util类
 * @package_name: com.sean.util
 * @data: 2019-8-7 9:21
 * @author: Sean
 * @version: V1.0
 */
public class WxTemplateUtil {

    private static Logger logger = LoggerFactory.getLogger(WxTemplateUtil.class);
    /**
     * 微信设置模板行业
     */
    public static void wxSetTrade(){
        Map<String,String> paramsMap = new HashMap<>();
        //设置第一个行业
        paramsMap.put("industry_id1", GlobalConstant.INDUSTRY_ID1);
        paramsMap.put("industry_id2", GlobalConstant.INDUSTRY_ID2);

        String jsonStr = JSONObject.toJSONString(paramsMap);

        //获取token
        String token = WxService.getAccessToken();
        //获取设置行业的url
        String setTradeUrl = PropertiesListenerConfig.getProperty("wx_trade_template_url");
        setTradeUrl = setTradeUrl.replace("ACCESS_TOKEN",token);

        //发送post请求
        String result = HttpClientUtil.doPostJson(setTradeUrl,jsonStr);
        logger.info("设置模板结果:"+result);
    }

    /**
     * 获取设置的行业信息
     */
    public static void wxGetTrade(){
        //获取token
        String token = WxService.getAccessToken();

        //获取设置行业的url
        String getTradeUrl = PropertiesListenerConfig.getProperty("wx_get_trade_url");
        getTradeUrl = getTradeUrl.replace("ACCESS_TOKEN",token);

        String result = HttpClientUtil.doPost(getTradeUrl);
        logger.info("获取模板结果:"+result);
    }


    /**
     * 发送模板消息
     */
    public static void sendTemplateMessage(String openId){
        String token = WxService.getAccessToken();
        //获取发送模板信息地址
        String sendTemplateUrl = PropertiesListenerConfig.getProperty("wx_send_template_url");

        sendTemplateUrl = sendTemplateUrl.replace("ACCESS_TOKEN",token);
        Template first = new Template("您有新的待办任务!","#C5DC6C");
        Template keyword1 = new Template("该找对象啦","#173177");
        Template keyword2 = new Template("2019年8月7日","#008000");
        Template keyword3 = new Template("夏威夷","#173177");
        Template remark = new Template("祝您早日脱单！！","#ABDCFA");

        //实例化data对象
        TemplateData data = new TemplateData(first,keyword1,keyword2,keyword3,remark);

        //实例化MiniProgram对象
        MiniProgram miniProgram = new MiniProgram();
        miniProgram.setAppid("wx83187f74bb3ffcba");
        miniProgram.setPagepath("pages/index/index");

        //实例化发送模板的对象
        WxTeamplte wxTeamplte = new WxTeamplte(openId,GlobalConstant.WX_TEMPLATE_ID1,"www.baidu.com",data);
        String jsonStr = JSONObject.toJSONString(wxTeamplte);

        System.out.println(jsonStr);
        //发送模板消息
        String result = HttpClientUtil.doPostJson(sendTemplateUrl,jsonStr);
        logger.info("发送模板消息结果:"+result);
    }
}
