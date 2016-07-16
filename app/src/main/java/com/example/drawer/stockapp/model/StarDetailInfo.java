package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/15.
 */
public class StarDetailInfo {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * PorfolioInfo : {"Id":"0","UserName":"牛人1号","Title":"牛人1号超级组合","SecondTitle":"二级小标题","TotleReturns":102,"ImgUrl":"","WinRatio":95,"MonthlyAverage":60,"Holding":12,"Position":85,"AveragePosition":21,"AverageTrading":3,"Favorites":888,"CreateTime":"2017-01-01"}
     * StarInfo : {"Id":"12","Name":"牛人5号","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Title":"超级期待牛人5号","MonthlyAverage":35,"PorfolioSucc":100,"StockPick":79,"Desc":"未来市场震荡明显，只要震荡，就是赚的时候"}
     * StrategyDetail : {"Id":"0","Name":"牛人1号","Type":0,"ImgUrl":"http://www.supwin.com/media/1009/logo.png","RecuitmentStartTDay":"2016-07-07 00:00:00","RecuitmentEndTimeDay":"2016-07-12 23:59:59","StarInvestment":1000000,"MostFollow":2000000,"Desc":"高风险，高回报","MaxDay":90,"StopLoss":15,"ShareRatio":60,"RunStartDay":"2016-07-13","RunEndDay":"2016-10-12","TargetReturns":0,"Recruitment":92,"Rights":"享受服务<p>第一，....<\/p>"}
     * Advantage : {"MoreYuEBao":388,"MoreOther":120,"DayRatio":8,"MonthRatio":123,"NetWorth":321567.8}
     * Achievemnt : {"Id":"3","LastTime":"2016-07-07 12:00:00","Profitability":5,"AntiRiskAbility":5,"Stability":4.9,"Dispersion":5,"Replication":3,"General":4.9}
     * TransferPositions : {"Id":"1","LastTime":"2016-07-07 12:00:00","TransferPositionsInfo":[{"Id":"0","Code":"000001","Name":"上证指数","BuyType":0,"Befor":200,"After":230,"Price":2990},{"Id":"0","Code":"390001","Name":"深圳成指","BuyType":0,"Befor":30,"After":23,"Price":10610},{"Id":"0","Code":"000300","Name":"沪深300","BuyType":0,"Befor":12,"After":9,"Price":3191}]}
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
         * Id : 0
         * UserName : 牛人1号
         * Title : 牛人1号超级组合
         * SecondTitle : 二级小标题
         * TotleReturns : 102.0
         * ImgUrl :
         * WinRatio : 95.0
         * MonthlyAverage : 60.0
         * Holding : 12
         * Position : 85.0
         * AveragePosition : 21
         * AverageTrading : 3.0
         * Favorites : 888
         * CreateTime : 2017-01-01
         */

        private PorfolioInfoBean PorfolioInfo;
        /**
         * Id : 12
         * Name : 牛人5号
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Title : 超级期待牛人5号
         * MonthlyAverage : 35.0
         * PorfolioSucc : 100.0
         * StockPick : 79.0
         * Desc : 未来市场震荡明显，只要震荡，就是赚的时候
         */

        private StarInfoBean StarInfo;
        /**
         * MoreYuEBao : 388.0
         * MoreOther : 120.0
         * DayRatio : 8.0
         * MonthRatio : 123.0
         * NetWorth : 321567.8
         */

        private AdvantageBean Advantage;
        /**
         * Id : 3
         * LastTime : 2016-07-07 12:00:00
         * Profitability : 5.0
         * AntiRiskAbility : 5.0
         * Stability : 4.9
         * Dispersion : 5.0
         * Replication : 3.0
         * General : 4.9
         */

        private AchievemntBean Achievemnt;
        /**
         * Id : 1
         * LastTime : 2016-07-07 12:00:00
         * TransferPositionsInfo : [{"Id":"0","Code":"000001","Name":"上证指数","BuyType":0,"Befor":200,"After":230,"Price":2990},{"Id":"0","Code":"390001","Name":"深圳成指","BuyType":0,"Befor":30,"After":23,"Price":10610},{"Id":"0","Code":"000300","Name":"沪深300","BuyType":0,"Befor":12,"After":9,"Price":3191}]
         */

        private TransferPositionsBean TransferPositions;

        public PorfolioInfoBean getPorfolioInfo() {
            return PorfolioInfo;
        }

        public void setPorfolioInfo(PorfolioInfoBean PorfolioInfo) {
            this.PorfolioInfo = PorfolioInfo;
        }

        public StarInfoBean getStarInfo() {
            return StarInfo;
        }

        public void setStarInfo(StarInfoBean StarInfo) {
            this.StarInfo = StarInfo;
        }

        public AdvantageBean getAdvantage() {
            return Advantage;
        }

        public void setAdvantage(AdvantageBean Advantage) {
            this.Advantage = Advantage;
        }

        public AchievemntBean getAchievemnt() {
            return Achievemnt;
        }

