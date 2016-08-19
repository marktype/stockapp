package com.example.drawer.stockapp.model;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/15.
 * 关注列表、动态列表
 */
public class TrendsInfo {
    private String image ;    //头像
    private String name;     //名字
    private String content;    //内容
    private ArrayList<String> contentImage;   //内容图片
    private String time;   //时间
    private int zhuanFaNum;    //转发数
    private int commentNum;    //评论数
    private int goodNum;     //点赞数
    private String friendImage;    //好友头像
    private String friendName;     //好友名字
    private String friendContent;    //好友评论内容
    private Boolean isCollect;   //是否收藏

    public Boolean getCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public int getZhuanFaNum() {
        return zhuanFaNum;
    }

    public void setZhuanFaNum(int zhuanFaNum) {
        this.zhuanFaNum = zhuanFaNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getFriendImage() {
        return friendImage;
    }

    public void setFriendImage(String friendImage) {
        this.friendImage = friendImage;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendContent() {
        return friendContent;
    }

    public void setFriendContent(String friendContent) {
        this.friendContent = friendContent;
    }

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

    public ArrayList<String> getContentImage() {
        return contentImage;
    }

    public void setContentImage(ArrayList<String> contentImage) {
        this.contentImage = contentImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
