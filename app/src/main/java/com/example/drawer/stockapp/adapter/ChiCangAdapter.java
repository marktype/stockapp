package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.ChiCangInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/28.
 */
public class ChiCangAdapter extends BaseAdapter {
    private ArrayList<ChiCangInfo> list;
    private Context context;
    public ChiCangAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<ChiCangInfo> list){
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
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.chicang_item_list,null);
            viewHolder = new ViewHolder();
            viewHolder.stockName = (TextView) view.findViewById(R.id.stock_name);
            viewHolder.stockNum = (TextView) view.findViewById(R.id.stock_num);
            viewHolder.nowPrice = (TextView) view.findViewById(R.id.now_price);
            viewHolder.nowAdd = (TextView) view.findViewById(R.id.today_add);
            viewHolder.bascPrice = (TextView) view.findViewById(R.id.base_price);
            viewHolder.cangWei = (TextView) view.findViewById(R.id.cangwei_txt);
            viewHolder.fuYing = (TextView) view.findViewById(R.id.fu_ying);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ChiCangInfo info = (ChiCangInfo) getItem(i);
        viewHolder.stockName.setText(info.getStockName());
        viewHolder.stockNum.setText(info.getStockNum());
        viewHolder.nowPrice.setText(info.getNowPrice());
        if (info.getTodayAddDecNum()>0){
            viewHolder.nowAdd.setTextColor(context.getResources().getColor(R.color.red));
        }else if (info.getTodayAddDecNum()<0){
            viewHolder.nowAdd.setTextColor(context.getResources().getColor(R.color.green_color));
        }else {
            viewHolder.nowAdd.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        viewHolder.nowAdd.setText(info.getTodayAdd());
        viewHolder.bascPrice.setText(info.getBascPrice());
        viewHolder.cangWei.setText(info.getCangwei());
        viewHolder.fuYing.setText(info.getFuYing());
        if (info.getFuYingNum()>0){
            viewHolder.fuYing.setTextColor(context.getResources().getColor(R.color.red));
        }else if (info.getFuYingNum()<0){
            viewHolder.fuYing.setTextColor(context.getResources().getColor(R.color.green_color));
        }else {
            viewHolder.fuYing.setTextColor(context.getResources().getColor(android.R.color.black));
        }

        return view;
    }

    private class ViewHolder{
        TextView stockName;
        TextView stockNum;
        TextView nowAdd;
        TextView nowPrice;
        TextView bascPrice;
        TextView cangWei;
        TextView fuYing;
    }
}
