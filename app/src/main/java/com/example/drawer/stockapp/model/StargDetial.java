package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/14.
 * 量化策略详情
 */
public class StargDetial {


    /**
     * Status : 0
     * Msg : null
     */

    private HeadBean Head;
    /**
     * Id : 1578
     * PorfolioInfo : {"Id":"1578","UserName":null,"NickName":null,"Title":"测试","UserImgUrl":null,"TotleReturns":70.33886337599998,"ImgData":[{"Date":"2016-08-26T00:00:00","CumulativeReturn":0.7600000000000016},{"Date":"2016-08-29T00:00:00","CumulativeReturn":0.6999999999999922},{"Date":"2016-08-30T00:00:00","CumulativeReturn":71.18886337599997},{"Date":"2016-08-31T00:00:00","CumulativeReturn":70.33886337599998}],"RecuitmentStartTime":"2016-08-22 09:26:00","RecuitmentEndTime":"2016-08-23 09:26:00","StarInvestment":1,"MostFollow":10,"Desc":"123","MaxDay":8,"StopLoss":10,"ShareRatio":10,"RunStartDay":"2016-08-23 09:27:00","RunEndDay":null,"RunTargetEndDay":"2016-08-31 09:27:00","TargetReturns":1,"Recruitment":0,"WinRatio":40,"MonthlyAverage":70.41,"Holding":0,"Position":0,"AveragePosition":0,"AverageTrading":0,"Favorites":0,"CreateTime":"2016-08-22 09:27:12","IsShare":false,"Type":null,"Return":0.07,"NetValue":17040.886337599997,"Cash":5570.160219199999,"PorfolioType":0,"RecruitType":0,"PorfolioChooseType":0,"PorfolioAmount":10}
     * PorfolioDetail : {"LimtAmount":10,"StartAmount":1,"PorfolioType":0,"Investment":0,"RunEndState":1,"IsStart":true,"IsEndInvestment":true,"MaxReturn":71.18886337599997,"MinReturn":0.6999999999999922}
     * TransferPositions : {"Id":null,"LastTime":"2016-08-30 06:27:48","TransferPositionsInfo":[{"Id":null,"Code":"000919.SZ","Name":"金陵药业","BuyType":1,"Befor":0,"After":100,"Price":14.29},{"Id":null,"Code":"600005.SH","Name":"武钢股份","BuyType":1,"Befor":0,"After":100,"Price":2.94}]}
     * StockRatio : [{"Name":"医药生物","Ratio":0.9340949033391915},{"Name":"金融服务","Ratio":0},{"Name":"交通运输","Ratio":0.06590509666080843}]
     * StarInfo : {"Id":null,"Name":null,"NickName":null,"ImgUrl":null,"Title":null,"MonthlyAverage":0,"PorfolioSucc":0,"StockPick":0}
     * Achievemnt : {"Id":null,"LastTime":null,"Profitability":100,"AntiRiskAbility":90,"Stability":100,"Dispersion":0,"Replication":30,"General":0}
     * HoldingDetail : [{"Code":"000919.SZ","Name":"金陵药业","Price":14.2,"AvgPrice":14.173333333333334,"Volumn":300,"ProfitRate":-0.42,"Profit":-18.00000000000015,"CumulativeReturnRate":0.19,"CumulativeReturn":7.999999999999652},{"Code":"600000.SH","Name":"浦发银行","Price":16.42,"AvgPrice":0,"Volumn":100,"ProfitRate":-0.36,"Profit":-5.999999999999872,"CumulativeReturnRate":0,"CumulativeReturn":1642.0000000000002},{"Code":"600004.SH","Name":"白云机场","Price":13.84,"AvgPrice":1,"Volumn":200,"ProfitRate":0.29,"Profit":7.9999999999998295,"CumulativeReturnRate":1284,"CumulativeReturn":2568},{"Code":"600009.SH","Name":"上海机场","Price":27.99,"AvgPrice":1,"Volumn":100,"ProfitRate":0.83,"Profit":22.999999999999687,"CumulativeReturnRate":2699,"CumulativeReturn":2699}]
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
        private Object Msg;

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public Object getMsg() {
            return Msg;
        }

        public void setMsg(Object Msg) {
            this.Msg = Msg;
        }
    }

    public static class ResultBean {
        private int Id;
        /**
         * Id : 1578
         * UserName : null
         * NickName : null
         * Title : 测试
         * UserImgUrl : null
         * TotleReturns : 70.33886337599998
         * ImgData : [{"Date":"2016-08-26T00:00:00","CumulativeReturn":0.7600000000000016},{"Date":"2016-08-29T00:00:00","CumulativeReturn":0.6999999999999922},{"Date":"2016-08-30T00:00:00","CumulativeReturn":71.18886337599997},{"Date":"2016-08-31T00:00:00","CumulativeReturn":70.33886337599998}]
         * RecuitmentStartTime : 2016-08-22 09:26:00
         * RecuitmentEndTime : 2016-08-23 09:26:00
         * StarInvestment : 1.0
         * MostFollow : 10.0
         * Desc : 123
         * MaxDay : 8
         * StopLoss : 10.0
         * ShareRatio : 10.0
         * RunStartDay : 2016-08-23 09:27:00
         * RunEndDay : null
         * RunTargetEndDay : 2016-08-31 09:27:00
         * TargetReturns : 1.0
         * Recruitment : 0.0
         * WinRatio : 40.0
         * MonthlyAverage : 70.41
         * Holding : 0
         * Position : 0.0
         * AveragePosition : 0
         * AverageTrading : 0.0
         * Favorites : 0
         * CreateTime : 2016-08-22 09:27:12
         * IsShare : false
         * Type : null
         * Return : 0.07
         * NetValue : 17040.886337599997
         * Cash : 5570.160219199999
         * PorfolioType : 0
         * RecruitType : 0
         * PorfolioChooseType : 0
         * PorfolioAmount : 10.0
         */

