package com.example.drawer.stockapp.model;

/**
 * Created by 欢大哥 on 2016/8/28.
 * 跟投和持仓
 */
public class ChiCangInfo {
    private String stockName;   //股票名字
    private String stockNum;   //股票编号
    private String todayAdd;    //今日涨数    //序号
    private String nowPrice;    //现价        //跟头人
    private String bascPrice;   //成本价      //跟投金额
    private String cangwei;    //仓位        //跟投时间
    private String fuYing;     //浮盈
    private double todayAddDecNum;  //今日张跌数值
    private double FuYingNum;   //浮盈数值

    public double getFuYingNum() {
        return FuYingNum;
    }

    public void setFuYingNum(double fuYingNum) {
        FuYingNum = fuYingNum;
    }

    public double getTodayAddDecNum() {
        return todayAddDecNum;
    }

    public void setTodayAddDecNum(double todayAddDecNum) {
        this.todayAddDecNum = todayAddDecNum;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public String getTodayAdd() {
        return todayAdd;
    }

    public void setTodayAdd(String todayAdd) {
        this.todayAdd = todayAdd;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getBascPrice() {
        return bascPrice;
    }

    public void setBascPrice(String bascPrice) {
        this.bascPrice = bascPrice;
    }

    public String getCangwei() {
        return cangwei;
    }

    public void setCangwei(String cangwei) {
        this.cangwei = cangwei;
    }

    public String getFuYing() {
        return fuYing;
    }

    public void setFuYing(String fuYing) {
        this.fuYing = fuYing;
    }
}
