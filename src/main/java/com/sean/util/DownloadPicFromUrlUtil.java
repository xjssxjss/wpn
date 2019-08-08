package com.sean.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * @description: 从网上下载图片
 * @package_name: com.sean.util
 * @data: 2019-8-7 17:10
 * @author: Sean
 * @version: V1.0
 */
public class DownloadPicFromUrlUtil {

    //链接url下载图片
    public static boolean downloadPicture(String urlList,String path) {
        String fileName = StringUtil.toStringExcludeNull(urlList.substring(urlList.lastIndexOf("/") + 1));
        //showqrcode?ticket=gQEh7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAybEJZX014eVVjajExcG11Yk50Y2sAAgQ2gktdAwQgHAAA
        //String urlStr = StringUtil.toStringExcludeNull(urlList.substring(0,urlList.lastIndexOf("/")+ 1));
        String urlStr= "https://mp.weixin.qq.com/cgi-bin/";
        boolean flag = false;
        URL url = null;
        try {
            System.out.println("fileName:"+(urlStr+fileName));

            fileName=java.net.URLEncoder.encode(fileName, "UTF-8");
            url = new URL(urlStr+fileName);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
            flag = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String getPicture(String urlHttp){
        String file = "D:/wpn/src/main/resources/upload/" + new Date().getTime() + ".jpg";
        try {
            URL url = new URL(urlHttp);
            BufferedImage img = ImageIO.read(url);
            ImageIO.write(img, "jpg", new File(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void main(String[] args) {
        String url = "http://www.mylife.philips.com.cn/Upload/Invoiceh5/20181106200843411269181106200843.jpg";
        String path="D:/wpn/src/main/resources/upload/123456.jpg";
        downloadPicture(url,path);
    }

}
