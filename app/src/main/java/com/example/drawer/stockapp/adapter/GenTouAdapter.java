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
public class GenTouAdapter extends BaseAdapter {
    private ArrayList<ChiCangInfo> list;
    private Context context;
    public GenTouAdapter(Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.gentou_item_list,null);
            viewHolder = new ViewHolder();
            viewHolder.num = (TextView) view.findViewById(R.id.now_price);
            viewHolder.people = (TextView) view.findViewById(R.id.today_add);
            viewHolder.price = (TextView) view.findViewById(R.id.base_price);
            viewHolder.time = (TextView) view.findViewById(R.id.cangwei_txt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ChiCangInfo info = (ChiCangInfo) getItem(i);
        viewHolder.num.setText(info.getTodayAdd());
        viewHolder.people.setText(info.getNowPrice());
        viewHolder.price.setText(info.getBascPrice());
        viewHolder.time.setText(info.getCangwei());

        return view;
    }


    private class ViewHolder{
        TextView num;
        TextView people;
        TextView price;
        TextView time;
    }
}
