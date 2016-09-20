package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.TiaoCangInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/28.
 */
public class TiaoCangAdapter extends BaseAdapter {

    private ArrayList<TiaoCangInfo> list ;
    private Context context;
    public TiaoCangAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<TiaoCangInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.tiaocang_item_list,null);
            viewHolder = new ViewHolder();
            viewHolder.buyTxt = (TextView) view.findViewById(R.id.mairu_txt);
            viewHolder.name = (TextView) view.findViewById(R.id.stock_name);
            viewHolder.num = (TextView) view.findViewById(R.id.stock_num);
            viewHolder.tradeNum = (TextView) view.findViewById(R.id.trade_num);
            viewHolder.price = (TextView) view.findViewById(R.id.chengjiao_price);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TiaoCangInfo info = (TiaoCangInfo) getItem(i);
        if (info.getBuyCome()){
            viewHolder.buyTxt.setText("买入");
            viewHolder.buyTxt.setBackground(context.getResources().getDrawable(R.drawable.rect_edit_bg_shape));
        }else {
            viewHolder.buyTxt.setText("卖出");
            viewHolder.buyTxt.setBackground(context.getResources().getDrawable(R.drawable.rect_write_black_shape));
        }

        viewHolder.name.setText(info.getStockName());
        viewHolder.num.setText("("+info.getStockNum()+")");
        viewHolder.tradeNum.setText(""+(info.getTradeNumEnd()-info.getTradeNumStart()));
        viewHolder.price.setText(info.getTradePrice());
        return view;
    }

    private class ViewHolder{
        TextView buyTxt;
        TextView name;
        TextView num;
        TextView tradeNum;
        TextView price;
    }
}
