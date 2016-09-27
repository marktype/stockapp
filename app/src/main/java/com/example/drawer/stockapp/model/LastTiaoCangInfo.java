package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/26.
 */
public class LastTiaoCangInfo {

    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * Code : 600202.SH
     * Name : 哈空调
     * Price : 11.51
     * TradeTime : 2016-09-23 16:26:08
     * TradeType : 1
     * Volume : 100
     */

    private List<ResultBean> Result;

    public HeadBean getHead() {
        return Head;
    }

    public void setHead(HeadBean Head) {
        this.Head = Head;
    }

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
    }

    public static class HeadBean {
        private int Status;
        private String Msg;

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }
    }

    public static class ResultBean {
        private String Code;
        private String Name;
        private double Price;
        private String TradeTime;
        private int TradeType;
        private int Volume;

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public String getTradeTime() {
            return TradeTime;
        }

        public void setTradeTime(String TradeTime) {
            this.TradeTime = TradeTime;
        }

        public int getTradeType() {
            return TradeType;
        }

        public void setTradeType(int TradeType) {
            this.TradeType = TradeType;
        }

        public int getVolume() {
            return Volume;
        }

        public void setVolume(int Volume) {
            this.Volume = Volume;
        }
    }
}
