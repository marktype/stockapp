package com.example.drawer.stockapp.model;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/7/13.
 */
public class HeadMassageInfo{


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
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
         * TargetUrl : http://www.supwin.com/
         * BannerUrl : http://www.supwin.com/media/1009/logo.png
         * Title : 标题1
         */

        private ArrayList<BannerUrlBean> BannerUrl;
        /**
         * Id : 0
         * Name : 上证指数
         * Code : 000001
         * Points : 2913.51
         * VariabilityPoints : -3.11
         * VariabilityRate : -0.11
         * UpdateTime : 2016-07-07 12:00:00
         */

        private ArrayList<MarketDataBean> MarketData;
        /**
         * Id : 0
         * Title : 任正非向高层汇报：为何华为现在感到迷茫
         * SecondTitle : 副标题
         * BannerUrl : ["http://www.supwin.com/media/1009/logo.png"]
         * UpdateTime : 2016-07-07
         * Comments : 5347
         * Forward : 532
         * Likes : 2011
         * Favorites : 300
         * NewsType : 0
         * TargetUri :
         */

        private ArrayList<NewsBean> News;

        public ArrayList<BannerUrlBean> getBannerUrl() {
            return BannerUrl;
        }

        public void setBannerUrl(ArrayList<BannerUrlBean> BannerUrl) {
            this.BannerUrl = BannerUrl;
        }

        public ArrayList<MarketDataBean> getMarketData() {
            return MarketData;
        }

        public void setMarketData(ArrayList<MarketDataBean> MarketData) {
            this.MarketData = MarketData;
        }

        public ArrayList<NewsBean> getNews() {
            return News;
        }

        public void setNews(ArrayList<NewsBean> News) {
            this.News = News;
        }

        public static class BannerUrlBean {
            private String Id;
            private String BannerUrl;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getBannerUrl() {
                return BannerUrl;
            }

            public void setBannerUrl(String BannerUrl) {
                this.BannerUrl = BannerUrl;
            }
        }

        public static class MarketDataBean {
            private String Id;
            private String Name;
            private String Code;
            private double Points;
            private double VariabilityPoints;
            private double VariabilityRate;
            private String UpdateTime;

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

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public double getPoints() {
                return Points;
            }

            public void setPoints(double Points) {
                this.Points = Points;
            }

            public double getVariabilityPoints() {
                return VariabilityPoints;
            }

            public void setVariabilityPoints(double VariabilityPoints) {
                this.VariabilityPoints = VariabilityPoints;
            }

            public double getVariabilityRate() {
                return VariabilityRate;
            }

            public void setVariabilityRate(double VariabilityRate) {
                this.VariabilityRate = VariabilityRate;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }
        }

        public static class NewsBean {
            private String Id;
            private String Title;
            private String SecondTitle;
            private String UpdateTime;
            private int Comments;
            private int Forward;
            private int Likes;
            private int Favorites;
            private int NewsType;
            private String TargetUri;
            private ArrayList<String> BannerUrl;

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

            public String getSecondTitle() {
                return SecondTitle;
            }

            public void setSecondTitle(String SecondTitle) {
                this.SecondTitle = SecondTitle;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public int getComments() {
                return Comments;
            }

            public void setComments(int Comments) {
                this.Comments = Comments;
            }

            public int getForward() {
                return Forward;
            }

            public void setForward(int Forward) {
                this.Forward = Forward;
            }

            public int getLikes() {
                return Likes;
            }

            public void setLikes(int Likes) {
                this.Likes = Likes;
            }

            public int getFavorites() {
                return Favorites;
            }

            public void setFavorites(int Favorites) {
                this.Favorites = Favorites;
            }

            public int getNewsType() {
                return NewsType;
            }

            public void setNewsType(int NewsType) {
                this.NewsType = NewsType;
            }

            public String getTargetUri() {
                return TargetUri;
            }

            public void setTargetUri(String TargetUri) {
                this.TargetUri = TargetUri;
            }

            public ArrayList<String> getBannerUrl() {
                return BannerUrl;
            }

            public void setBannerUrl(ArrayList<String> BannerUrl) {
                this.BannerUrl = BannerUrl;
            }
        }
    }
}
