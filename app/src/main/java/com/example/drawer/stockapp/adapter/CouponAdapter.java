package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.TrendsInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/8.
 * 优惠券item
 */
public class CouponAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TrendsInfo> list;
    public CouponAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<TrendsInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.coupon_item_layout,null);
        }
        return null;
    }
}
