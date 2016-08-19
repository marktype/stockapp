package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/8/17.
 * 发现
 */
public class FindInfo {


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
         * Id : 3
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Name : 精选课堂1
         * Forward : 1423
         * Comments : 2132
         * Favorites : 2257
         * TargetUrl : http://www.supwin.com/
         */

        private List<ExcellentCoursesBean> ExcellentCourses;
        /**
         * Id : 0
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Title : 合集1
         * Forward : 13
         * Comments : 32
         * Favorites : 557
         * IsForwar : true
         */

        private List<ExceCollBean> ExceColl;
        /**
         * Id : 0
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Name : 牛人1号
         * Source : 本地推荐人
         */

        private List<RecommendUsersBean> RecommendUsers;
        /**
         * Id : 0
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Name : 课堂1
         * Forward : 13
         * Comments : 32
         * Favorites : 557
         * TargetUrl : http://www.supwin.com/
         */

        private List<CoursesBean> Courses;

        public List<ExcellentCoursesBean> getExcellentCourses() {
            return ExcellentCourses;
        }

        public void setExcellentCourses(List<ExcellentCoursesBean> ExcellentCourses) {
            this.ExcellentCourses = ExcellentCourses;
        }

        public List<ExceCollBean> getExceColl() {
            return ExceColl;
        }

        public void setExceColl(List<ExceCollBean> ExceColl) {
            this.ExceColl = ExceColl;
        }

        public List<RecommendUsersBean> getRecommendUsers() {
            return RecommendUsers;
        }

        public void setRecommendUsers(List<RecommendUsersBean> RecommendUsers) {
            this.RecommendUsers = RecommendUsers;
        }

        public List<CoursesBean> getCourses() {
            return Courses;
        }

        public void setCourses(List<CoursesBean> Courses) {
            this.Courses = Courses;
        }

        public static class ExcellentCoursesBean {
            private String Id;
            private String ImgUrl;
            private String Name;
            private int Forward;
            private int Comments;
            private int Favorites;
            private String TargetUrl;

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

            public String getTargetUrl() {
                return TargetUrl;
            }

            public void setTargetUrl(String TargetUrl) {
                this.TargetUrl = TargetUrl;
            }
        }

        public static class ExceCollBean {
            private String Id;
            private String ImgUrl;
            private String Title;
            private int Forward;
            private int Comments;
            private int Favorites;
            private boolean IsForwar;

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

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
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

            public boolean isIsForwar() {
                return IsForwar;
            }

            public void setIsForwar(boolean IsForwar) {
                this.IsForwar = IsForwar;
            }
        }

        public static class RecommendUsersBean {
            private String Id;
            private String ImgUrl;
            private String Name;
            private String Source;

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

            public String getSource() {
                return Source;
            }

            public void setSource(String Source) {
                this.Source = Source;
            }
        }

        public static class CoursesBean {
            private String Id;
            private String ImgUrl;
            private String Name;
            private int Forward;
            private int Comments;
            private int Favorites;
            private String TargetUrl;

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

            public String getTargetUrl() {
                return TargetUrl;
            }

            public void setTargetUrl(String TargetUrl) {
                this.TargetUrl = TargetUrl;
            }
        }
    }
}
