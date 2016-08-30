package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/15.
 */
public class StarDetailInfo {


    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * Id : 1606
     * PorfolioInfo : {"Id":"1606","UserName":null,"NickName":null,"Title":"string","UserImgUrl":null,"TotleReturns":-0.05499999999999978,"ImgData":[{"Date":"2016-08-25T00:00:00","CumulativeReturn":0.2699999999999995},{"Date":"2016-08-26T00:00:00","CumulativeReturn":-0.05499999999999978}],"RecuitmentStartTime":"","RecuitmentEndTime":"","StarInvestment":0,"MostFollow":0,"Desc":"150***0882的组合","MaxDay":0,"StopLoss":0,"ShareRatio":0,"RunStartDay":null,"RunEndDay":null,"RunTargetEndDay":null,"TargetReturns":0,"Recruitment":0,"WinRatio":50,"MonthlyAverage":-0.36608662688102367,"Holding":0,"Position":0,"AveragePosition":0,"AverageTrading":0,"Favorites":3,"CreateTime":null,"IsShare":false,"Type":null,"Return":0,"NetValue":99945,"Cash":88495.6392,"PorfolioType":0,"RecruitType":0,"PorfolioChooseType":0,"PorfolioAmount":0}
     * PorfolioDetail : {"LimtAmount":0,"StartAmount":0,"PorfolioType":0,"Investment":0,"RunEndState":0,"IsStart":false,"IsEndInvestment":false,"MaxReturn":0.2699999999999995,"MinReturn":-0.05499999999999978}
     * TransferPositions : {"Id":null,"LastTime":"2016-08-25 04:18:58","TransferPositionsInfo":[{"Id":null,"Code":"000002.SZ","Name":"万科A","BuyType":0,"Befor":0,"After":500,"Price":23}]}
     * StockRatio : [{"Name":"房地产","Ratio":1}]
     * StarInfo : {"Id":null,"Name":"15018400882","NickName":null,"ImgUrl":null,"Title":null,"MonthlyAverage":0,"PorfolioSucc":0,"StockPick":0}
     * Achievemnt : {"Id":null,"LastTime":null,"Profitability":0,"AntiRiskAbility":100,"Stability":100,"Dispersion":100,"Replication":100,"General":0}
     * HoldingDetail : [{"Code":"000002.SZ","Name":"万科A","Price":24.43,"AvgPrice":23,"Volumn":500,"ProfitRate":0,"Profit":0,"CumulativeReturnRate":6.22,"CumulativeReturn":714.9999999999999}]
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
        /**
         * Id : 1606
         * UserName : null
         * NickName : null
         * Title : string
         * UserImgUrl : null
         * TotleReturns : -0.05499999999999978
         * ImgData : [{"Date":"2016-08-25T00:00:00","CumulativeReturn":0.2699999999999995},{"Date":"2016-08-26T00:00:00","CumulativeReturn":-0.05499999999999978}]
         * RecuitmentStartTime :
         * RecuitmentEndTime :
         * StarInvestment : 0.0
         * MostFollow : 0.0
         * Desc : 150***0882的组合
         * MaxDay : 0
         * StopLoss : 0.0
         * ShareRatio : 0.0
         * RunStartDay : null
         * RunEndDay : null
         * RunTargetEndDay : null
         * TargetReturns : 0.0
         * Recruitment : 0.0
         * WinRatio : 50.0
         * MonthlyAverage : -0.36608662688102367
         * Holding : 0
         * Position : 0.0
         * AveragePosition : 0
         * AverageTrading : 0.0
         * Favorites : 3
         * CreateTime : null
         * IsShare : false
         * Type : null
         * Return : 0.0
         * NetValue : 99945.0
         * Cash : 88495.6392
         * PorfolioType : 0
         * RecruitType : 0
         * PorfolioChooseType : 0
         * PorfolioAmount : 0.0
         */

