package com.sean.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.ImageUtil;
import com.sean.common.GlobalConstant;
import com.sean.model.*;
import com.sean.util.AipOcrUtil;
import com.sean.util.HttpClientUtil;
import com.sean.util.PropertiesListenerConfig;
import com.sean.util.TuLingUtil;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sean on 2019-8-4.
 */
@Service
public class WxService {

    private static AccessToken accessToken;
    /**
     * 解析微信发过来的消息
     * @param request
     * @param response
     * @return
     */
    public String getWxMessage(HttpServletRequest request, HttpServletResponse response){
        //封装回复的数据包
        String respXml = null;
        try {
            System.out.println("token"+getAccessToken());;
            request.setCharacterEncoding("utf8");
            response.setCharacterEncoding("utf8");
            InputStream is = request.getInputStream();
            //处理xml之后放入map集合中
            Map<String,String> parseResultMap = parseRequest(is);
            System.out.println("解析结果>>>>>"+parseResultMap);

            respXml = getResponseXml(parseResultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respXml;
    }

    /**
     * 处理所有事件和消息的回复
     * @param requestMap
     * @return
     */
    private String getResponseXml(Map<String,String> requestMap) {
        BaseMessage baseMessage = null;
        //获取微信公众号发送的消息类型
        String msgType = requestMap.get(GlobalConstant.WX_MSG_TYPE);

        //消息转成对象xml包
        switch (msgType){
            case "text":
                baseMessage = dealTextMessage(requestMap);
                break;

            case "image":
                baseMessage = dealImageMessage(requestMap);
                break;

            case "voice":
                baseMessage = dealVoiceMessage(requestMap);
                break;

            case "music":
                baseMessage = dealMusicMessage(requestMap);
                break;

            case "video":
                break;

            case "link":
                break;

            case "event":
                //处理事件
                baseMessage = dealEventMessage(requestMap);
                break;


        }
        System.out.println(beanToXml(baseMessage));
        return beanToXml(baseMessage);
    }

    /**
     * 处理发送的音乐信息
     * @param requestMap
     * @return
     */
    private BaseMessage dealMusicMessage(Map<String, String> requestMap) {
        Music music = new Music();
        MusicMessage musicMessage = new MusicMessage(requestMap,music);
        return musicMessage;
    }

    /**
     * 回复图片消息
     * @param requestMap
     * @return
     */
    private BaseMessage dealImageMessage(Map<String, String> requestMap) {
        //发送图片消息之后，对图片进行处理
        String url = requestMap.get("PicUrl");
        //把识别结果返回给客户端
        TextMessage textMessage = new TextMessage(requestMap,AipOcrUtil.imageUrlOcr(url));
        return textMessage;
    }

    /**
     * 回复用户发送的语音消息
     * @param requestMap
     * @return
     */
    private BaseMessage dealVoiceMessage(Map<String, String> requestMap) {
        //TextMessage textMessage = new TextMessage(requestMap,"小样儿，我竟然都听不出来这是普通话~~~");
        //0NTAAaZZvmUg8H2lu_1oVgc93LM4lH7g_yv0coYM3v6ztgKJzuGjKkyiPW_VMUVx
        VoiceMessage voiceMessage = new VoiceMessage(requestMap,new Voice("0NTAAaZZvmUg8H2lu_1oVgc93LM4lH7g_yv0coYM3v6ztgKJzuGjKkyiPW_VMUVx"));
        return voiceMessage;
    }

    /**
     * 发生点击事件的时候触发
     * @param requestMap
     * @return
     */
    private BaseMessage dealEventMessage(Map<String, String> requestMap) {
        //获取事件类型
        String event = requestMap.get("Event");
        switch (event){
            case "CLICK":
                return dealClickMessage(requestMap);
            case "subscribe":
                return dealSubscribeMessage(requestMap);
            case "unsubscribe":
                return dealUnSubscribeMessage(requestMap);
        }
        return null;
    }

    /**
     * 用户取关公众号的时候触发
     * @param requestMap
     * @return
     */
    private BaseMessage dealUnSubscribeMessage(Map<String, String> requestMap) {
        TextMessage textMessage = new TextMessage(requestMap,"期待与您再次相会~~~");
        return textMessage;
    }

    /**
     * 用户关注公众号的时候触发
     * @param requestMap
     * @return
     */
    private BaseMessage dealSubscribeMessage(Map<String, String> requestMap) {
        TextMessage textMessage = new TextMessage(requestMap,"欢迎关注~~~");
        return textMessage;
    }

    /**
     * 处理用户点击事件
     * @param requestMap
     */
    private BaseMessage dealClickMessage(Map<String, String> requestMap) {
        BaseMessage baseMessage = null;

        String eventKey = requestMap.get("EventKey");
        //获取用户点击所对应的EventKey
        if(GlobalConstant.C1001_BLJC.equals(eventKey)){
            //笨狼进城
            baseMessage = new TextMessage(requestMap,GlobalConstant.C1001_BLJC_CONTENT.replaceAll(" ","").replaceAll("\n",""));
        } else if(GlobalConstant.C1004_TODAY_MUSIC.equals(eventKey)){
            Music music = new Music("像风一样 (《小女花不弃》电视剧插曲)","这个秋天，请一边看着风，一边听着薛之谦的「像风一样」。 你会发现，你的心被什么挑拨了。 那是风。","https://y.qq.com/n/yqq/song/001uxKNp3a7Qkv.html?ADTAG=baiduald&play=1","https://y.qq.com/n/yqq/song/001uxKNp3a7Qkv.html?ADTAG=baiduald&play=1","79mDN48liiZbyAsgOsRbFoCDXGrL4x9P4-8zxEY-qgacBVo04fmqjuVBRIokoaiE");
            baseMessage = new MusicMessage(requestMap,music);
        }
        return baseMessage;
    }

    /**
     * 回复客户端发送的文本消息
     * @param requestMap
     * @return
     */
    private BaseMessage dealTextMessage(Map<String, String> requestMap) {
        TextMessage textMessage = null;

        if(requestMap.get("Content").equals("图片")){
            ImageMessage imageMessage = new ImageMessage(requestMap,new Image("URSef5rQ60eygVRaaABAbnNqFzNz2Px3tR5SfQxl-Pi4GazXjaSoQN0ZKOA9nHUQ"));
            return imageMessage;
        } else if(requestMap.get("Content").equals("声音")){
            VoiceMessage voiceMessage = new VoiceMessage(requestMap,new Voice("0NTAAaZZvmUg8H2lu_1oVgc93LM4lH7g_yv0coYM3v6ztgKJzuGjKkyiPW_VMUVx"));
            return voiceMessage;
        } else if(requestMap.get("Content").equals("图文")){
            List<Article> articles = new ArrayList<>();
            Article article = new Article("如果不爱他,跟他结婚会有什么下场!","一辈子的时间不长，不要总拿来撞南墙","http://attimg.dospy.com/img/day_080625/20080625_3ab0023ac4806e36491fEPzkebAg4lio.jpg","www.baidu.com");
            articles.add(article);
            NewsMessage newsMessage = new NewsMessage(requestMap,articles);
            return newsMessage;
        }
        //声明返回给公众号的字符串对象
        String resultMsg = null;
        String result =null;
        String url ="http://op.juhe.cn/iRobot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", PropertiesListenerConfig.getProperty("tuling_appkey"));//您申请到的本接口专用的APPKEY
        params.put("info",requestMap.get("Content"));//要发送给机器人的内容，不要超过30个字符
        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
        params.put("loc","");//地点，如北京中关村
        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        try {
            result =TuLingUtil.net(url,params,"POST");
            //获取解析返回的json字符串
            JSONObject object = JSONObject.parseObject(result);
            //返回结果
            if(object.getIntValue("error_code") == 0){
                //说明成功返回,获取返回的结果对象
                JSONObject resultObject = object.getJSONObject("result");
                resultMsg = (String)resultObject.get("text");
            } else {
                resultMsg = object.get("error_code")+":"+object.get("reason");
            }
            textMessage = new TextMessage(requestMap,resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //对接图灵机器人
        return textMessage;
    }

    /**
     * 把java对象转化为xml
     * @return
     */
    private static String beanToXml(BaseMessage baseMessage){
        //实例化XStream对象
        XStream stream = new XStream();
        //设置需要转化为xml的对象
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(MusicMessage.class);
        return stream.toXML(baseMessage);
    }

    /**
     * 解析微信公众号发来的消息
     * @param is
     * @return
     */
    private Map<String,String> parseRequest(InputStream is) {
        Map<String,String> parseResultMap = new HashMap<>();
        //声明读取xml的对象
        SAXReader reader = new SAXReader();
        try {
            //1、根据saxReader读取流对象
            Document document = reader.read(is);
            //2、获取根节点
            Element rootElement = document.getRootElement();
            //3、获取根节点的所有子节点
            List<Element> listElement = rootElement.elements();
            //4、循环遍历节点对象
            for (Element element : listElement) {
                parseResultMap.put(element.getName(),element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return parseResultMap;
    }

    /**
     * 根据api地址获取token
     */
    private static void getToken(){
        String result = "";
        //获取accessToken的url
        String getAccessTokenUrl = PropertiesListenerConfig.getProperty("wx_get_access_token_url");

        String url = getAccessTokenUrl+"&appid="+PropertiesListenerConfig.getProperty("wx_app_id")+"&secret="+PropertiesListenerConfig.getProperty("wx_app_secret");
        result = HttpClientUtil.doGet(url);

        //对返回的结果转为JSONObject对象
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
        String token = (String)jsonObject.get("access_token");
        String expiresIn = jsonObject.get("expires_in").toString();

        accessToken = new AccessToken(token,expiresIn);
        System.out.println(result);
    }

    /**
     * 向外暴露的方法判断是否已过期
     * @return
     */
    public static String getAccessToken(){
        if(null == accessToken || accessToken.isExpired()){
            //获取accessToken
            getToken();
        }
        return accessToken.getAccessToken();
    }
}
