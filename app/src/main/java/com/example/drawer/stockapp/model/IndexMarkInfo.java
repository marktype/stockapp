package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/7.
 * 指数信息
 */
public class IndexMarkInfo {


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
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
        /**
         * Id : 0
         * Name : 上证指数
         * Code : 000001.SH
         * Points : 3103.489990234375
         * VariabilityPoints : 12.777099609375
         * VariabilityRate : 0.4134029934689673
         * UpdateTime : 2016-09-07 10:28:45
         */

        private List<MarketDataBean> MarketData;

        public List<MarketDataBean> getMarketData() {
            return MarketData;
        }

        public void setMarketData(List<MarketDataBean> MarketData) {
            this.MarketData = MarketData;
        }

        public static class MarketDataBean {
            private String Id;
            private String Name;
            private String Code;
            private double Points;
            private double VariabilityPoints;
            private double VariabilityRate;
            private String UpdateTime;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public double getPoints() {
                return Points;
            }

            public void setPoints(double Points) {
                this.Points = Points;
            }

            public double getVariabilityPoints() {
                return VariabilityPoints;
            }

            public void setVariabilityPoints(double VariabilityPoints) {
                this.VariabilityPoints = VariabilityPoints;
            }

            public double getVariabilityRate() {
                return VariabilityRate;
            }

            public void setVariabilityRate(double VariabilityRate) {
                this.VariabilityRate = VariabilityRate;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }
        }
    }
}