        private PorfolioInfoBean PorfolioInfo;
        /**
         * LimtAmount : 0.0
         * StartAmount : 0.0
         * PorfolioType : 0
         * Investment : 0
         * RunEndState : 0
         * IsStart : false
         * IsEndInvestment : false
         * MaxReturn : 0.2699999999999995
         * MinReturn : -0.05499999999999978
         */

        private PorfolioDetailBean PorfolioDetail;
        /**
         * Id : null
         * LastTime : 2016-08-25 04:18:58
         * TransferPositionsInfo : [{"Id":null,"Code":"000002.SZ","Name":"万科A","BuyType":0,"Befor":0,"After":500,"Price":23}]
         */

        private TransferPositionsBean TransferPositions;
        /**
         * Id : null
         * Name : 15018400882
         * NickName : null
         * ImgUrl : null
         * Title : null
         * MonthlyAverage : 0.0
         * PorfolioSucc : 0.0
         * StockPick : 0.0
         */

        private StarInfoBean StarInfo;
        /**
         * Id : null
         * LastTime : null
         * Profitability : 0.0
         * AntiRiskAbility : 100.0
         * Stability : 100.0
         * Dispersion : 100.0
         * Replication : 100.0
         * General : 0.0
         */

        private AchievemntBean Achievemnt;
        /**
         * Name : 房地产
         * Ratio : 1.0
         */

        private List<StockRatioBean> StockRatio;
        /**
         * Code : 000002.SZ
         * Name : 万科A
         * Price : 24.43
         * AvgPrice : 23.0
         * Volumn : 500.0
         * ProfitRate : 0.0
         * Profit : 0.0
         * CumulativeReturnRate : 6.22
         * CumulativeReturn : 714.9999999999999
         */

        private List<HoldingDetailBean> HoldingDetail;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public PorfolioInfoBean getPorfolioInfo() {
            return PorfolioInfo;
        }

        public void setPorfolioInfo(PorfolioInfoBean PorfolioInfo) {
            this.PorfolioInfo = PorfolioInfo;
        }

        public PorfolioDetailBean getPorfolioDetail() {
            return PorfolioDetail;
        }

        public void setPorfolioDetail(PorfolioDetailBean PorfolioDetail) {
            this.PorfolioDetail = PorfolioDetail;
        }

        public TransferPositionsBean getTransferPositions() {
            return TransferPositions;
        }

        public void setTransferPositions(TransferPositionsBean TransferPositions) {
            this.TransferPositions = TransferPositions;
        }

        public StarInfoBean getStarInfo() {
            return StarInfo;
        }

        public void setStarInfo(StarInfoBean StarInfo) {
            this.StarInfo = StarInfo;
        }

        public AchievemntBean getAchievemnt() {
            return Achievemnt;
        }

        public void setAchievemnt(AchievemntBean Achievemnt) {
            this.Achievemnt = Achievemnt;
        }

        public List<StockRatioBean> getStockRatio() {
            return StockRatio;
        }

        public void setStockRatio(List<StockRatioBean> StockRatio) {
            this.StockRatio = StockRatio;
        }

        public List<HoldingDetailBean> getHoldingDetail() {
            return HoldingDetail;
        }

        public void setHoldingDetail(List<HoldingDetailBean> HoldingDetail) {
            this.HoldingDetail = HoldingDetail;
        }

        public static class PorfolioInfoBean {
            private String Id;
            private Object UserName;
            private String NickName;
            private String Title;
            private String UserImgUrl;
            private double TotleReturns;
            private String RecuitmentStartTime;
            private String RecuitmentEndTime;
            private double StarInvestment;
            private double MostFollow;
            private String Desc;
            private int MaxDay;
            private double StopLoss;
            private double ShareRatio;
            private Object RunStartDay;
            private Object RunEndDay;
            private Object RunTargetEndDay;
            private double TargetReturns;
            private double Recruitment;
            private double WinRatio;
            private double MonthlyAverage;
            private int Holding;
            private double Position;
            private int AveragePosition;
            private double AverageTrading;
            private int Favorites;
            private Object CreateTime;
            private boolean IsShare;
            private Object Type;
            private double Return;
            private double NetValue;
            private double Cash;
            private int PorfolioType;
            private int RecruitType;
            private int PorfolioChooseType;
            private double PorfolioAmount;
            /**
             * Date : 2016-08-25T00:00:00
             * CumulativeReturn : 0.2699999999999995
             */

