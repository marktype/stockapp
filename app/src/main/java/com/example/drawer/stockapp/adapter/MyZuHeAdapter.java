package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.NiuRenInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/16.
 */
public class MyZuHeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NiuRenInfo> list;
    public MyZuHeAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<NiuRenInfo> list){
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
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.my_zuhe_item_layout,null);
            viewHolder.rate = (TextView) view.findViewById(R.id.zuhe_persent);
            viewHolder.type = (TextView) view.findViewById(R.id.stock_name);
            viewHolder.getNum = (TextView) view.findViewById(R.id.jiqi_name);
            viewHolder.guanzhuNum = (TextView) view.findViewById(R.id.guanzhu_name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NiuRenInfo info = (NiuRenInfo) getItem(i);
        viewHolder.rate.setText(info.getShouyiRate());
        viewHolder.type.setText(info.getStockType());
        viewHolder.getNum.setText("赚钱"+(i+1)+"号机器");
        viewHolder.guanzhuNum.setText(info.getTradeTime());
        return view;
    }

    private class ViewHolder{
        TextView rate;
        TextView type;
        TextView getNum;
        TextView guanzhuNum;
    }
}
