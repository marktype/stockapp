package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class HeadBean implements Parcelable {
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
