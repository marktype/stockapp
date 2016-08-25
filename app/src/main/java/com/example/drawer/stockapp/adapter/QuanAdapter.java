package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.QuanInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/25.
 */
public class QuanAdapter extends BaseAdapter {

    private ArrayList<QuanInfo> list;
    private Context context;
    public QuanAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<QuanInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.quan_iten_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.money = (TextView) view.findViewById(R.id.money_txt);
            viewHolder.canUsed = (TextView) view.findViewById(R.id.can_use_money);
            viewHolder.whoUsed = (TextView) view.findViewById(R.id.who_use_money);
            viewHolder.timeUsed = (TextView) view.findViewById(R.id.time_use_money);
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.quan_lin);
            viewHolder.yuan = (TextView) view.findViewById(R.id.yuan_txt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        QuanInfo info = (QuanInfo) getItem(i);

        if (!info.getTimeOver()){
            viewHolder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.quan_bg_on_shape));
            viewHolder.money.setTextColor(context.getResources().getColor(R.color.quan_on_txt));
            viewHolder.canUsed.setTextColor(context.getResources().getColor(R.color.quan_on_txt));
            viewHolder.whoUsed.setTextColor(context.getResources().getColor(R.color.quan_on_txt));
            viewHolder.timeUsed.setTextColor(context.getResources().getColor(R.color.quan_on_txt));
            viewHolder.yuan.setTextColor(context.getResources().getColor(R.color.quan_on_txt));

            }else {
            viewHolder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.quan_bg_off_shape));
            viewHolder.money.setTextColor(context.getResources().getColor(R.color.quan_off_txt));
            viewHolder.canUsed.setTextColor(context.getResources().getColor(R.color.quan_off_txt));
            viewHolder.whoUsed.setTextColor(context.getResources().getColor(R.color.quan_off_txt));
            viewHolder.timeUsed.setTextColor(context.getResources().getColor(R.color.quan_off_txt));
            viewHolder.yuan.setTextColor(context.getResources().getColor(R.color.quan_off_txt));
        }
        viewHolder.money.setText(info.getMoney());
        viewHolder.canUsed.setText(info.getCanUsed());
        viewHolder.whoUsed.setText(info.getWhoUsed());
        viewHolder.timeUsed.setText(info.getTimeUsed());


        return view;
    }

    private class ViewHolder{
        TextView money;
        TextView canUsed;
        TextView whoUsed;
        TextView timeUsed;
        LinearLayout linearLayout;
        TextView yuan;
    }
}
