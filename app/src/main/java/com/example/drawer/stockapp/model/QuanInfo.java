package com.example.drawer.stockapp.model;

/**
 * Created by 欢大哥 on 2016/8/25.
 */
public class QuanInfo {
    private String money;   //使用钱数
    private String canUsed;   //使用权限
    private String whoUsed;   //谁能使用
    private String timeUsed;   //使用期限
    private Boolean isTimeOver;   //是否过期

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCanUsed() {
        return canUsed;
    }

    public void setCanUsed(String canUsed) {
        this.canUsed = canUsed;
    }

    public String getWhoUsed() {
        return whoUsed;
    }

    public void setWhoUsed(String whoUsed) {
        this.whoUsed = whoUsed;
    }

    public String getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(String timeUsed) {
        this.timeUsed = timeUsed;
    }

    public Boolean getTimeOver() {
        return isTimeOver;
    }

    public void setTimeOver(Boolean timeOver) {
        isTimeOver = timeOver;
    }
}
