package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/14.
 * 量化策略详情
 */
public class StargDetial {


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * Id : 1605
     * PorfolioInfo : {"Id":"1605","UserName":null,"NickName":"猫猫狗狗都有人#","Title":"大牛牛1号","UserImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906113855601.jpg","TotleReturns":53.055000000000106,"ImgData":[{"Date":"2016-08-25T00:00:00","CumulativeReturn":45.031999999999925},{"Date":"2016-08-26T00:00:00","CumulativeReturn":-19.951999999999927},{"Date":"2016-08-29T00:00:00","CumulativeReturn":-17.95299999999997},{"Date":"2016-08-30T00:00:00","CumulativeReturn":-42.94799999999997},{"Date":"2016-08-31T00:00:00","CumulativeReturn":184.044},{"Date":"2016-09-01T00:00:00","CumulativeReturn":35.02700000000015},{"Date":"2016-09-02T00:00:00","CumulativeReturn":68.03299999999996},{"Date":"2016-09-05T00:00:00","CumulativeReturn":35.02300000000014},{"Date":"2016-09-06T00:00:00","CumulativeReturn":23.037000000000035},{"Date":"2016-09-07T00:00:00","CumulativeReturn":18.042999999999964},{"Date":"2016-09-08T00:00:00","CumulativeReturn":53.055000000000106}],"BenchmarkImgData":[{"Date":"2016-08-25T00:00:00","CumulativeReturn":0},{"Date":"2016-08-26T00:00:00","CumulativeReturn":-0.06},{"Date":"2016-08-29T00:00:00","CumulativeReturn":-0.04},{"Date":"2016-08-30T00:00:00","CumulativeReturn":0.09},{"Date":"2016-08-31T00:00:00","CumulativeReturn":0.57},{"Date":"2016-09-01T00:00:00","CumulativeReturn":-0.22},{"Date":"2016-09-02T00:00:00","CumulativeReturn":0.16},{"Date":"2016-09-05T00:00:00","CumulativeReturn":0.32},{"Date":"2016-09-06T00:00:00","CumulativeReturn":1.02},{"Date":"2016-09-07T00:00:00","CumulativeReturn":0.96},{"Date":"2016-09-08T00:00:00","CumulativeReturn":0.92}],"RecuitmentStartTime":"","RecuitmentEndTime":"","StarInvestment":0,"MostFollow":0,"Desc":"大牛牛无敌！！##","MaxDay":0,"StopLoss":0,"ShareRatio":0,"RunStartDay":null,"RunEndDay":null,"RunTargetEndDay":null,"TargetReturns":20,"Recruitment":0,"WinRatio":58.33,"MonthlyAverage":4.03,"Holding":2,"Position":2243.587127400815,"AveragePosition":0,"AverageTrading":0,"Favorites":6,"CreateTime":"2016-08-25 12:26:20","IsShare":false,"Type":null,"Return":-49.02,"NetValue":104032.99999999991,"Cash":-2211258.0968544004,"PorfolioType":0,"RecruitType":0,"PorfolioChooseType":0,"PorfolioAmount":0,"RunEndState":null}
     * PorfolioDetail : {"LimtAmount":0,"StartAmount":0,"PorfolioType":0,"Investment":0,"IsStart":false,"IsEndInvestment":false,"MaxReturn":184.044,"MinReturn":-42.94799999999997}
     * TransferPositions : {"Id":null,"LastTime":"2016-08-25 09:41:19","TransferPositionsInfo":[{"Id":null,"Code":"000002.SZ","Name":"万科A","BuyType":0,"Befor":0,"After":100000,"Price":23.09}]}
     * StockRatio : [{"Name":"医药","Ratio":5.981694801985126E-4},{"Name":"房地产","Ratio":0.9994018305198015}]
     * StarInfo : {"Id":null,"Name":null,"NickName":"猫猫狗狗都有人#","ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906113855601.jpg","Title":null,"MonthlyAverage":0,"PorfolioSucc":0,"StockPick":0}
     * Achievemnt : {"Id":null,"LastTime":null,"Profitability":100,"AntiRiskAbility":90,"Stability":0,"Dispersion":0,"Replication":100,"General":0}
     * HoldingDetail : [{"Code":"000919.SZ","Name":"金陵药业","Price":14.15,"AvgPrice":13.82,"Volumn":100,"ProfitRate":-1.53,"Profit":-21.999999999999886,"CumulativeReturnRate":2.39,"CumulativeReturn":33.00000000000001},{"Code":"000002.SZ","Name":"万科A","Price":23.13,"AvgPrice":23.09,"Volumn":100000,"ProfitRate":-2.07,"Profit":-49000.0000000002,"CumulativeReturnRate":0.17,"CumulativeReturn":3999.9999999999145}]
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
         * Id : 1605
         * UserName : null
         * NickName : 猫猫狗狗都有人#
         * Title : 大牛牛1号
         * UserImgUrl : http://filewebpath.matrix.lab.supwin.com:8899/20160906113855601.jpg
         * TotleReturns : 53.055000000000106
         * ImgData : [{"Date":"2016-08-25T00:00:00","CumulativeReturn":45.031999999999925},{"Date":"2016-08-26T00:00:00","CumulativeReturn":-19.951999999999927},{"Date":"2016-08-29T00:00:00","CumulativeReturn":-17.95299999999997},{"Date":"2016-08-30T00:00:00","CumulativeReturn":-42.94799999999997},{"Date":"2016-08-31T00:00:00","CumulativeReturn":184.044},{"Date":"2016-09-01T00:00:00","CumulativeReturn":35.02700000000015},{"Date":"2016-09-02T00:00:00","CumulativeReturn":68.03299999999996},{"Date":"2016-09-05T00:00:00","CumulativeReturn":35.02300000000014},{"Date":"2016-09-06T00:00:00","CumulativeReturn":23.037000000000035},{"Date":"2016-09-07T00:00:00","CumulativeReturn":18.042999999999964},{"Date":"2016-09-08T00:00:00","CumulativeReturn":53.055000000000106}]
         * BenchmarkImgData : [{"Date":"2016-08-25T00:00:00","CumulativeReturn":0},{"Date":"2016-08-26T00:00:00","CumulativeReturn":-0.06},{"Date":"2016-08-29T00:00:00","CumulativeReturn":-0.04},{"Date":"2016-08-30T00:00:00","CumulativeReturn":0.09},{"Date":"2016-08-31T00:00:00","CumulativeReturn":0.57},{"Date":"2016-09-01T00:00:00","CumulativeReturn":-0.22},{"Date":"2016-09-02T00:00:00","CumulativeReturn":0.16},{"Date":"2016-09-05T00:00:00","CumulativeReturn":0.32},{"Date":"2016-09-06T00:00:00","CumulativeReturn":1.02},{"Date":"2016-09-07T00:00:00","CumulativeReturn":0.96},{"Date":"2016-09-08T00:00:00","CumulativeReturn":0.92}]
         * RecuitmentStartTime :
         * RecuitmentEndTime :
         * StarInvestment : 0
         * MostFollow : 0
         * Desc : 大牛牛无敌！！##
         * MaxDay : 0
         * StopLoss : 0
         * ShareRatio : 0
         * RunStartDay : null
         * RunEndDay : null
         * RunTargetEndDay : null
         * TargetReturns : 20
         * Recruitment : 0
         * WinRatio : 58.33
         * MonthlyAverage : 4.03
         * Holding : 2
         * Position : 2243.587127400815
         * AveragePosition : 0
         * AverageTrading : 0
         * Favorites : 6
         * CreateTime : 2016-08-25 12:26:20
         * IsShare : false
         * Type : null
         * Return : -49.02
         * NetValue : 104032.99999999991
         * Cash : -2211258.0968544004
         * PorfolioType : 0
         * RecruitType : 0
         * PorfolioChooseType : 0
         * PorfolioAmount : 0
         * RunEndState : null
         */

