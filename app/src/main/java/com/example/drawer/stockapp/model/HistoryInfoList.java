package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/19.
 */
public class HistoryInfoList {

    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":1,"PageSize":10}
     * Data : [{"Id":278597,"Name":"手动调仓信号_2016-09-18 11:22:29","Time":"2016-09-18T11:22:29","PorfolioId":1761,"PorfolioName":"准备发行测试","Codes":[{"Code":"000799.SZ","Price":20.07,"Name":"酒鬼酒","Volume":100,"TradeTime":"2016-09-18T11:22:29","TradeType":1}]},{"Id":278480,"Name":"手动调仓信号_2016-09-08 21:09:51","Time":"2016-09-08T21:09:51","PorfolioId":1761,"PorfolioName":"准备发行测试","Codes":[{"Code":"000799.SZ","Price":16,"Name":"酒鬼酒","Volume":10000,"TradeTime":"2016-09-08T21:09:51","TradeType":0}]},{"Id":278447,"Name":"手动调仓信号_2016-09-07 15:34:10","Time":"2016-09-07T15:34:10","PorfolioId":1761,"PorfolioName":"准备发行测试","Codes":[{"Code":"000861.SZ","Price":5.44,"Name":"海印股份","Volume":150000,"TradeTime":"2016-09-07T15:34:10","TradeType":1}]},{"Id":278446,"Name":"手动调仓信号_2016-09-07 15:33:14","Time":"2016-09-07T15:33:14","PorfolioId":1761,"PorfolioName":"准备发行测试","Codes":[{"Code":"000861.SZ","Price":6,"Name":"海印股份","Volume":150000,"TradeTime":"2016-09-07T15:33:14","TradeType":0}]},{"Id":278445,"Name":"手动调仓信号_2016-09-07 15:32:49","Time":"2016-09-07T15:32:49","PorfolioId":1761,"PorfolioName":"准备发行测试","Codes":[{"Code":"000799.SZ","Price":21.28,"Name":"酒鬼酒","Volume":1000,"TradeTime":"2016-09-07T15:32:49","TradeType":1}]},{"Id":278444,"Name":"手动调仓信号_2016-09-07 15:30:59","Time":"2016-09-07T15:30:59","PorfolioId":1761,"PorfolioName":"准备发行测试","Codes":[{"Code":"000799.SZ","Price":23,"Name":"酒鬼酒","Volume":1000,"TradeTime":"2016-09-07T15:30:59","TradeType":0}]}]
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
        /**
         * PageIndex : 0
         * PageCount : 1
         * PageSize : 10
         */

        private PageInfoBean PageInfo;
        /**
         * Id : 278597
         * Name : 手动调仓信号_2016-09-18 11:22:29
         * Time : 2016-09-18T11:22:29
         * PorfolioId : 1761
         * PorfolioName : 准备发行测试
         * Codes : [{"Code":"000799.SZ","Price":20.07,"Name":"酒鬼酒","Volume":100,"TradeTime":"2016-09-18T11:22:29","TradeType":1}]
         */

        private List<DataBean> Data;

        public PageInfoBean getPageInfo() {
            return PageInfo;
        }

        public void setPageInfo(PageInfoBean PageInfo) {
            this.PageInfo = PageInfo;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class PageInfoBean {
            private int PageIndex;
            private int PageCount;
            private int PageSize;

            public int getPageIndex() {
                return PageIndex;
            }

            public void setPageIndex(int PageIndex) {
                this.PageIndex = PageIndex;
            }

            public int getPageCount() {
                return PageCount;
            }

            public void setPageCount(int PageCount) {
                this.PageCount = PageCount;
            }

            public int getPageSize() {
                return PageSize;
            }

            public void setPageSize(int PageSize) {
                this.PageSize = PageSize;
            }
        }

        public static class DataBean {
            private int Id;
            private String Name;
            private String Time;
            private int PorfolioId;
            private String PorfolioName;
            /**
             * Code : 000799.SZ
             * Price : 20.07
             * Name : 酒鬼酒
             * Volume : 100
             * TradeTime : 2016-09-18T11:22:29
             * TradeType : 1
             */

            private List<CodesBean> Codes;

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

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            public int getPorfolioId() {
                return PorfolioId;
            }

            public void setPorfolioId(int PorfolioId) {
                this.PorfolioId = PorfolioId;
            }

            public String getPorfolioName() {
                return PorfolioName;
            }

            public void setPorfolioName(String PorfolioName) {
                this.PorfolioName = PorfolioName;
            }

            public List<CodesBean> getCodes() {
                return Codes;
            }

            public void setCodes(List<CodesBean> Codes) {
                this.Codes = Codes;
            }

            public static class CodesBean {
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
}
