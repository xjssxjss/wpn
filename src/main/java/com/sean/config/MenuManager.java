package com.sean.config;

import com.alibaba.fastjson.JSONObject;
import com.sean.model.menu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 菜单管理器
 * @package_name: com.sean.config
 * @data: 2019-8-5 10:32
 * @author: Sean
 * @version: V1.0
 */
public class MenuManager {
    private static Logger logger = LoggerFactory.getLogger(MenuManager.class);

    public static String getMenu(){
        ClickButton clickButton = new ClickButton("今日歌曲","C1004_TODAY_MUSIC");
        LocationButton locationButton = new LocationButton("发送位置","L1001_LOCATION");

        ClickButton clickButton1 = new ClickButton("笨狼进城","C1001_BLJC");
        ClickButton clickButton2 = new ClickButton("公主的猫","C1002_GZDM");
        ClickButton clickButton3 = new ClickButton("会说话的卷心菜","C1003_HSHDJXC");
        ViewButton viewButton1 = new ViewButton("去百度","http://www.baidu.com");
        PicPhotoOrAlbumButton picPhotoOrAlbumButton = new PicPhotoOrAlbumButton("图片文字识别","image_orc");
        List<Button> listSubButton = new ArrayList<>();
        listSubButton.add(viewButton1);
        listSubButton.add(clickButton1);
        //listSubButton.add(clickButton2);
        //listSubButton.add(clickButton3);
        listSubButton.add(picPhotoOrAlbumButton);
        ComplexButton subButton = new ComplexButton("每日精选",listSubButton);

        List<Button> list = new ArrayList<>();
        list.add(clickButton);
        list.add(locationButton);
        //添加复合按钮
        list.add(subButton);

        Menu menu = new Menu(list);
        Object object = JSONObject.toJSON(menu);
        logger.info("菜单:{}",object.toString());
        return object.toString();
    }
}
