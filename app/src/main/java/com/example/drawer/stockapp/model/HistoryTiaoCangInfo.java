package com.example.drawer.stockapp.model;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/9/19.
 */
public class HistoryTiaoCangInfo {
    private String title;
    private String time;
    private ArrayList<TiaoCangInfo> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<TiaoCangInfo> getList() {
        return list;
    }

    public void setList(ArrayList<TiaoCangInfo> list) {
        this.list = list;
    }
}