            private List<ImgDataBean> ImgData;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public Object getUserName() {
                return UserName;
            }

            public void setUserName(Object UserName) {
                this.UserName = UserName;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getUserImgUrl() {
                return UserImgUrl;
            }

            public void setUserImgUrl(String UserImgUrl) {
                this.UserImgUrl = UserImgUrl;
            }

            public double getTotleReturns() {
                return TotleReturns;
            }

            public void setTotleReturns(double TotleReturns) {
                this.TotleReturns = TotleReturns;
            }

            public String getRecuitmentStartTime() {
                return RecuitmentStartTime;
            }

            public void setRecuitmentStartTime(String RecuitmentStartTime) {
                this.RecuitmentStartTime = RecuitmentStartTime;
            }

            public String getRecuitmentEndTime() {
                return RecuitmentEndTime;
            }

            public void setRecuitmentEndTime(String RecuitmentEndTime) {
                this.RecuitmentEndTime = RecuitmentEndTime;
            }

            public double getStarInvestment() {
                return StarInvestment;
            }

            public void setStarInvestment(double StarInvestment) {
                this.StarInvestment = StarInvestment;
            }

            public double getMostFollow() {
                return MostFollow;
            }

            public void setMostFollow(double MostFollow) {
                this.MostFollow = MostFollow;
            }

            public String getDesc() {
                return Desc;
            }

            public void setDesc(String Desc) {
                this.Desc = Desc;
            }

            public int getMaxDay() {
                return MaxDay;
            }

            public void setMaxDay(int MaxDay) {
                this.MaxDay = MaxDay;
            }

            public double getStopLoss() {
                return StopLoss;
            }

            public void setStopLoss(double StopLoss) {
                this.StopLoss = StopLoss;
            }

            public double getShareRatio() {
                return ShareRatio;
            }

            public void setShareRatio(double ShareRatio) {
                this.ShareRatio = ShareRatio;
            }

            public Object getRunStartDay() {
                return RunStartDay;
            }

            public void setRunStartDay(Object RunStartDay) {
                this.RunStartDay = RunStartDay;
            }

            public Object getRunEndDay() {
                return RunEndDay;
            }

            public void setRunEndDay(Object RunEndDay) {
                this.RunEndDay = RunEndDay;
            }

            public Object getRunTargetEndDay() {
                return RunTargetEndDay;
            }

            public void setRunTargetEndDay(Object RunTargetEndDay) {
                this.RunTargetEndDay = RunTargetEndDay;
            }

            public double getTargetReturns() {
                return TargetReturns;
            }

            public void setTargetReturns(double TargetReturns) {
                this.TargetReturns = TargetReturns;
            }

            public double getRecruitment() {
                return Recruitment;
            }

            public void setRecruitment(double Recruitment) {
                this.Recruitment = Recruitment;
            }

            public double getWinRatio() {
                return WinRatio;
            }

            public void setWinRatio(double WinRatio) {
                this.WinRatio = WinRatio;
            }

            public double getMonthlyAverage() {
                return MonthlyAverage;
            }

            public void setMonthlyAverage(double MonthlyAverage) {
                this.MonthlyAverage = MonthlyAverage;
            }

            public int getHolding() {
                return Holding;
            }

            public void setHolding(int Holding) {
                this.Holding = Holding;
            }

            public double getPosition() {
                return Position;
            }

            public void setPosition(double Position) {
                this.Position = Position;
            }

            public int getAveragePosition() {
                return AveragePosition;
            }

            public void setAveragePosition(int AveragePosition) {
                this.AveragePosition = AveragePosition;
            }

            public double getAverageTrading() {
                return AverageTrading;
            }

            public void setAverageTrading(double AverageTrading) {
                this.AverageTrading = AverageTrading;
            }

            public int getFavorites() {
                return Favorites;
            }

            public void setFavorites(int Favorites) {
                this.Favorites = Favorites;
            }

            public Object getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(Object CreateTime) {
                this.CreateTime = CreateTime;
            }

            public boolean isIsShare() {
                return IsShare;
            }

            public void setIsShare(boolean IsShare) {
                this.IsShare = IsShare;
            }

            public Object getType() {
                return Type;
            }

            public void setType(Object Type) {
                this.Type = Type;
            }

            public double getReturn() {
                return Return;
            }

            public void setReturn(double Return) {
                this.Return = Return;
            }

            public double getNetValue() {
                return NetValue;
            }

            public void setNetValue(double NetValue) {
                this.NetValue = NetValue;
            }

            public double getCash() {
                return Cash;
            }

            public void setCash(double Cash) {
                this.Cash = Cash;
            }

            public int getPorfolioType() {
                return PorfolioType;
            }

            public void setPorfolioType(int PorfolioType) {
                this.PorfolioType = PorfolioType;
            }

            public int getRecruitType() {
                return RecruitType;
            }

            public void setRecruitType(int RecruitType) {
                this.RecruitType = RecruitType;
            }

            public int getPorfolioChooseType() {
                return PorfolioChooseType;
            }

            public void setPorfolioChooseType(int PorfolioChooseType) {
                this.PorfolioChooseType = PorfolioChooseType;
            }

            public double getPorfolioAmount() {
                return PorfolioAmount;
            }

            public void setPorfolioAmount(double PorfolioAmount) {
                this.PorfolioAmount = PorfolioAmount;
            }

            public List<ImgDataBean> getImgData() {
                return ImgData;
            }

            public void setImgData(List<ImgDataBean> ImgData) {
                this.ImgData = ImgData;
            }

            public static class ImgDataBean {
                private String Date;
                private double CumulativeReturn;

                public String getDate() {
                    return Date;
                }

                public void setDate(String Date) {
                    this.Date = Date;
                }

                public double getCumulativeReturn() {
                    return CumulativeReturn;
                }

                public void setCumulativeReturn(double CumulativeReturn) {
                    this.CumulativeReturn = CumulativeReturn;
                }
            }
        }

