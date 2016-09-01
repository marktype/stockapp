package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/1.
 */
public class FollowRecord {

    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * Id : 1611
     * Name : 大涨
     * PorfolioChooseType : 1
     * CodeList : [{"Code":"002002.SZ","Price":6.8,"Name":"鸿达兴业","Volume":2000,"TradeTime":"2016-08-29T02:11:32Z","TradeType":0},{"Code":"002023.SZ","Price":17.45,"Name":"海特高新","Volume":1000,"TradeTime":"2016-08-29T02:11:32Z","TradeType":0}]
     */

    private ResultBean Result;

    public HeadBean getHead() {
        return Head;
    }

    public void setHead(HeadBean Head) {
        this.Head = Head;
    }

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
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
        private int Id;
        private String Name;
        private int PorfolioChooseType;
        /**
         * Code : 002002.SZ
         * Price : 6.8
         * Name : 鸿达兴业
         * Volume : 2000
         * TradeTime : 2016-08-29T02:11:32Z
         * TradeType : 0
         */

        private List<CodeListBean> CodeList;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getPorfolioChooseType() {
            return PorfolioChooseType;
        }

        public void setPorfolioChooseType(int PorfolioChooseType) {
            this.PorfolioChooseType = PorfolioChooseType;
        }

        public List<CodeListBean> getCodeList() {
            return CodeList;
        }

        public void setCodeList(List<CodeListBean> CodeList) {
            this.CodeList = CodeList;
        }

        public static class CodeListBean {
            private String Code;
            private double Price;
            private String Name;
            private int Volume;
            private String TradeTime;
            private int TradeType;

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(double Price) {
                this.Price = Price;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getVolume() {
                return Volume;
            }

            public void setVolume(int Volume) {
                this.Volume = Volume;
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
        }
    }
}
