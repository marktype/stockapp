package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class NiuRenListInfo {


    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":4,"PageSize":10}
     * Strategies : [{"Id":"1606","UserName":"15018400882","NickName":null,"Title":"string","UserImgUrl":null,"TotleReturns":0,"ImgData":[{"Date":"2016-08-25T00:00:00","CumulativeReturn":0.0026999999999999954}],"RecuitmentStartTime":null,"RecuitmentEndTime":null,"StarInvestment":0,"MostFollow":0,"Desc":"150***0882的组合","MaxDay":0,"StopLoss":0,"ShareRatio":0,"RunStartDay":null,"RunEndDay":null,"TargetReturns":0,"Recruitment":0,"WinRatio":0,"MonthlyAverage":6.1114694376816825,"Holding":1,"Position":12.128807936311858,"AveragePosition":0,"AverageTrading":0,"Favorites":0,"CreateTime":"2016-08-25 06:35:23","IsShare":false,"Type":"沪深","Return":0,"NetValue":0,"Cash":88495.6392,"PorfolioType":0,"RecruitType":0,"PorfolioChooseType":3},{"Id":"1605","UserName":null,"NickName":"zoe","Title":"大牛牛1号","UserImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","TotleReturns":0,"ImgData":[{"Date":"2016-08-25T00:00:00","CumulativeReturn":0.4503199999999993}],"RecuitmentStartTime":null,"RecuitmentEndTime":null,"StarInvestment":0,"MostFollow":0,"Desc":"大牛牛无敌！！##","MaxDay":0,"StopLoss":0,"ShareRatio":0,"RunStartDay":null,"RunEndDay":null,"TargetReturns":20,"Recruitment":0,"WinRatio":0,"MonthlyAverage":356410.7529155501,"Holding":2,"Position":1048.871235481287,"AveragePosition":0,"AverageTrading":0,"Favorites":0,"CreateTime":"2016-08-25 04:26:20","IsShare":false,"Type":"沪深","Return":0,"NetValue":0,"Cash":-2211258.0968544004,"PorfolioType":0,"RecruitType":0,"PorfolioChooseType":3},{"Id":"1600","UserName":null,"NickName":"MaoZhuan","Title":"Uuu","UserImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160824154558048.jpg","TotleReturns":0,"ImgData":null,"RecuitmentStartTime":null,"RecuitmentEndTime":null,"StarInvestment":0,"MostFollow":0,"Desc":"Eeee","MaxDay":0,"StopLoss":0,"ShareRatio":0,"RunStartDay":null,"RunEndDay":null,"TargetReturns":0,"Recruitment":0,"WinRatio":0,"MonthlyAverage":0,"Holding":0,"Position":0,"AveragePosition":0,"AverageTrading":0,"Favorites":0,"CreateTime":"2016-08-24 10:14:04","IsShare":false,"Type":"沪深","Return":0,"NetValue":0,"Cash":0,"PorfolioType":0,"RecruitType":0,"PorfolioChooseType":3},{"Id":"1585","UserName":null,"NickName":"zoe","Title":"牛人组合11","UserImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","TotleReturns":0,"ImgData":null,"RecuitmentStartTime":null,"RecuitmentEndTime":null,"StarInvestment":0,"MostFollow":0,"Desc":"string","MaxDay":0,"StopLoss":0,"ShareRatio":0,"RunStartDay":null,"RunEndDay":null,"TargetReturns":0,"Recruitment":0,"WinRatio":0,"MonthlyAverage":0,"Holding":0,"Position":0,"AveragePosition":0,"AverageTrading":0,"Favorites":0,"CreateTime":"2016-08-23 06:34:06","IsShare":false,"Type":"沪深","Return":0,"NetValue":0,"Cash":0,"PorfolioType":0,"RecruitType":0,"PorfolioChooseType":3}]
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
         * Id : 1606
         * UserName : 15018400882
         * NickName : null
         * Title : string
         * UserImgUrl : null
         * TotleReturns : 0.0
         * ImgData : [{"Date":"2016-08-25T00:00:00","CumulativeReturn":0.0026999999999999954}]
         * RecuitmentStartTime : null
         * RecuitmentEndTime : null
         * StarInvestment : 0.0
         * MostFollow : 0.0
         * Desc : 150***0882的组合
         * MaxDay : 0
         * StopLoss : 0.0
         * ShareRatio : 0.0
         * RunStartDay : null
         * RunEndDay : null
         * TargetReturns : 0.0
         * Recruitment : 0.0
         * WinRatio : 0.0
         * MonthlyAverage : 6.1114694376816825
         * Holding : 1
         * Position : 12.128807936311858
         * AveragePosition : 0
         * AverageTrading : 0.0
         * Favorites : 0
         * CreateTime : 2016-08-25 06:35:23
         * IsShare : false
         * Type : 沪深
         * Return : 0.0
         * NetValue : 0.0
         * Cash : 88495.6392
         * PorfolioType : 0
         * RecruitType : 0
         * PorfolioChooseType : 3
         */

        private List<StrategiesBean> Strategies;

        public List<StrategiesBean> getStrategies() {
            return Strategies;
        }

        public void setStrategies(List<StrategiesBean> Strategies) {
            this.Strategies = Strategies;
        }

        public static class StrategiesBean {
            private String Id;
            private String UserName;
            private Object NickName;
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
            private String Type;
            private double Return;
            private double NetValue;
            private double Cash;
            private int PorfolioType;
            private int RecruitType;
            private int PorfolioChooseType;
            /**
             * Date : 2016-08-25T00:00:00
             * CumulativeReturn : 0.0026999999999999954
             */

            private List<ImgDataBean> ImgData;

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

            public String getRunEndDay() {
                return RunEndDay;
            }

            public void setRunEndDay(String RunEndDay) {
                this.RunEndDay = RunEndDay;
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

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
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
    }
}