        public static class PorfolioDetailBean {
            private double LimtAmount;
            private double StartAmount;
            private int PorfolioType;
            private int Investment;
            private int RunEndState;
            private boolean IsStart;
            private boolean IsEndInvestment;
            private double MaxReturn;
            private double MinReturn;

            public double getLimtAmount() {
                return LimtAmount;
            }

            public void setLimtAmount(double LimtAmount) {
                this.LimtAmount = LimtAmount;
            }

            public double getStartAmount() {
                return StartAmount;
            }

            public void setStartAmount(double StartAmount) {
                this.StartAmount = StartAmount;
            }

            public int getPorfolioType() {
                return PorfolioType;
            }

            public void setPorfolioType(int PorfolioType) {
                this.PorfolioType = PorfolioType;
            }

            public int getInvestment() {
                return Investment;
            }

            public void setInvestment(int Investment) {
                this.Investment = Investment;
            }

            public int getRunEndState() {
                return RunEndState;
            }

            public void setRunEndState(int RunEndState) {
                this.RunEndState = RunEndState;
            }

            public boolean isIsStart() {
                return IsStart;
            }

            public void setIsStart(boolean IsStart) {
                this.IsStart = IsStart;
            }

            public boolean isIsEndInvestment() {
                return IsEndInvestment;
            }

            public void setIsEndInvestment(boolean IsEndInvestment) {
                this.IsEndInvestment = IsEndInvestment;
            }

            public double getMaxReturn() {
                return MaxReturn;
            }

            public void setMaxReturn(double MaxReturn) {
                this.MaxReturn = MaxReturn;
            }

