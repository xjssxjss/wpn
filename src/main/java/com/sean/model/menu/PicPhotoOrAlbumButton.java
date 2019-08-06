package com.sean.model.menu;

/**
 * @description: 发送本地图片或者拍照发送
 * @package_name: com.sean.model.menu
 * @data: 2019-8-6 9:40
 * @author: Sean
 * @version: V1.0
 */
public class PicPhotoOrAlbumButton extends Button{
    private String type = "pic_photo_or_album";
    private String key;
    private String[] sub_button;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(String[] sub_button) {
        this.sub_button = sub_button;
    }

    public PicPhotoOrAlbumButton(String name, String key) {
        super(name);
        this.key = key;
        this.sub_button = new String[]{};
    }
}
