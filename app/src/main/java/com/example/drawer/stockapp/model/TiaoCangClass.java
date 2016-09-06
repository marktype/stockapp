package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/9/5.
 */
public class TiaoCangClass implements Parcelable {

    private String stockID;   //组合id
    private String name;     //组合名字
    private String desc;    //组合描述

    private double totalMoney;
    private ArrayList<HeadIndex> list;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public ArrayList<HeadIndex> getList() {
        return list;
    }

    public void setList(ArrayList<HeadIndex> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stockID);
        dest.writeDouble(this.totalMoney);
        dest.writeTypedList(this.list);
        dest.writeString(this.name);
        dest.writeString(this.desc);
    }

    public TiaoCangClass() {
    }

    protected TiaoCangClass(Parcel in) {
        this.stockID = in.readString();
        this.totalMoney = in.readDouble();
        this.list = in.createTypedArrayList(HeadIndex.CREATOR);
        this.name = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<TiaoCangClass> CREATOR = new Parcelable.Creator<TiaoCangClass>() {
        @Override
        public TiaoCangClass createFromParcel(Parcel source) {
            return new TiaoCangClass(source);
        }

        @Override
        public TiaoCangClass[] newArray(int size) {
            return new TiaoCangClass[size];
        }
    };
}
