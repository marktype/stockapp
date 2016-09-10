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
    /**
     * PageInfo : {"PageIndex":0,"PageCount":31,"PageSize":10}
     * News : [{"Id":"429","Title":"测试链接","SecondTitle":"测试链接","BannerUrl":[null],"UpdateTime":"2016-09-09 11:44:01","Comments":0,"Forward":0,"Likes":0,"Favorites":0,"NewsType":0,"TargetUrl":"https://www.baidu.com/"},{"Id":"368","Title":"测试下资讯详情页","SecondTitle":"据俄罗斯卫星网最新消息，俄罗斯远东发展部部长亚历山大∙加卢什卡认为，俄日关系成功发展的主要前提是把领土和经济合作问题分开。","BannerUrl":[null],"UpdateTime":"2016-09-08 10:32:12","Comments":12,"Forward":7,"Likes":3,"Favorites":0,"NewsType":0,"TargetUrl":"http://finance.ifeng.com/a/20160908/14866737_0.shtml"},{"Id":"367","Title":"日俄\u201c迅猛\u201d签订20项协议 价值13亿美元","SecondTitle":"据俄罗斯卫星网最新消息，俄罗斯远东发展部部长亚历山大∙加卢什卡认为，俄日关系成功发展的主要前提是把领土和经济合作问题分开。","BannerUrl":["http://filewebpath.matrix.lab.supwin.com:8899/20160908101615112.jpg"],"UpdateTime":"2016-09-08 10:16:51","Comments":2,"Forward":1,"Likes":2,"Favorites":0,"NewsType":0,"TargetUrl":"http://finance.ifeng.com/a/20160908/14866737_0.shtml"},{"Id":"363","Title":"普京专车发生交通事故 司机当场死亡","SecondTitle":null,"BannerUrl":["http://filewebpath.matrix.lab.supwin.com:8899/20160907180950189.jpg"],"UpdateTime":"2016-09-07 18:10:12","Comments":1,"Forward":0,"Likes":0,"Favorites":0,"NewsType":0,"TargetUrl":"据英国路透社9月7日报道，周三（9月7日），泰国政府发言人素空塔帕提帕克(Weerachon Sukondhapatipak)称，在南海地区争端中，泰国支持中国维护海洋和平的努力。"},{"Id":"339","Title":"测试下图片","SecondTitle":null,"BannerUrl":[null],"UpdateTime":"2016-09-06 16:58:11","Comments":0,"Forward":0,"Likes":0,"Favorites":0,"NewsType":0,"TargetUrl":"http://finance.eastmoney.com/news/1345,20160906661655874.html"},{"Id":"306","Title":"再来一条","SecondTitle":null,"BannerUrl":[null],"UpdateTime":"2016-09-05 17:54:41","Comments":0,"Forward":0,"Likes":1,"Favorites":0,"NewsType":0,"TargetUrl":null},{"Id":"305","Title":"测试下解析问题","SecondTitle":"看看解析到底是什么问题","BannerUrl":[null],"UpdateTime":"2016-09-05 17:50:55","Comments":0,"Forward":0,"Likes":0,"Favorites":0,"NewsType":0,"TargetUrl":null},{"Id":"263","Title":"国企改革17个配套方案已出台 未来关注三大方向","SecondTitle":"二十国集团（G20）领导人第十一次峰会召开在即，中国的改革与发展进程受到世界瞩目。《关于深化国有企业改革的指导意见》去年出台以来已满一周年。一年来，包括《指导意见》在内的17个配套专项改革意见或方案已出台","BannerUrl":[null],"UpdateTime":"2016-09-05 14:00:52","Comments":8,"Forward":1,"Likes":1,"Favorites":1,"NewsType":0,"TargetUrl":"http://www.fx286.com/caijing/2016-09/319042.html"},{"Id":"262","Title":"万科A股价相当于股指6000点 9月行情调整的概率大","SecondTitle":"周二，沪深两市震荡，恒大概念股卷土重来，上海等地楼市疯狂抢购给股市投资者带来了乐观的情绪。可是，昨日万科A强势涨停，并没有带来市场的大涨，这显示市场上行动力在逐渐衰竭，大盘要跨越年线，还需要新的领涨龙头，更需量能的有效释放，量价的有效配合。","BannerUrl":[null],"UpdateTime":"2016-09-02 11:01:12","Comments":0,"Forward":0,"Likes":1,"Favorites":0,"NewsType":0,"TargetUrl":null},{"Id":"261","Title":"大批黄金从世界最大储存基地\u201c撤退\u201d，堪比08年","SecondTitle":"各国央行大量黄金储备并没有全部存在本国，许多国家的黄金都储藏在美国。美国纽约联邦储备银行(简称纽约联储)的保管库被称为\u201c世界上最安全的仓库\u201d，有60个国家把自己的黄金储备藏于此地。","BannerUrl":["http://filewebpath.matrix.lab.supwin.com:8899/20160902110021476.jpeg"],"UpdateTime":"2016-09-07 18:20:24","Comments":1,"Forward":1,"Likes":2,"Favorites":0,"NewsType":0,"TargetUrl":null}]
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
         * Id : 429
         * Title : 测试链接
         * SecondTitle : 测试链接
         * BannerUrl : [null]
         * UpdateTime : 2016-09-09 11:44:01
         * Comments : 0
         * Forward : 0
         * Likes : 0
         * Favorites : 0
         * NewsType : 0
         * TargetUrl : https://www.baidu.com/
         */

        private List<NewsBean> News;

        public List<NewsBean> getNews() {
            return News;
        }

        public void setNews(List<NewsBean> News) {
            this.News = News;
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
            private String TargetUrl;
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

            public String getTargetUrl() {
                return TargetUrl;
            }

            public void setTargetUrl(String TargetUrl) {
                this.TargetUrl = TargetUrl;
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
