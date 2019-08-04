package com.sean.controller;

import com.sean.service.WxService;
import com.sean.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Created by Sean on 2019-8-3.
 */

@RestController
@RequestMapping(value = "wxController")
public class WxController extends BaseController{

    @Autowired
    private WxService wxService;
    /**
     * 接入微信
     */
    @RequestMapping(value = "wxSignIn",method = RequestMethod.POST)
    public void wxSignIn(HttpServletRequest request, HttpServletResponse response){
        String writer = wxService.getWxMessage(request,response);
        try {
            PrintWriter out = response.getWriter();
            out.print(writer);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
           timestamp	时间戳
           nonce	随机数
           echostr	随机字符串
         */
        /*String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //接入微信校验
        boolean checkSignIn = WxUtil.wxSignInCheck(timestamp,nonce,signature);
        //表示接入成功
        if(checkSignIn){
            System.out.println("接入成功>>>>>>>>>>>>>>>>>>>>>>>>>");
            try {
                //写数据
                PrintWriter writer = response.getWriter();
                writer.write(echostr);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
