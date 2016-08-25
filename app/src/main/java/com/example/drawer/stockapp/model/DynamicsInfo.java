package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class DynamicsInfo implements Parcelable {


    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":8,"PageSize":10}
     * Share : [{"Id":"129","UserId":"a3144b71850bf31f","NickName":"我我我我我我我我我","UserName":null,"ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160825162147007.jpg","Comments":0,"Forward":0,"Likes":0,"UpdateTime":"2016-08-25 16:48:20","Content":"我","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"117","UserId":"29641ff588ebed5c","NickName":null,"UserName":"15018400882","ImgUrl":null,"Comments":2,"Forward":0,"Likes":0,"UpdateTime":"2016-08-25 15:15:08","Content":"这是什么鬼","Imgs":["http://filewebpath.matrix.lab.supwin.com:8899/20160825151508950.jpg","http://filewebpath.matrix.lab.supwin.com:8899/20160825151508959.jpg","http://filewebpath.matrix.lab.supwin.com:8899/20160825151508968.jpg"],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"116","UserId":"571c2de6825c519b","NickName":"zoe","UserName":null,"ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","Comments":0,"Forward":2,"Likes":0,"UpdateTime":"2016-08-25 15:14:49","Content":"哈哈哈","Imgs":["http://filewebpath.matrix.lab.supwin.com:8899/20160825151449177.jpg"],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"88","UserId":"571c2de6825c519b","NickName":"zoe","UserName":null,"ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","Comments":3,"Forward":3,"Likes":0,"UpdateTime":"2016-08-24 15:54:21","Content":"string","Imgs":["http://filewebpath.matrix.lab.supwin.com:8899/20160824155421524.jpg"],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"86","UserId":"571c2de6825c519b","NickName":"zoe","UserName":null,"ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","Comments":1,"Forward":0,"Likes":0,"UpdateTime":"2016-08-24 15:35:03","Content":"测试5555555。。。。","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"85","UserId":"e0c35cc5309d9c63","NickName":"测","UserName":null,"ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160824152845988.jpg","Comments":1,"Forward":0,"Likes":0,"UpdateTime":"2016-08-24 15:34:33","Content":"测试5555555666","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"81","UserId":"e0c35cc5309d9c63","NickName":"测","UserName":null,"ImgUrl":"http://filewebpath.matrix.lab.supwin.com:8899/20160824152845988.jpg","Comments":0,"Forward":2,"Likes":0,"UpdateTime":"2016-08-24 10:17:12","Content":"测试数据","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"80","UserId":null,"NickName":null,"UserName":null,"ImgUrl":null,"Comments":0,"Forward":1,"Likes":0,"UpdateTime":"2016-08-24 10:05:20","Content":"hello你好。。。。。。。","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null}]
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

    public static class HeadBean implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.Status);
            dest.writeString(this.Msg);
        }

        public HeadBean() {
        }

        protected HeadBean(Parcel in) {
            this.Status = in.readInt();
            this.Msg = in.readString();
        }

        public static final Creator<HeadBean> CREATOR = new Creator<HeadBean>() {
            @Override
            public HeadBean createFromParcel(Parcel source) {
                return new HeadBean(source);
            }

            @Override
            public HeadBean[] newArray(int size) {
                return new HeadBean[size];
            }
        };
    }

    public static class ResultBean implements Parcelable {
        /**
         * Id : 129
         * UserId : a3144b71850bf31f
         * NickName : 我我我我我我我我我
         * UserName : null
         * ImgUrl : http://filewebpath.matrix.lab.supwin.com:8899/20160825162147007.jpg
         * Comments : 0
         * Forward : 0
         * Likes : 0
         * UpdateTime : 2016-08-25 16:48:20
         * Content : 我
         * Imgs : []
         * HasFavorites : false
         * HasForward : false
         * HasLike : false
         * IsForward : false
         * ForwardInfo : null
         */

        private List<ShareBean> Share;

        public List<ShareBean> getShare() {
            return Share;
        }

        public void setShare(List<ShareBean> Share) {
            this.Share = Share;
        }

        public static class ShareBean implements Parcelable {
            private String Id;
            private String UserId;
            private String NickName;
            private String UserName;
            private String ImgUrl;
            private int Comments;
            private int Forward;
            private int Likes;
            private String UpdateTime;
            private String Content;
            private boolean HasFavorites;
            private boolean HasForward;
            private boolean HasLike;
            private boolean IsForward;
            private String ForwardInfo;
            private ArrayList<String> Imgs;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
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

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
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

            public boolean isIsForward() {
                return IsForward;
            }

            public void setIsForward(boolean IsForward) {
                this.IsForward = IsForward;
            }

            public String getForwardInfo() {
                return ForwardInfo;
            }

            public void setForwardInfo(String ForwardInfo) {
                this.ForwardInfo = ForwardInfo;
            }

            public ArrayList<String> getImgs() {
                return Imgs;
            }

            public void setImgs(ArrayList<String> Imgs) {
                this.Imgs = Imgs;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.Id);
                dest.writeString(this.UserId);
                dest.writeString(this.NickName);
                dest.writeString(this.UserName);
                dest.writeString(this.ImgUrl);
                dest.writeInt(this.Comments);
                dest.writeInt(this.Forward);
                dest.writeInt(this.Likes);
                dest.writeString(this.UpdateTime);
                dest.writeString(this.Content);
                dest.writeByte(this.HasFavorites ? (byte) 1 : (byte) 0);
                dest.writeByte(this.HasForward ? (byte) 1 : (byte) 0);
                dest.writeByte(this.HasLike ? (byte) 1 : (byte) 0);
                dest.writeByte(this.IsForward ? (byte) 1 : (byte) 0);
                dest.writeString(this.ForwardInfo);
                dest.writeList(this.Imgs);
            }

            public ShareBean() {
            }

            protected ShareBean(Parcel in) {
                this.Id = in.readString();
                this.UserId = in.readString();
                this.NickName = in.readString();
                this.UserName = in.readString();
                this.ImgUrl = in.readString();
                this.Comments = in.readInt();
                this.Forward = in.readInt();
                this.Likes = in.readInt();
                this.UpdateTime = in.readString();
                this.Content = in.readString();
                this.HasFavorites = in.readByte() != 0;
                this.HasForward = in.readByte() != 0;
                this.HasLike = in.readByte() != 0;
                this.IsForward = in.readByte() != 0;
                this.ForwardInfo = in.readString();
                this.Imgs = new ArrayList<String>();
                in.readList(this.Imgs, String.class.getClassLoader());
            }

            public static final Creator<ShareBean> CREATOR = new Creator<ShareBean>() {
                @Override
                public ShareBean createFromParcel(Parcel source) {
                    return new ShareBean(source);
                }

                @Override
                public ShareBean[] newArray(int size) {
                    return new ShareBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.Share);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.Share = new ArrayList<ShareBean>();
            in.readList(this.Share, ShareBean.class.getClassLoader());
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Head, flags);
        dest.writeParcelable(this.Result, flags);
    }

    public DynamicsInfo() {
    }

    protected DynamicsInfo(Parcel in) {
        this.Head = in.readParcelable(HeadBean.class.getClassLoader());
        this.Result = in.readParcelable(ResultBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DynamicsInfo> CREATOR = new Parcelable.Creator<DynamicsInfo>() {
        @Override
        public DynamicsInfo createFromParcel(Parcel source) {
            return new DynamicsInfo(source);
        }

        @Override
        public DynamicsInfo[] newArray(int size) {
            return new DynamicsInfo[size];
        }
    };
}
