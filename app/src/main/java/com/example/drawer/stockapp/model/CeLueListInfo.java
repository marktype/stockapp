package com.example.drawer.stockapp.model;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class CeLueListInfo {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":1,"PageSize":10}
     * Strategies : [{"Id":"0","Recruitment":75,"Name":"稳赢1号","Desc":"以量化核心，低风险，平稳收益","TargetReturns":8,"MaxDay":365,"ShareRatio":8,"MinFollow":1000,"UserName":"牛人1号","UserImgUrl":"http://www.supwin.com/media/1009/logo.png","UserLevel":"12","UserLevelImgUrl":"http://www.supwin.com/media/1009/logo.png"},{"Id":"1","Recruitment":85,"Name":"稳赢2号","Desc":"以量化核心，低风险，平稳收益","TargetReturns":8.2,"MaxDay":365,"ShareRatio":8.1,"MinFollow":1000,"UserName":"牛人1号","UserImgUrl":"http://www.supwin.com/media/1009/logo.png","UserLevel":"12","UserLevelImgUrl":"http://www.supwin.com/media/1009/logo.png"},{"Id":"2","Recruitment":92,"Name":"稳赢3号","Desc":"以量化核心，低风险，平稳收益","TargetReturns":8.8,"MaxDay":365,"ShareRatio":8.2,"MinFollow":1000,"UserName":"牛人1号","UserImgUrl":"http://www.supwin.com/media/1009/logo.png","UserLevel":"12","UserLevelImgUrl":"http://www.supwin.com/media/1009/logo.png"}]
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
         * Recruitment : 75
         * Name : 稳赢1号
         * Desc : 以量化核心，低风险，平稳收益
         * TargetReturns : 8.0
         * MaxDay : 365
         * ShareRatio : 8.0
         * MinFollow : 1000.0
         * UserName : 牛人1号
         * UserImgUrl : http://www.supwin.com/media/1009/logo.png
         * UserLevel : 12
         * UserLevelImgUrl : http://www.supwin.com/media/1009/logo.png
         */

        private ArrayList<StrategiesBean> Strategies;

        public ArrayList<StrategiesBean> getStrategies() {
            return Strategies;
        }

        public void setStrategies(ArrayList<StrategiesBean> Strategies) {
            this.Strategies = Strategies;
        }

        public static class StrategiesBean {
            private String Id;
            private int Recruitment;
            private String Name;
            private String Desc;
            private double TargetReturns;
            private int MaxDay;
            private double ShareRatio;
            private double MinFollow;
            private String UserName;
            private String UserImgUrl;
            private String UserLevel;
            private String UserLevelImgUrl;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public int getRecruitment() {
                return Recruitment;
            }

            public void setRecruitment(int Recruitment) {
                this.Recruitment = Recruitment;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getDesc() {
                return Desc;
            }

            public void setDesc(String Desc) {
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

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getUserImgUrl() {
                return UserImgUrl;
            }

            public void setUserImgUrl(String UserImgUrl) {
                this.UserImgUrl = UserImgUrl;
            }

            public String getUserLevel() {
                return UserLevel;
            }

            public void setUserLevel(String UserLevel) {
                this.UserLevel = UserLevel;
            }

            public String getUserLevelImgUrl() {
                return UserLevelImgUrl;
            }

            public void setUserLevelImgUrl(String UserLevelImgUrl) {
                this.UserLevelImgUrl = UserLevelImgUrl;
            }
        }
    }
}