        private PorfolioInfoBean PorfolioInfo;
        /**
         * LimtAmount : 10.0
         * StartAmount : 1.0
         * PorfolioType : 0
         * Investment : 0
         * RunEndState : 1
         * IsStart : true
         * IsEndInvestment : true
         * MaxReturn : 71.18886337599997
         * MinReturn : 0.6999999999999922
         */

        private PorfolioDetailBean PorfolioDetail;
        /**
         * Id : null
         * LastTime : 2016-08-30 06:27:48
         * TransferPositionsInfo : [{"Id":null,"Code":"000919.SZ","Name":"金陵药业","BuyType":1,"Befor":0,"After":100,"Price":14.29},{"Id":null,"Code":"600005.SH","Name":"武钢股份","BuyType":1,"Befor":0,"After":100,"Price":2.94}]
         */

        private TransferPositionsBean TransferPositions;    //最新调仓
        /**
         * Id : null
         * Name : null
         * NickName : null
         * ImgUrl : null
         * Title : null
         * MonthlyAverage : 0.0
         * PorfolioSucc : 0.0
         * StockPick : 0.0
         */

        private StarInfoBean StarInfo;    //牛人信息
        /**
         * Id : null
         * LastTime : null
         * Profitability : 100.0
         * AntiRiskAbility : 90.0
         * Stability : 100.0
         * Dispersion : 0.0
         * Replication : 30.0
         * General : 0.0
         */

        private AchievemntBean Achievemnt;     //    雷达图
        /**
         * Name : 医药生物
         * Ratio : 0.9340949033391915
         */

        private List<StockRatioBean> StockRatio;     //饼图
        /**
         * Code : 000919.SZ
         * Name : 金陵药业
         * Price : 14.2
         * AvgPrice : 14.173333333333334
         * Volumn : 300.0
         * ProfitRate : -0.42
         * Profit : -18.00000000000015
         * CumulativeReturnRate : 0.19
         * CumulativeReturn : 7.999999999999652
         */

        private List<HoldingDetailBean> HoldingDetail;   //当前持仓（和持仓有关）

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
            private Object NickName;
            private String Title;
            private Object UserImgUrl;
            private double TotleReturns;
            private String RecuitmentStartTime;   //招募时间
            private String RecuitmentEndTime;
            private double StarInvestment;
            private double MostFollow;
            private String Desc;
            private int MaxDay;
            private double StopLoss;
            private double ShareRatio;   //分成比例
            private String RunStartDay;    //运行起时间
            private Object RunEndDay;
            private String RunTargetEndDay;   //运行终时间
            private double TargetReturns;    //目标收益
            private double Recruitment;     //招募进度
            private double WinRatio;
            private double MonthlyAverage;
            private int Holding;
            private double Position;
            private int AveragePosition;     //交易次数
            private double AverageTrading;
            private int Favorites;
            private String CreateTime;
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
             * Date : 2016-08-26T00:00:00
             * CumulativeReturn : 0.7600000000000016
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

            public Object getNickName() {
                return NickName;
            }

            public void setNickName(Object NickName) {
                this.NickName = NickName;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public Object getUserImgUrl() {
                return UserImgUrl;
            }

            public void setUserImgUrl(Object UserImgUrl) {
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

            public String getRunStartDay() {
                return RunStartDay;
            }

            public void setRunStartDay(String RunStartDay) {
                this.RunStartDay = RunStartDay;
            }

            public Object getRunEndDay() {
                return RunEndDay;
            }

            public void setRunEndDay(Object RunEndDay) {
                this.RunEndDay = RunEndDay;
            }

            public String getRunTargetEndDay() {
                return RunTargetEndDay;
            }

            public void setRunTargetEndDay(String RunTargetEndDay) {
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

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
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
             * Code : 000919.SZ
             * Name : 金陵药业
             * BuyType : 1
             * Befor : 0.0
             * After : 100.0
             * Price : 14.29
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
                private int BuyType;     //0：买，1：卖
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
            private String Id;
            private String Name;
            private String NickName;
            private String ImgUrl;
            private String Title;
            private double MonthlyAverage;
            private double PorfolioSucc;
            private double StockPick;

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

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
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
