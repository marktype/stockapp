package com.example.drawer.stockapp.model;

/**
 * Created by 欢大哥 on 2016/8/28.
 *
 */
public class TiaoCangInfo {
    private Boolean isBuyCome;   //买入还是卖出 true(买入)
    private String stockName;   //股票名字
    private String stockNum;   //股票编号
    private int tradeNumStart;    //交易量前
    private int tradeNumEnd;    //交易量后
    private String tradePrice;   //成交价
    private String zuheName;    //组合名字
    private String time;    //时间

    public String getZuheName() {
        return zuheName;
    }

    public void setZuheName(String zuheName) {
        this.zuheName = zuheName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getBuyCome() {
        return isBuyCome;
    }

    public void setBuyCome(Boolean buyCome) {
        isBuyCome = buyCome;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getTradeNumStart() {
        return tradeNumStart;
    }

    public void setTradeNumStart(int tradeNumStart) {
        this.tradeNumStart = tradeNumStart;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public int getTradeNumEnd() {
        return tradeNumEnd;
    }

    public void setTradeNumEnd(int tradeNumEnd) {
        this.tradeNumEnd = tradeNumEnd;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }
}
