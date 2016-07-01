package com.example.drawer.stockapp.model;

/**
 * 策略组合信息
 */
public class CeLueInfo {
    private String celuePersent;    //策略百分比
    private String title;     //题目
    private String runTime;    //运行时间
    private String jingZhiNum;    //目标收益
    private String maxNum;    //最长期限
    private String minGengTou;    //最小跟投
    private String rateNum;    //分成比例
    private String headImage;    //头像
    private String name;      //名字
    private String otherInfo;    //内容
    private int type;   //类型

    public String getMinGengTou() {
        return minGengTou;
    }

    public void setMinGengTou(String minGengTou) {
        this.minGengTou = minGengTou;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCeluePersent() {
        return celuePersent;
    }

    public void setCeluePersent(String celuePersent) {
        this.celuePersent = celuePersent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String  getJingZhiNum() {
        return jingZhiNum;
    }

    public void setJingZhiNum(String jingZhiNum) {
        this.jingZhiNum = jingZhiNum;
    }

    public String getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }

    public String getRateNum() {
        return rateNum;
    }

    public void setRateNum(String rateNum) {
        this.rateNum = rateNum;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
