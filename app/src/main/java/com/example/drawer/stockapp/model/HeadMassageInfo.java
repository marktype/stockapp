package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/7/13.
 */
public class HeadMassageInfo implements Parcelable {


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



    public static class ResultBean implements Parcelable {
        private ArrayList<String> BannerUrl;
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
         * BannerUrl : http://www.supwin.com/media/1009/logo.png
         * UpdateTime : 2016-07-07
         * Comments : 5347
         * Forward : 532
         * Likes : 2011
         * Favorites : 300
         */

        private ArrayList<NewsBean> News;

        public ArrayList<String> getBannerUrl() {
            return BannerUrl;
        }

        public void setBannerUrl(ArrayList<String> BannerUrl) {
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

        public static class MarketDataBean implements Parcelable {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.Id);
                dest.writeString(this.Name);
                dest.writeString(this.Code);
                dest.writeDouble(this.Points);
                dest.writeDouble(this.VariabilityPoints);
                dest.writeDouble(this.VariabilityRate);
                dest.writeString(this.UpdateTime);
            }

            public MarketDataBean() {
            }

            protected MarketDataBean(Parcel in) {
                this.Id = in.readString();
                this.Name = in.readString();
                this.Code = in.readString();
                this.Points = in.readDouble();
                this.VariabilityPoints = in.readDouble();
                this.VariabilityRate = in.readDouble();
                this.UpdateTime = in.readString();
            }

            public static final Creator<MarketDataBean> CREATOR = new Creator<MarketDataBean>() {
                @Override
                public MarketDataBean createFromParcel(Parcel source) {
                    return new MarketDataBean(source);
                }

                @Override
                public MarketDataBean[] newArray(int size) {
                    return new MarketDataBean[size];
                }
            };
        }

        public static class NewsBean implements Parcelable {
            private String Id;
            private String Title;
            private String BannerUrl;
            private String UpdateTime;
            private int Comments;
            private int Forward;
            private int Likes;
            private int Favorites;

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

            public String getBannerUrl() {
                return BannerUrl;
            }

            public void setBannerUrl(String BannerUrl) {
                this.BannerUrl = BannerUrl;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.Id);
                dest.writeString(this.Title);
                dest.writeString(this.BannerUrl);
                dest.writeString(this.UpdateTime);
                dest.writeInt(this.Comments);
                dest.writeInt(this.Forward);
                dest.writeInt(this.Likes);
                dest.writeInt(this.Favorites);
            }

            public NewsBean() {
            }

            protected NewsBean(Parcel in) {
                this.Id = in.readString();
                this.Title = in.readString();
                this.BannerUrl = in.readString();
                this.UpdateTime = in.readString();
                this.Comments = in.readInt();
                this.Forward = in.readInt();
                this.Likes = in.readInt();
                this.Favorites = in.readInt();
            }

            public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
                @Override
                public NewsBean createFromParcel(Parcel source) {
                    return new NewsBean(source);
                }

                @Override
                public NewsBean[] newArray(int size) {
                    return new NewsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStringList(this.BannerUrl);
            dest.writeList(this.MarketData);
            dest.writeList(this.News);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.BannerUrl = in.createStringArrayList();
            this.MarketData = new ArrayList<MarketDataBean>();
            in.readList(this.MarketData, MarketDataBean.class.getClassLoader());
            this.News = new ArrayList<NewsBean>();
            in.readList(this.News, NewsBean.class.getClassLoader());
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

    public HeadMassageInfo() {
    }

    protected HeadMassageInfo(Parcel in) {
        this.Head = in.readParcelable(HeadBean.class.getClassLoader());
        this.Result = in.readParcelable(ResultBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HeadMassageInfo> CREATOR = new Parcelable.Creator<HeadMassageInfo>() {
        @Override
        public HeadMassageInfo createFromParcel(Parcel source) {
            return new HeadMassageInfo(source);
        }

        @Override
        public HeadMassageInfo[] newArray(int size) {
            return new HeadMassageInfo[size];
        }
    };
}
