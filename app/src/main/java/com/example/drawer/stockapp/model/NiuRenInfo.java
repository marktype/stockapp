package com.example.drawer.stockapp.model;

/**
 * 牛人组合信息（我的组合信息共用）
 */
public class NiuRenInfo {
    private String niurenHead;    //牛人头像
    private String niurenName;   //名字
    private String niurenRoundImage;   //曲线图片
    private String stockType;    //股票类型  (我的，例如沪深)
    private String shouyiRate;    //总收益率  （我的 ，百分比）
    private String victorRate;    //胜率
    private String shouyiByMonth;   //月均收益
    private int stockNum;     //持股数
    private String cangweiRate;   //仓位
    private int dayNum;        //持股周期
    private String tradeTime;      //交易次数  （我的，关注人数）

    public String getNiurenHead() {
        return niurenHead;
    }

    public void setNiurenHead(String niurenHead) {
        this.niurenHead = niurenHead;
    }

    public String getNiurenName() {
        return niurenName;
    }

    public void setNiurenName(String niurenName) {
        this.niurenName = niurenName;
    }

    public String getNiurenRoundImage() {
        return niurenRoundImage;
    }

    public void setNiurenRoundImage(String niurenRoundImage) {
        this.niurenRoundImage = niurenRoundImage;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getShouyiRate() {
        return shouyiRate;
    }

    public void setShouyiRate(String shouyiRate) {
        this.shouyiRate = shouyiRate;
    }

    public String getVictorRate() {
        return victorRate;
    }

    public void setVictorRate(String victorRate) {
        this.victorRate = victorRate;
    }

    public String getShouyiByMonth() {
        return shouyiByMonth;
    }

    public void setShouyiByMonth(String shouyiByMonth) {
        this.shouyiByMonth = shouyiByMonth;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public String getCangweiRate() {
        return cangweiRate;
    }

    public void setCangweiRate(String cangweiRate) {
        this.cangweiRate = cangweiRate;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }
}


