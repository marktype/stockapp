package com.example.drawer.stockapp.model;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class NiuRenListInfo {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":1,"PageSize":10}
     * StarPorfolio : [{"Id":"0","UserName":"牛人1号","Title":"牛人1号超级组合","SecondTitle":"二级小标题","TotleReturns":102,"ImgUrl":"","WinRatio":95,"MonthlyAverage":60,"Holding":12,"Position":85,"AveragePosition":21,"AverageTrading":3,"Favorites":888,"CreateTime":"2017-01-01"},{"Id":"1","UserName":"牛人2号","Title":"牛人2号超级组合","SecondTitle":"二级小标题","TotleReturns":102,"ImgUrl":"","WinRatio":89,"MonthlyAverage":70,"Holding":3,"Position":90,"AveragePosition":21,"AverageTrading":3,"Favorites":888,"CreateTime":"2017-01-01"},{"Id":"2","UserName":"牛人3号","Title":"牛人3号超级组合","SecondTitle":"二级小标题","TotleReturns":102,"ImgUrl":"","WinRatio":98,"MonthlyAverage":70,"Holding":6,"Position":32,"AveragePosition":21,"AverageTrading":3,"Favorites":888,"CreateTime":"2017-01-01"},{"Id":"3","UserName":"牛人4号","Title":"牛人4号超级组合","SecondTitle":"二级小标题","TotleReturns":102,"ImgUrl":"","WinRatio":93,"MonthlyAverage":60,"Holding":12,"Position":85,"AveragePosition":21,"AverageTrading":3,"Favorites":888,"CreateTime":"2017-01-01"}]
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

        private ArrayList<StarPorfolioBean> StarPorfolio;

        public ArrayList<StarPorfolioBean> getStarPorfolio() {
            return StarPorfolio;
        }

        public void setStarPorfolio(ArrayList<StarPorfolioBean> StarPorfolio) {
            this.StarPorfolio = StarPorfolio;
        }

        public static class StarPorfolioBean {
            private String Id;
            private String UserName;
            private String Title;
            private String SecondTitle;
            private double TotleReturns;
            private String ImgUrl;
            private double WinRatio;
            private double MonthlyAverage;
            private int Holding;
            private double Position;
            private int AveragePosition;
            private double AverageTrading;
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
        }
    }
}
