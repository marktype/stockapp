package com.example.drawer.stockapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 欢大哥 on 2016/6/14.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private ArrayList<String> titles;
    public MyViewPagerAdapter(List<View> viewList,ArrayList<String> list) {
        this.viewList=viewList;
        this.titles = list;
    }

    /**
     * 获得当前页卡的数量
     */
    public int getCount() {
        return viewList.size();
    }

    /**
     * 判断视图是否由对象产生
     */
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    /**
     * 实例化页卡
     */
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    /**
     * 删除页卡
     */
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
