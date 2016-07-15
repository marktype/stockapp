package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class DynamicsInfo implements Parcelable {

    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":1,"PageSize":10}
     * Share : [{"Id":"0","NickName":"牛人1号","UserName":"superwin111","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Comments":5347,"Forward":532,"Likes":2011,"UpdateTime":"2016-07-07","Content":"今天大盘小幅度高开，冲开后迎来一波杀跌，随后震荡走高...","Imgs":["http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png"]},{"Id":"1","NickName":"牛人2号","UserName":"superwin666","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Comments":29112,"Forward":666,"Likes":209991,"UpdateTime":"2016-07-07","Content":"今天大盘小幅度高开，冲开后迎来一波杀跌，随后震荡走高...","Imgs":["http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png"]},{"Id":"2","NickName":"牛人3号","UserName":"superwin996","ImgUrl":"http://www.supwin.com/media/1009/logo.png","Comments":1235,"Forward":666,"Likes":2135,"UpdateTime":"2016-07-07","Content":"今天大盘小幅度高开，冲开后迎来一波杀跌，随后震荡走高...","Imgs":["http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png"]}]
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

    public static class ResultBean implements Parcelable {
        /**
         * PageIndex : 0
         * PageCount : 1
         * PageSize : 10
         */

        private PageInfoBean PageInfo;
        /**
         * Id : 0
         * NickName : 牛人1号
         * UserName : superwin111
         * ImgUrl : http://www.supwin.com/media/1009/logo.png
         * Comments : 5347
         * Forward : 532
         * Likes : 2011
         * UpdateTime : 2016-07-07
         * Content : 今天大盘小幅度高开，冲开后迎来一波杀跌，随后震荡走高...
         * Imgs : ["http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png","http://www.supwin.com/media/1009/logo.png"]
         */

        private ArrayList<ShareBean> Share;

        public PageInfoBean getPageInfo() {
            return PageInfo;
        }

        public void setPageInfo(PageInfoBean PageInfo) {
            this.PageInfo = PageInfo;
        }

        public ArrayList<ShareBean> getShare() {
            return Share;
        }

        public void setShare(ArrayList<ShareBean> Share) {
            this.Share = Share;
        }

        public static class PageInfoBean implements Parcelable {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.PageIndex);
                dest.writeInt(this.PageCount);
                dest.writeInt(this.PageSize);
            }

            public PageInfoBean() {
            }

            protected PageInfoBean(Parcel in) {
                this.PageIndex = in.readInt();
                this.PageCount = in.readInt();
                this.PageSize = in.readInt();
            }

            public static final Creator<PageInfoBean> CREATOR = new Creator<PageInfoBean>() {
                @Override
                public PageInfoBean createFromParcel(Parcel source) {
                    return new PageInfoBean(source);
                }

                @Override
                public PageInfoBean[] newArray(int size) {
                    return new PageInfoBean[size];
                }
            };
        }

        public static class ShareBean implements Parcelable {
            private String Id;
            private String NickName;
            private String UserName;
            private String ImgUrl;
            private int Comments;
            private int Forward;
            private int Likes;
            private String UpdateTime;
            private String Content;
            private ArrayList<String> Imgs;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
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
                dest.writeString(this.NickName);
                dest.writeString(this.UserName);
                dest.writeString(this.ImgUrl);
                dest.writeInt(this.Comments);
                dest.writeInt(this.Forward);
                dest.writeInt(this.Likes);
                dest.writeString(this.UpdateTime);
                dest.writeString(this.Content);
                dest.writeStringList(this.Imgs);
            }

            public ShareBean() {
            }

            protected ShareBean(Parcel in) {
                this.Id = in.readString();
                this.NickName = in.readString();
                this.UserName = in.readString();
                this.ImgUrl = in.readString();
                this.Comments = in.readInt();
                this.Forward = in.readInt();
                this.Likes = in.readInt();
                this.UpdateTime = in.readString();
                this.Content = in.readString();
                this.Imgs = in.createStringArrayList();
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
            dest.writeParcelable(this.PageInfo, flags);
            dest.writeList(this.Share);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.PageInfo = in.readParcelable(PageInfoBean.class.getClassLoader());
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