        public void setAchievemnt(AchievemntBean Achievemnt) {
            this.Achievemnt = Achievemnt;
        }

        public TransferPositionsBean getTransferPositions() {
            return TransferPositions;
        }

        public void setTransferPositions(TransferPositionsBean TransferPositions) {
            this.TransferPositions = TransferPositions;
        }

        public static class PorfolioInfoBean {
            private String Id;
            private String UserName;
            private String Title;
            private String SecondTitle;
            private double TotleReturns;
            private String ImgUrl;
            private int Favorites;
            private String CreateTime;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getSecondTitle() {
                return SecondTitle;
            }

            public void setSecondTitle(String SecondTitle) {
                this.SecondTitle = SecondTitle;
            }

            public double getTotleReturns() {
                return TotleReturns;
            }

            public void setTotleReturns(double TotleReturns) {
                this.TotleReturns = TotleReturns;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public int getFavorites() {
                return Favorites;
            }

            public void setFavorites(int Favorites) {
                this.Favorites = Favorites;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }
        }

        public static class StarInfoBean {
            private String Id;
            private String Name;
            private String ImgUrl;
            private String Desc;

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

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public String getDesc() {
                return Desc;
            }

            public void setDesc(String Desc) {
                this.Desc = Desc;
            }
        }

        public static class AdvantageBean {
            private double MoreYuEBao;
            private double MoreOther;
            private double DayRatio;
            private double MonthRatio;
            private double NetWorth;

            public double getMoreYuEBao() {
                return MoreYuEBao;
            }

            public void setMoreYuEBao(double MoreYuEBao) {
                this.MoreYuEBao = MoreYuEBao;
            }

            public double getMoreOther() {
                return MoreOther;
            }

            public void setMoreOther(double MoreOther) {
                this.MoreOther = MoreOther;
            }

            public double getDayRatio() {
                return DayRatio;
            }

            public void setDayRatio(double DayRatio) {
                this.DayRatio = DayRatio;
            }

            public double getMonthRatio() {
                return MonthRatio;
            }

            public void setMonthRatio(double MonthRatio) {
                this.MonthRatio = MonthRatio;
            }

            public double getNetWorth() {
                return NetWorth;
            }

            public void setNetWorth(double NetWorth) {
                this.NetWorth = NetWorth;
            }
        }

        public static class AchievemntBean {
            private String Id;
            private String LastTime;
            private double Profitability;
            private double AntiRiskAbility;
            private double Stability;
            private double Dispersion;
            private double Replication;
            private double General;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getLastTime() {
                return LastTime;
            }

            public void setLastTime(String LastTime) {
                this.LastTime = LastTime;
            }

            public double getProfitability() {
                return Profitability;
            }

            public void setProfitability(double Profitability) {
                this.Profitability = Profitability;
            }

            public double getAntiRiskAbility() {
                return AntiRiskAbility;
            }

            public void setAntiRiskAbility(double AntiRiskAbility) {
                this.AntiRiskAbility = AntiRiskAbility;
            }

            public double getStability() {
                return Stability;
            }

            public void setStability(double Stability) {
                this.Stability = Stability;
            }

            public double getDispersion() {
                return Dispersion;
            }

            public void setDispersion(double Dispersion) {
                this.Dispersion = Dispersion;
            }

            public double getReplication() {
                return Replication;
            }

            public void setReplication(double Replication) {
                this.Replication = Replication;
            }

            public double getGeneral() {
                return General;
            }

            public void setGeneral(double General) {
                this.General = General;
            }
        }

        public static class TransferPositionsBean {
            private String Id;
            private String LastTime;
            /**
             * Id : 0
             * Code : 000001
             * Name : 上证指数
             * BuyType : 0
             * Befor : 200.0
             * After : 230.0
             * Price : 2990.0
             */

            private List<TransferPositionsInfoBean> TransferPositionsInfo;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getLastTime() {
                return LastTime;
            }

            public void setLastTime(String LastTime) {
                this.LastTime = LastTime;
            }

            public List<TransferPositionsInfoBean> getTransferPositionsInfo() {
                return TransferPositionsInfo;
            }

            public void setTransferPositionsInfo(List<TransferPositionsInfoBean> TransferPositionsInfo) {
                this.TransferPositionsInfo = TransferPositionsInfo;
            }

            public static class TransferPositionsInfoBean {
                private String Id;
                private String Code;
                private String Name;
                private int BuyType;
                private double Befor;
                private double After;
                private double Price;

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }

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

                public int getBuyType() {
                    return BuyType;
                }

                public void setBuyType(int BuyType) {
                    this.BuyType = BuyType;
                }

                public double getBefor() {
                    return Befor;
                }

                public void setBefor(double Befor) {
                    this.Befor = Befor;
                }

                public double getAfter() {
                    return After;
                }

                public void setAfter(double After) {
                    this.After = After;
                }

                public double getPrice() {
                    return Price;
                }

                public void setPrice(double Price) {
                    this.Price = Price;
                }
            }
        }
    }
}
