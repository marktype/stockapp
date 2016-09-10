package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/10.
 */
public class BannerInfo {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":7,"PageSize":10}
     * BannerUrl : [{"Id":"323","TargetUrl":"http://stock.eastmoney.com/news/1407,20160906661422251.html","BannerUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906142335504.jpg","Title":null},{"Id":"322","TargetUrl":"http://stock.eastmoney.com/news/1405,20160906661610337.html","BannerUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906142315099.jpg","Title":null},{"Id":"321","TargetUrl":"http://stock.eastmoney.com/news/1405,20160906661616047.html","BannerUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906142254150.jpg","Title":null},{"Id":"29","TargetUrl":"http://mbcaijing.baijia.baidu.com/article/590795","BannerUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160820111143810.jpg","Title":"房价飙涨之后的厦门：年轻人正在逃离\u201c最美城市\u201d"},{"Id":"28","TargetUrl":"http://youxituoluo.baijia.baidu.com/article/592584","BannerUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160820110930146.jpg","Title":"山寨游戏那么多，为什么成功的却没有几款？"},{"Id":"11","TargetUrl":"http://yanglinhua.baijia.baidu.com/article/590022","BannerUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160818175729837.jpg","Title":"川普惹怒全美风险投资协会: 这是个危险的总统！"},{"Id":"10","TargetUrl":"http://woshipm.baijia.baidu.com/article/590417","BannerUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160818175150493.jpg","Title":"这是广告页"}]
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
         * Id : 323
         * TargetUrl : http://stock.eastmoney.com/news/1407,20160906661422251.html
         * BannerUrl : http://filewebpath.matrix.lab.supwin.com:8899/20160906142335504.jpg
         * Title : null
         */

        private List<BannerUrlBean> BannerUrl;

        public List<BannerUrlBean> getBannerUrl() {
            return BannerUrl;
        }

        public void setBannerUrl(List<BannerUrlBean> BannerUrl) {
            this.BannerUrl = BannerUrl;
        }

        public static class BannerUrlBean {
            private String Id;
            private String TargetUrl;
            private String BannerUrl;
            private Object Title;

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

            public Object getTitle() {
                return Title;
            }

            public void setTitle(Object Title) {
                this.Title = Title;
            }
        }
    }
}
