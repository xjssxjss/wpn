package com.sean.common;

/**
 * 常量配置
 */
public class GlobalConstant {
    //项目前缀
    public static String KEY_PREFIX = "wpn";

    //文件常用后缀
    public static String PROP_SUFFIX = ".properties";

    //所有配置文件名称,多个中间以,逗号进行分隔
    public static final String BASE_FILE_NAMES = "wx,wpn,tuling,ai,sms";

    public static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=UTF-8";

    //微信公众号发送的消息类型
    public static final String WX_MSG_TYPE = "MsgType";


    /**********************微信点击按钮定义key****************************/
    public static final String C1001_BLJC = "C1001_BLJC";
    public static final String C1002_TGEWM = "C1002_TGEWM";
    public static final String C1003_HSHDJXC = "C1003_HSHDJXC";
    public static final String C1004_TODAY_MUSIC = "C1004_TODAY_MUSIC";


    public static final String C1001_BLJC_CONTENT = "听说城里很热闹，笨狼就想去看看。";

    /*声明微信模板所属行业*/
    //设置第一行业(互联网/电子商务)
    public static final String INDUSTRY_ID1 = "1";
    //设置第二行业(IT软件与服务)
    public static final String INDUSTRY_ID2 = "2";


    /*微信模板Id定义*/
    //待办任务提醒  {{first.DATA}} 待办内容：{{keyword1.DATA}} 时间：{{keyword2.DATA}} 地点：{{keyword3.DATA}} {{remark.DATA}}
    public static final String WX_TEMPLATE_ID1 = "XAjp5MfpnhXoyINMJuLKEMFOmFQGIE7FBQ66fOx1zlA";

}
