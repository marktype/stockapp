package com.example.drawer.stockapp.model;

import java.util.ArrayList;

/**
 * 新闻资讯信息，策略组合中的退款保障信息
 */
public class NewsInfo {
    private int type;    //布局类型
    //类型为1时（1张图片）
    private String Image;
    private String title;
    private String content;
    private String time;
    private String peopleNum;
    //类型为2时（3张图片）
    private ArrayList<String> imgaes;
    private String threeTitle;
    private String threeContent;
    private int threePeopleNum;
    private String threeTime;
    //类型为3时（动态列表）
    private String dynImage ;    //头像
    private String dynName;     //名字
    private String dynContent;    //内容
    private ArrayList<String> dynContentImage;   //内容图片
    private String dynTime;   //时间
    private int dynZhuanFaNum;    //转发数
    private int dynCommentNum;    //评论数
    private int dynGoodNum;     //点赞数


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getImgaes() {
        return imgaes;
    }

    public void setImgaes(ArrayList<String> imgaes) {
        this.imgaes = imgaes;
    }

    public String getThreeTitle() {
        return threeTitle;
    }

    public void setThreeTitle(String threeTitle) {
        this.threeTitle = threeTitle;
    }

    public int getThreePeopleNum() {
        return threePeopleNum;
    }

    public void setThreePeopleNum(int threePeopleNum) {
        this.threePeopleNum = threePeopleNum;
    }

    public String getThreeContent() {
        return threeContent;
    }

    public void setThreeContent(String threeContent) {
        this.threeContent = threeContent;
    }

    public String getThreeTime() {
        return threeTime;
    }

    public void setThreeTime(String threeTime) {
        this.threeTime = threeTime;
    }

    public String getDynImage() {
        return dynImage;
    }

    public void setDynImage(String dynImage) {
        this.dynImage = dynImage;
    }

    public String getDynName() {
        return dynName;
    }

    public void setDynName(String dynName) {
        this.dynName = dynName;
    }

    public String getDynContent() {
        return dynContent;
    }

    public void setDynContent(String dynContent) {
        this.dynContent = dynContent;
    }

    public ArrayList<String> getDynContentImage() {
        return dynContentImage;
    }

    public void setDynContentImage(ArrayList<String> dynContentImage) {
        this.dynContentImage = dynContentImage;
    }

    public String getDynTime() {
        return dynTime;
    }

    public void setDynTime(String dynTime) {
        this.dynTime = dynTime;
    }

    public int getDynZhuanFaNum() {
        return dynZhuanFaNum;
    }

    public void setDynZhuanFaNum(int dynZhuanFaNum) {
        this.dynZhuanFaNum = dynZhuanFaNum;
    }

    public int getDynCommentNum() {
        return dynCommentNum;
    }

    public void setDynCommentNum(int dynCommentNum) {
        this.dynCommentNum = dynCommentNum;
    }

    public int getDynGoodNum() {
        return dynGoodNum;
    }

    public void setDynGoodNum(int dynGoodNum) {
        this.dynGoodNum = dynGoodNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
