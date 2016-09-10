package com.example.drawer.stockapp.model;

/**
 * Created by 欢大哥 on 2016/8/26.
 */
public class NewsDetial {


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * Id : 67
     * Title : hello竞走
     * UpdateTime : 2016-08-20 16:43:20
     * Author : 新浪
     * Content : &lt;ins style="text-decoration:none;margin:0px auto;width:300px;display:block;position:relative;"&gt;&lt;/ins&gt;&lt;p&gt;　　最后两公里开争！冈萨雷斯在两位中国选手的夹击下奋力前进，现在问题不是谁摆脱谁，三个人都在加速，但根本形成不了绝对领先。关键时刻刘虹吃到一张黄牌。接着冈萨雷斯也吃到黄牌，但终点就在前方，三人都没任何理由调整速度！冈萨雷斯只能说稍稍领先两位中国选手一肩之宽的距离。&lt;/p&gt;&lt;p&gt;　　最后500米左右三人冲刺，吕秀芝被甩开，刘虹和冈萨雷斯疯狂竞争！在最后大概30米的地方，刘虹终于走在了冈萨雷斯的前面！这次墨西哥人无力、也没足够的距离让她从侧反击，刘虹最终以1小时28分35秒惊险获得冠军，墨西哥选手冈萨雷斯以仅仅落后两秒的劣势获得亚军，吕秀芝以1小时28分42秒获得第三名。&lt;/p&gt;&lt;p&gt;　　（威猛）&lt;/p&gt;
     * Comments : 0
     * Forward : 0
     * Likes : 0
     * Favorites : 0
     * TargetUrl : null
     * HasFavorites : false
     * HasForward : false
     * HasLike : false
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
        private String Id;
        private String Title;
        private String UpdateTime;
        private String Author;
        private String Content;
        private int Comments;
        private int Forward;
        private int Likes;
        private int Favorites;
        private String TargetUrl;
        private boolean HasFavorites;
        private boolean HasForward;
        private boolean HasLike;

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

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String Author) {
            this.Author = Author;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
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

        public String getTargetUrl() {
            return TargetUrl;
        }

        public void setTargetUrl(String TargetUrl) {
            this.TargetUrl = TargetUrl;
        }

        public boolean isHasFavorites() {
            return HasFavorites;
        }

        public void setHasFavorites(boolean HasFavorites) {
            this.HasFavorites = HasFavorites;
        }

        public boolean isHasForward() {
            return HasForward;
        }

        public void setHasForward(boolean HasForward) {
            this.HasForward = HasForward;
        }

        public boolean isHasLike() {
            return HasLike;
        }

        public void setHasLike(boolean HasLike) {
            this.HasLike = HasLike;
        }
    }
}
