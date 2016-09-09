package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/8.
 */
public class XueTangInfo {


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":5,"PageSize":10}
     * Courses : [{"Id":"360","ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906181934390.png","Name":"怎样在股价的回调期内波段操作","Forward":0,"Comments":4,"Favorites":0,"TargetUrl":null},{"Id":"359","ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906181821991.jpg","Name":"识别市场发生变盘前六点征兆","Forward":0,"Comments":0,"Favorites":0,"TargetUrl":null},{"Id":"358","ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906181717482.jpg","Name":"最后一跌的七大精准特征","Forward":0,"Comments":0,"Favorites":0,"TargetUrl":null},{"Id":"357","ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160906181549670.jpg","Name":"如何在收盘前30分钟抓住第二天上涨的股票","Forward":0,"Comments":0,"Favorites":0,"TargetUrl":null},{"Id":"245","ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160901190936944.jpeg","Name":"操盘讲堂第一回：巨量高开，是陷阱还是机会？","Forward":3,"Comments":6,"Favorites":1,"TargetUrl":null}]
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
         * PageIndex : 0
         * PageCount : 5
         * PageSize : 10
         */

        private PageInfoBean PageInfo;
        /**
         * Id : 360
         * ImgUrl : http://filewebpath.matrix.lab.supwin.com:8899/20160906181934390.png
         * Name : 怎样在股价的回调期内波段操作
         * Forward : 0
         * Comments : 4
         * Favorites : 0
         * TargetUrl : null
         */

        private List<CoursesBean> Courses;

        public PageInfoBean getPageInfo() {
            return PageInfo;
        }

        public void setPageInfo(PageInfoBean PageInfo) {
            this.PageInfo = PageInfo;
        }

        public List<CoursesBean> getCourses() {
            return Courses;
        }

        public void setCourses(List<CoursesBean> Courses) {
            this.Courses = Courses;
        }

        public static class PageInfoBean {
            private int PageIndex;
            private int PageCount;
            private int PageSize;

            public int getPageIndex() {
                return PageIndex;
            }

            public void setPageIndex(int PageIndex) {
                this.PageIndex = PageIndex;
            }

            public int getPageCount() {
                return PageCount;
            }

            public void setPageCount(int PageCount) {
                this.PageCount = PageCount;
            }

            public int getPageSize() {
                return PageSize;
            }

            public void setPageSize(int PageSize) {
                this.PageSize = PageSize;
            }
        }

        public static class CoursesBean {
            private String Id;
            private String ImgUrl;
            private String Name;
            private int Forward;
            private int Comments;
            private int Favorites;
            private Object TargetUrl;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getForward() {
                return Forward;
            }

            public void setForward(int Forward) {
                this.Forward = Forward;
            }

            public int getComments() {
                return Comments;
            }

            public void setComments(int Comments) {
                this.Comments = Comments;
            }

            public int getFavorites() {
                return Favorites;
            }

            public void setFavorites(int Favorites) {
                this.Favorites = Favorites;
            }

            public Object getTargetUrl() {
                return TargetUrl;
            }

            public void setTargetUrl(Object TargetUrl) {
                this.TargetUrl = TargetUrl;
            }
        }
    }
}