        private PorfolioInfoBean PorfolioInfo;
        /**
         * LimtAmount : 0
         * StartAmount : 0
         * PorfolioType : 0
         * Investment : 0
         * IsStart : false
         * IsEndInvestment : false
         * MaxReturn : 184.044
         * MinReturn : -42.94799999999997
         */

        private PorfolioDetailBean PorfolioDetail;
        /**
         * Id : null
         * LastTime : 2016-08-25 09:41:19
         * TransferPositionsInfo : [{"Id":null,"Code":"000002.SZ","Name":"万科A","BuyType":0,"Befor":0,"After":100000,"Price":23.09}]
         */

        private TransferPositionsBean TransferPositions;
        /**
         * Id : null
         * Name : null
         * NickName : 猫猫狗狗都有人#
         * ImgUrl : http://filewebpath.matrix.lab.supwin.com:8899/20160906113855601.jpg
         * Title : null
         * MonthlyAverage : 0
         * PorfolioSucc : 0
         * StockPick : 0
         */

        private StarInfoBean StarInfo;
        /**
         * Id : null
         * LastTime : null
         * Profitability : 100
         * AntiRiskAbility : 90
         * Stability : 0
         * Dispersion : 0
         * Replication : 100
         * General : 0
         */

        private AchievemntBean Achievemnt;
        /**
         * Name : 医药
         * Ratio : 5.981694801985126E-4
         */

