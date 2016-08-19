package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/8/17.
 */
public class ClassInfo {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * UserCourses : [{"Id":"0","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"课堂1","Forward":13,"Comments":32,"Favorites":557,"TargetUrl":"http://www.supwin.com/"},{"Id":"1","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"课堂2","Forward":123,"Comments":3212,"Favorites":457,"TargetUrl":"http://www.supwin.com/"},{"Id":"2","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"课堂3","Forward":1312,"Comments":3542,"Favorites":357,"TargetUrl":"http://www.supwin.com/"},{"Id":"3","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"精选课堂1","Forward":1423,"Comments":2132,"Favorites":2257,"TargetUrl":"http://www.supwin.com/"},{"Id":"4","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"精选课堂2","Forward":2133,"Comments":3762,"Favorites":547,"TargetUrl":"http://www.supwin.com/"},{"Id":"5","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"精选课堂3","Forward":13,"Comments":322,"Favorites":357,"TargetUrl":"http://www.supwin.com/"},{"Id":"6","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"我开的课1","Forward":163,"Comments":342,"Favorites":57,"TargetUrl":"http://www.supwin.com/"},{"Id":"7","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"我开的课2","Forward":123,"Comments":326,"Favorites":527,"TargetUrl":"http://www.supwin.com/"},{"Id":"8","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"我开的课3","Forward":135,"Comments":327,"Favorites":577,"TargetUrl":"http://www.supwin.com/"}]
     * UserOpenCourses : [{"Id":"6","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"我开的课1","Forward":163,"Comments":342,"Favorites":57,"TargetUrl":"http://www.supwin.com/"},{"Id":"7","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"我开的课2","Forward":123,"Comments":326,"Favorites":527,"TargetUrl":"http://www.supwin.com/"},{"Id":"8","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Name":"我开的课3","Forward":135,"Comments":327,"Favorites":577,"TargetUrl":"http://www.supwin.com/"}]
     * UserDynastic : {"Id":"0","SignDays":10,"FinishCourses":6,"AccumulativeTotal":13,"Credits":18}
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
         * SignDays : 10
         * FinishCourses : 6
         * AccumulativeTotal : 13
         * Credits : 18.0
         */

        private UserDynasticBean UserDynastic;
        /**
         * Id : 0
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Name : 课堂1
         * Forward : 13
         * Comments : 32
         * Favorites : 557
         * TargetUrl : http://www.supwin.com/
         */

        private List<UserCoursesBean> UserCourses;
        /**
         * Id : 6
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Name : 我开的课1
         * Forward : 163
         * Comments : 342
         * Favorites : 57
         * TargetUrl : http://www.supwin.com/
         */

        private List<UserOpenCoursesBean> UserOpenCourses;

        public UserDynasticBean getUserDynastic() {
            return UserDynastic;
        }

        public void setUserDynastic(UserDynasticBean UserDynastic) {
            this.UserDynastic = UserDynastic;
        }

        public List<UserCoursesBean> getUserCourses() {
            return UserCourses;
        }

        public void setUserCourses(List<UserCoursesBean> UserCourses) {
            this.UserCourses = UserCourses;
        }

        public List<UserOpenCoursesBean> getUserOpenCourses() {
            return UserOpenCourses;
        }

        public void setUserOpenCourses(List<UserOpenCoursesBean> UserOpenCourses) {
            this.UserOpenCourses = UserOpenCourses;
        }

        public static class UserDynasticBean {
            private String Id;
            private int SignDays;
            private int FinishCourses;
            private int AccumulativeTotal;
            private double Credits;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public int getSignDays() {
                return SignDays;
            }

            public void setSignDays(int SignDays) {
                this.SignDays = SignDays;
            }

            public int getFinishCourses() {
                return FinishCourses;
            }

            public void setFinishCourses(int FinishCourses) {
                this.FinishCourses = FinishCourses;
            }

            public int getAccumulativeTotal() {
                return AccumulativeTotal;
            }

            public void setAccumulativeTotal(int AccumulativeTotal) {
                this.AccumulativeTotal = AccumulativeTotal;
            }

            public double getCredits() {
                return Credits;
            }

            public void setCredits(double Credits) {
                this.Credits = Credits;
            }
        }

        public static class UserCoursesBean {
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

        public static class UserOpenCoursesBean {
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
