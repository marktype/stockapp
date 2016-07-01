package com.example.drawer.stockapp.model;

/**
 * Created by 欢大哥 on 2016/6/15.
 */
public class TrendsInfo {
    private String image ;    //头像
    private String name;     //名字
    private String content;    //内容
    private String contentImage;   //内容图片
    private String time;   //时间

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
