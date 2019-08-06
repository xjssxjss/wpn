package com.sean;

import com.baidu.aip.ocr.AipOcr;
import com.sean.util.AipOcrUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @description: TODO
 * @package_name: com.sean
 * @data: 2019-8-6 8:59
 * @author: Sean
 * @version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageTests {

    //设置APPID/AK/SK
    public static final String APP_ID = "16958077";
    public static final String API_KEY = "bx85TLhQzVB2D6pRPMXvoKSE";
    public static final String SECRET_KEY = "k85Iw9fXVezpQFkroBrWw3UhDfBybv3p";


    @Test
    public void aic(){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565063424116&di=97c3d3df55370e73ec138762981f8418&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201705%2F15%2F20170515065704_AZPCW.thumb.700_0.jpeg";
        //JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        //System.out.println(res.toString(2));

        // 通用文字识别（含位置信息版）, 图片参数为远程url图片
        JSONObject res = client.generalUrl(url, new HashMap<>());
        System.out.println(res.toString());
    }

    @Test
    public void testImageOrc(){
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565063424116&di=97c3d3df55370e73ec138762981f8418&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201705%2F15%2F20170515065704_AZPCW.thumb.700_0.jpeg";
        System.out.println(AipOcrUtil.imageUrlOcr(url));
    }
}
