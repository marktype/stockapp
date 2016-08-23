package com.example.drawer.stockapp.model;

import java.util.List;

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
         * Id : 29
         * TargetUrl : http://mbcaijing.baijia.baidu.com/article/590795
         * BannerUrl : http://filewebpath.matrix.lab.supwin.com:8899/20160820111143810.jpg
         * Title : 房价飙涨之后的厦门：年轻人正在逃离“最美城市”
         */

        private List<BannerUrlBean> BannerUrl;
        /**
         * Id : 31
         * Title : 冬日娜问中国第1棒:你起跑反应全场最慢是求稳吗?
         * SecondTitle : 冬日娜问中国第1棒:你起跑反应全场最慢是求稳吗?
         * BannerUrl : [null]
         * UpdateTime : 2016-08-20 13:51:40
         * Comments : 0
         * Forward : 0
         * Likes : 0
         * Favorites : 0
         * NewsType : 0
         * TargetUri : null
         */

        private List<NewsBean> News;

        public List<BannerUrlBean> getBannerUrl() {
            return BannerUrl;
        }

        public void setBannerUrl(List<BannerUrlBean> BannerUrl) {
            this.BannerUrl = BannerUrl;
        }

        public List<NewsBean> getNews() {
            return News;
        }

        public void setNews(List<NewsBean> News) {
            this.News = News;
        }

        public static class BannerUrlBean {
            private String Id;
            private String TargetUrl;
            private String BannerUrl;
            private String Title;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getTargetUrl() {
                return TargetUrl;
            }

            public void setTargetUrl(String TargetUrl) {
                this.TargetUrl = TargetUrl;
            }

            public String getBannerUrl() {
                return BannerUrl;
            }

            public void setBannerUrl(String BannerUrl) {
                this.BannerUrl = BannerUrl;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
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
            private Object TargetUri;
            private List<?> BannerUrl;

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

            public Object getTargetUri() {
                return TargetUri;
            }

            public void setTargetUri(Object TargetUri) {
                this.TargetUri = TargetUri;
            }

            public List<?> getBannerUrl() {
                return BannerUrl;
            }

            public void setBannerUrl(List<?> BannerUrl) {
                this.BannerUrl = BannerUrl;
            }
        }
    }
}