            public double getMinReturn() {
                return MinReturn;
            }

            public void setMinReturn(double MinReturn) {
                this.MinReturn = MinReturn;
            }
        }

        public static class TransferPositionsBean {
            private Object Id;
            private String LastTime;
            /**
             * Id : null
             * Code : 000002.SZ
             * Name : 万科A
             * BuyType : 0
             * Befor : 0.0
             * After : 500.0
             * Price : 23.0
             */

            private List<TransferPositionsInfoBean> TransferPositionsInfo;

            public Object getId() {
                return Id;
            }

            public void setId(Object Id) {
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
                private Object Id;
                private String Code;
                private String Name;
                private int BuyType;
                private double Befor;
                private double After;
                private double Price;

                public Object getId() {
                    return Id;
                }

                public void setId(Object Id) {
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

        public static class StarInfoBean {
            private Object Id;
            private String Name;
            private Object NickName;
            private Object ImgUrl;
            private Object Title;
            private double MonthlyAverage;
            private double PorfolioSucc;
            private double StockPick;

            public Object getId() {
                return Id;
            }

            public void setId(Object Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public Object getNickName() {
                return NickName;
            }

            public void setNickName(Object NickName) {
                this.NickName = NickName;
            }

            public Object getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(Object ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public Object getTitle() {
                return Title;
            }

            public void setTitle(Object Title) {
                this.Title = Title;
            }

            public double getMonthlyAverage() {
                return MonthlyAverage;
            }

            public void setMonthlyAverage(double MonthlyAverage) {
                this.MonthlyAverage = MonthlyAverage;
            }

            public double getPorfolioSucc() {
                return PorfolioSucc;
            }

            public void setPorfolioSucc(double PorfolioSucc) {
                this.PorfolioSucc = PorfolioSucc;
            }

            public double getStockPick() {
                return StockPick;
            }

            public void setStockPick(double StockPick) {
                this.StockPick = StockPick;
            }
        }

        public static class AchievemntBean {
            private Object Id;
            private Object LastTime;
            private double Profitability;
            private double AntiRiskAbility;
            private double Stability;
            private double Dispersion;
            private double Replication;
            private double General;

            public Object getId() {
                return Id;
            }

            public void setId(Object Id) {
                this.Id = Id;
            }

            public Object getLastTime() {
                return LastTime;
            }

            public void setLastTime(Object LastTime) {
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

        public static class StockRatioBean {
            private String Name;
            private double Ratio;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public double getRatio() {
                return Ratio;
            }

            public void setRatio(double Ratio) {
                this.Ratio = Ratio;
            }
        }

        public static class HoldingDetailBean {
            private String Code;
            private String Name;
            private double Price;
            private double AvgPrice;
            private double Volumn;
            private double ProfitRate;
            private double Profit;
            private double CumulativeReturnRate;
            private double CumulativeReturn;

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

            public double getAvgPrice() {
                return AvgPrice;
            }

            public void setAvgPrice(double AvgPrice) {
                this.AvgPrice = AvgPrice;
            }

            public double getVolumn() {
                return Volumn;
            }

            public void setVolumn(double Volumn) {
                this.Volumn = Volumn;
            }

            public double getProfitRate() {
                return ProfitRate;
            }

            public void setProfitRate(double ProfitRate) {
                this.ProfitRate = ProfitRate;
            }

            public double getProfit() {
                return Profit;
            }

            public void setProfit(double Profit) {
                this.Profit = Profit;
            }

            public double getCumulativeReturnRate() {
                return CumulativeReturnRate;
            }

            public void setCumulativeReturnRate(double CumulativeReturnRate) {
                this.CumulativeReturnRate = CumulativeReturnRate;
            }

            public double getCumulativeReturn() {
                return CumulativeReturn;
            }

            public void setCumulativeReturn(double CumulativeReturn) {
                this.CumulativeReturn = CumulativeReturn;
            }
        }
    }
}
