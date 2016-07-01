package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class StockBean implements Parcelable {
    private int percent;
    private String name;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.percent);
        dest.writeString(this.name);
    }

    public StockBean() {
    }

    public StockBean(int percent, String name) {
        this.percent = percent;
        this.name = name;
    }

    protected StockBean(Parcel in) {
        this.percent = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<StockBean> CREATOR = new Creator<StockBean>() {
        @Override
        public StockBean createFromParcel(Parcel source) {
            return new StockBean(source);
        }

        @Override
        public StockBean[] newArray(int size) {
            return new StockBean[size];
        }
    };
}
