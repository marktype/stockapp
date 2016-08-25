package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class CeLueListInfo {


    /**
     * Status : 0
     * Msg :
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":7,"PageSize":10}
     * Strategies : [{"Id":"1593","Recruitment":0,"Name":"爱猫爪2号","Desc":null,"TargetReturns":10,"MaxDay":4,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":5,"RecuitmentStartTime":"2016-08-22 06:08:00","RecuitmentEndTime":"2016-08-24 06:08:00","RunStartDay":"2016-08-24 06:09:00","RunEndDay":null},{"Id":"1592","Recruitment":1,"Name":"实盈量化第二期","Desc":"这是一个量化策略组合","TargetReturns":10,"MaxDay":5,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":false,"IsNotStartRecruit":false,"IsStartInvestment":false,"IsStartRecruit":true,"IsStartRun":false,"StopLoss":5,"RecuitmentStartTime":"2016-08-25 06:02:00","RecuitmentEndTime":"2016-08-26 06:02:00","RunStartDay":"2016-08-26 04:55:00","RunEndDay":null},{"Id":"1590","Recruitment":100,"Name":"爱猫爪1号","Desc":"测试一下","TargetReturns":5,"MaxDay":1,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":false,"IsNotStartRecruit":false,"IsStartInvestment":false,"IsStartRecruit":true,"IsStartRun":false,"StopLoss":0.9,"RecuitmentStartTime":"2016-08-25 02:19:00","RecuitmentEndTime":"2016-08-26 02:19:00","RunStartDay":"2016-08-26 02:20:00","RunEndDay":null},{"Id":"1578","Recruitment":0,"Name":"测试","Desc":"123","TargetReturns":1,"MaxDay":8,"ShareRatio":10,"MinFollow":1,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":10,"RecuitmentStartTime":"2016-08-22 09:26:00","RecuitmentEndTime":"2016-08-23 09:26:00","RunStartDay":"2016-08-23 09:27:00","RunEndDay":null},{"Id":"1577","Recruitment":20,"Name":"猫猫","Desc":"猫猫猫猫","TargetReturns":1,"MaxDay":5,"ShareRatio":1,"MinFollow":100,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":2,"RecuitmentStartTime":"2016-08-19 09:21:00","RecuitmentEndTime":"2016-08-20 09:21:00","RunStartDay":"2016-08-21 09:21:00","RunEndDay":"2016-08-23 05:02:51"},{"Id":"1566","Recruitment":0.45,"Name":"爱猫爪1","Desc":"爱猫爪1--发发发","TargetReturns":5,"MaxDay":3,"ShareRatio":10,"MinFollow":1000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":4.78,"RecuitmentStartTime":"2016-08-22 08:24:00","RecuitmentEndTime":"2016-08-23 08:24:00","RunStartDay":"2016-08-23 07:17:00","RunEndDay":"2016-08-25 02:16:43"},{"Id":"1565","Recruitment":4,"Name":"实盈量化第一期","Desc":"这一是一个测试的组合，请慎用","TargetReturns":15,"MaxDay":6,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":5,"RecuitmentStartTime":"2016-08-21 03:59:00","RecuitmentEndTime":"2016-08-22 15:59:00","RunStartDay":"2016-08-22 17:59:00","RunEndDay":null}]
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
         * Id : 1593
         * Recruitment : 0.0
         * Name : 爱猫爪2号
         * Desc : null
         * TargetReturns : 10.0
         * MaxDay : 4
         * ShareRatio : 0.0
         * MinFollow : 10000.0
         * UserName : null
         * UserImgUrl : null
         * IsEndInvestment : false
         * IsEndRecruit : true
         * IsNotStartRecruit : false
         * IsStartInvestment : true
         * IsStartRecruit : false
         * IsStartRun : true
         * StopLoss : 5.0
         * RecuitmentStartTime : 2016-08-22 06:08:00
         * RecuitmentEndTime : 2016-08-24 06:08:00
         * RunStartDay : 2016-08-24 06:09:00
         * RunEndDay : null
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
            private String Recruitment;
            private String Name;
            private Object Desc;
            private double TargetReturns;
            private int MaxDay;
            private double ShareRatio;
            private double MinFollow;
            private Object UserName;
            private Object UserImgUrl;
            private boolean IsEndInvestment;
            private boolean IsEndRecruit;
            private boolean IsNotStartRecruit;
            private boolean IsStartInvestment;
            private boolean IsStartRecruit;
            private boolean IsStartRun;
            private double StopLoss;
            private String RecuitmentStartTime;
            private String RecuitmentEndTime;
            private String RunStartDay;
            private Object RunEndDay;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getRecruitment() {
                return Recruitment;
            }

            public void setRecruitment(String Recruitment) {
                this.Recruitment = Recruitment;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public Object getDesc() {
                return Desc;
            }

            public void setDesc(Object Desc) {
                this.Desc = Desc;
            }

            public double getTargetReturns() {
                return TargetReturns;
            }

            public void setTargetReturns(double TargetReturns) {
                this.TargetReturns = TargetReturns;
            }

            public int getMaxDay() {
                return MaxDay;
            }

            public void setMaxDay(int MaxDay) {
                this.MaxDay = MaxDay;
            }

            public double getShareRatio() {
                return ShareRatio;
            }

            public void setShareRatio(double ShareRatio) {
                this.ShareRatio = ShareRatio;
            }

            public double getMinFollow() {
                return MinFollow;
            }

            public void setMinFollow(double MinFollow) {
                this.MinFollow = MinFollow;
            }

            public Object getUserName() {
                return UserName;
            }

            public void setUserName(Object UserName) {
                this.UserName = UserName;
            }

            public Object getUserImgUrl() {
                return UserImgUrl;
            }

            public void setUserImgUrl(Object UserImgUrl) {
                this.UserImgUrl = UserImgUrl;
            }

            public boolean isIsEndInvestment() {
                return IsEndInvestment;
            }

            public void setIsEndInvestment(boolean IsEndInvestment) {
                this.IsEndInvestment = IsEndInvestment;
            }

            public boolean isIsEndRecruit() {
                return IsEndRecruit;
            }

            public void setIsEndRecruit(boolean IsEndRecruit) {
                this.IsEndRecruit = IsEndRecruit;
            }

            public boolean isIsNotStartRecruit() {
                return IsNotStartRecruit;
            }

            public void setIsNotStartRecruit(boolean IsNotStartRecruit) {
                this.IsNotStartRecruit = IsNotStartRecruit;
            }

            public boolean isIsStartInvestment() {
                return IsStartInvestment;
            }

            public void setIsStartInvestment(boolean IsStartInvestment) {
                this.IsStartInvestment = IsStartInvestment;
            }

            public boolean isIsStartRecruit() {
                return IsStartRecruit;
            }

            public void setIsStartRecruit(boolean IsStartRecruit) {
                this.IsStartRecruit = IsStartRecruit;
            }

            public boolean isIsStartRun() {
                return IsStartRun;
            }

            public void setIsStartRun(boolean IsStartRun) {
                this.IsStartRun = IsStartRun;
            }

            public double getStopLoss() {
                return StopLoss;
            }

            public void setStopLoss(double StopLoss) {
                this.StopLoss = StopLoss;
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
        }
    }
}