        private List<StockRatioBean> StockRatio;
        /**
         * Code : 000919.SZ
         * Name : 金陵药业
         * Price : 14.15
         * AvgPrice : 13.82
         * Volumn : 100
         * ProfitRate : -1.53
         * Profit : -21.999999999999886
         * CumulativeReturnRate : 2.39
         * CumulativeReturn : 33.00000000000001
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
            private String RunStartDay;
            private String RunEndDay;
            private String RunTargetEndDay;
            private double TargetReturns;
            private double Recruitment;
            private double WinRatio;
            private double MonthlyAverage;
            private int Holding;
            private double Position;
            private int AveragePosition;
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
            private Object RunEndState;
            /**
             * Date : 2016-08-25T00:00:00
             * CumulativeReturn : 45.031999999999925
             */

            private List<ImgDataBean> ImgData;   //组合
            /**
             * Date : 2016-08-25T00:00:00
             * CumulativeReturn : 0
             */

            private List<BenchmarkImgDataBean> BenchmarkImgData;   //基准

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

            public String getRunStartDay() {
                return RunStartDay;
            }

            public void setRunStartDay(String RunStartDay) {
                this.RunStartDay = RunStartDay;
            }

            public Object getRunEndDay() {
                return RunEndDay;
            }

            public void setRunEndDay(String RunEndDay) {
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

            public Object getRunEndState() {
                return RunEndState;
            }

            public void setRunEndState(Object RunEndState) {
                this.RunEndState = RunEndState;
            }

            public List<ImgDataBean> getImgData() {
                return ImgData;
            }

            public void setImgData(List<ImgDataBean> ImgData) {
                this.ImgData = ImgData;
            }

            public List<BenchmarkImgDataBean> getBenchmarkImgData() {
                return BenchmarkImgData;
            }

            public void setBenchmarkImgData(List<BenchmarkImgDataBean> BenchmarkImgData) {
                this.BenchmarkImgData = BenchmarkImgData;
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

            public static class BenchmarkImgDataBean {
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
            private int RecruitType;
            private int Investment;
            private boolean IsStart;
            private boolean IsEndInvestment;
            private double MaxReturn;
            private double MinReturn;
            private double CompleteAlongAmount;

            public double getCompleteAlongAmount() {
                return CompleteAlongAmount;
            }

            public void setCompleteAlongAmount(double completeAlongAmount) {
                CompleteAlongAmount = completeAlongAmount;
            }

            public int getRecruitType() {
                return RecruitType;
            }

            public void setRecruitType(int recruitType) {
                RecruitType = recruitType;
            }

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
             * Befor : 0
             * After : 100000
             * Price : 23.09
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
                private int Befor;
                private int After;
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

                public int getBefor() {
                    return Befor;
                }

                public void setBefor(int Befor) {
                    this.Befor = Befor;
                }

                public int getAfter() {
                    return After;
                }

                public void setAfter(int After) {
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
            private int MonthlyAverage;
            private int PorfolioSucc;
            private int StockPick;

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

            public int getMonthlyAverage() {
                return MonthlyAverage;
            }

            public void setMonthlyAverage(int MonthlyAverage) {
                this.MonthlyAverage = MonthlyAverage;
            }

            public int getPorfolioSucc() {
                return PorfolioSucc;
            }

            public void setPorfolioSucc(int PorfolioSucc) {
                this.PorfolioSucc = PorfolioSucc;
            }

            public int getStockPick() {
                return StockPick;
            }

            public void setStockPick(int StockPick) {
                this.StockPick = StockPick;
            }
        }

        public static class AchievemntBean {
            private Object Id;
            private String LastTime;
            private int Profitability;
            private int AntiRiskAbility;
            private int Stability;
            private int Dispersion;
            private int Replication;
            private int General;

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

            public int getProfitability() {
                return Profitability;
            }

            public void setProfitability(int Profitability) {
                this.Profitability = Profitability;
            }

            public int getAntiRiskAbility() {
                return AntiRiskAbility;
            }

            public void setAntiRiskAbility(int AntiRiskAbility) {
                this.AntiRiskAbility = AntiRiskAbility;
            }

            public int getStability() {
                return Stability;
            }

            public void setStability(int Stability) {
                this.Stability = Stability;
            }

            public int getDispersion() {
                return Dispersion;
            }

            public void setDispersion(int Dispersion) {
                this.Dispersion = Dispersion;
            }

            public int getReplication() {
                return Replication;
            }

            public void setReplication(int Replication) {
                this.Replication = Replication;
            }

            public int getGeneral() {
                return General;
            }

            public void setGeneral(int General) {
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
            private int Volumn;
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

            public int getVolumn() {
                return Volumn;
            }

            public void setVolumn(int Volumn) {
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
