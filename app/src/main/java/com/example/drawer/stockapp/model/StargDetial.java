package com.example.drawer.stockapp.model;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class StargDetial {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * StrategyDetail : {"Id":"0","Name":"牛人1号","Type":0,"ImgUrl":"http://www.supwin.com/media/1009/logo.png","RecuitmentStartTDay":"2016-07-07 00:00:00","RecuitmentEndTimeDay":"2016-07-12 23:59:59","StarInvestment":1000000,"MostFollow":2000000,"Desc":"高风险，高回报","MaxDay":90,"StopLoss":15,"ShareRatio":60,"RunStartDay":"2016-07-13","RunEndDay":"2016-10-12","TargetReturns":0,"Recruitment":92,"Rights":"享受服务<p>第一，....<\/p>"}
     * StarInfo : {"Id":"12","Name":"牛人5号","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Title":"超级期待牛人5号","MonthlyAverage":35,"PorfolioSucc":100,"StockPick":79,"Desc":"未来市场震荡明显，只要震荡，就是赚的时候"}
     * ImgUrl : http://www.supwin.com/media/1009/logo.png
     * FollowInfo : [{"Id":"0","Title":"最合适的建议","ImgUrl":"http://www.supwin.com/media/1009/logo.png"},{"Id":"0","Title":"10个你应该关注我微信号","ImgUrl":"http://www.supwin.com/media/1009/logo.png"},{"Id":"0","Title":"投资，理财","ImgUrl":"http://www.supwin.com/media/1009/logo.png"}]
     * CallProtection : <h1>退款保障</h1><p>带html标签的内容</p>
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
         * Name : 牛人1号
         * Type : 0
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * RecuitmentStartTDay : 2016-07-07 00:00:00
         * RecuitmentEndTimeDay : 2016-07-12 23:59:59
         * StarInvestment : 1000000.0
         * MostFollow : 2000000.0
         * Desc : 高风险，高回报
         * MaxDay : 90
         * StopLoss : 15.0
         * ShareRatio : 60.0
         * RunStartDay : 2016-07-13
         * RunEndDay : 2016-10-12
         * TargetReturns : 0.0
         * Recruitment : 92
         * Rights : 享受服务<p>第一，....</p>
         */

        private StrategyDetailBean StrategyDetail;
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
        private String ImgUrl;
        private String CallProtection;
        /**
         * Id : 0
         * Title : 最合适的建议
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         */

        private ArrayList<FollowInfoBean> FollowInfo;

        public StrategyDetailBean getStrategyDetail() {
            return StrategyDetail;
        }

        public void setStrategyDetail(StrategyDetailBean StrategyDetail) {
            this.StrategyDetail = StrategyDetail;
        }

        public StarInfoBean getStarInfo() {
            return StarInfo;
        }

        public void setStarInfo(StarInfoBean StarInfo) {
            this.StarInfo = StarInfo;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public String getCallProtection() {
            return CallProtection;
        }

        public void setCallProtection(String CallProtection) {
            this.CallProtection = CallProtection;
        }

        public ArrayList<FollowInfoBean> getFollowInfo() {
            return FollowInfo;
        }

        public void setFollowInfo(ArrayList<FollowInfoBean> FollowInfo) {
            this.FollowInfo = FollowInfo;
        }

        public static class StrategyDetailBean {
            private String Id;
            private String Name;
            private int Type;
            private String ImgUrl;
            private String RecuitmentStartTDay;
            private String RecuitmentEndTimeDay;
            private double StarInvestment;
            private double MostFollow;
            private String Desc;
            private int MaxDay;
            private double StopLoss;
            private double ShareRatio;
            private String RunStartDay;
            private String RunEndDay;
            private double TargetReturns;
            private int Recruitment;
            private String Rights;

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

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public String getRecuitmentStartTDay() {
                return RecuitmentStartTDay;
            }

            public void setRecuitmentStartTDay(String RecuitmentStartTDay) {
                this.RecuitmentStartTDay = RecuitmentStartTDay;
            }

            public String getRecuitmentEndTimeDay() {
                return RecuitmentEndTimeDay;
            }

            public void setRecuitmentEndTimeDay(String RecuitmentEndTimeDay) {
                this.RecuitmentEndTimeDay = RecuitmentEndTimeDay;
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

            public int getRecruitment() {
                return Recruitment;
            }

            public void setRecruitment(int Recruitment) {
                this.Recruitment = Recruitment;
            }

            public String getRights() {
                return Rights;
            }

            public void setRights(String Rights) {
                this.Rights = Rights;
            }
        }

        public static class StarInfoBean {
            private String Id;
            private String Name;
            private String ImgUrl;
            private String Title;
            private double MonthlyAverage;
            private double PorfolioSucc;
            private double StockPick;
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

            public String getDesc() {
                return Desc;
            }

            public void setDesc(String Desc) {
                this.Desc = Desc;
            }
        }

        public static class FollowInfoBean {
            private String Id;
            private String Title;
            private String ImgUrl;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }
        }
    }
}
