package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 资讯指数对象(和精选课程、合集，推荐人信息共用)
 *  搜索信息
 */
public class HeadIndex implements Parcelable {
    private String indexName;    //指数名称   （精选课程/合集名字）（推荐人名字）(我的组合添加，策略名)  (搜索名称)
    private String indexImage;    //  （精选课程、合集图片）（推荐人图片）
    private String indexNum;     //数量     （定制精选数量）（我的组合添加，编号）     （搜索编号）
    private String indexPersent;    //百分比     （评论人数）（由谁推荐）（我的组合添加，百分数）
    private double price;    //股票价格

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexPersent() {
        return indexPersent;
    }

    public void setIndexPersent(String indexPersent) {
        this.indexPersent = indexPersent;
    }

    public String getIndexImage() {
        return indexImage;
    }

    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.indexName);
        dest.writeString(this.indexImage);
        dest.writeString(this.indexNum);
        dest.writeString(this.indexPersent);
        dest.writeDouble(price);
    }

    public HeadIndex() {
    }

    protected HeadIndex(Parcel in) {
        this.indexName = in.readString();
        this.indexImage = in.readString();
        this.indexNum = in.readString();
        this.indexPersent = in.readString();
        this.price = in.readDouble();
    }

    public static final Parcelable.Creator<HeadIndex> CREATOR = new Parcelable.Creator<HeadIndex>() {
        @Override
        public HeadIndex createFromParcel(Parcel source) {
            return new HeadIndex(source);
        }

        @Override
        public HeadIndex[] newArray(int size) {
            return new HeadIndex[size];
        }
    };
}
