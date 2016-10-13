package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.NiuRenInfo;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/16.
 */
public class NiuRenAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NiuRenInfo> list;
    private DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
    public NiuRenAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<NiuRenInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(ArrayList<NiuRenInfo> list){
        this.list.addAll(list);
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
            view = LayoutInflater.from(context).inflate(R.layout.niurenzuhe_item_layout_two,null);
            viewHolder.head = (ImageView) view.findViewById(R.id.niuiren_head);
            viewHolder.name = (TextView) view.findViewById(R.id.niuren_name);
//            viewHolder.tradeImage = (ImageView) view.findViewById(R.id.graph_image);
//            viewHolder.typeTxt = (TextView) view.findViewById(R.id.type_stock);
            viewHolder.shouyiAll = (TextView) view.findViewById(R.id.shouyi_rate);
            viewHolder.victor = (TextView) view.findViewById(R.id.victor_rate);
            viewHolder.shouyiBymonth = (TextView) view.findViewById(R.id.shouyi_every_month);
            viewHolder.stockNum = (TextView) view.findViewById(R.id.have_stock);
            viewHolder.cangwei = (TextView) view.findViewById(R.id.cangwei);
            viewHolder.layoutTitle = (RelativeLayout) view.findViewById(R.id.niuren_relat_item);
//            viewHolder.roundTime = (TextView) view.findViewById(R.id.round_every);
//            viewHolder.tradeTime = (TextView) view.findViewById(R.id.trade_time);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NiuRenInfo info = (NiuRenInfo) getItem(i);
        Picasso.with(context).load(info.getNiurenHead()).placeholder(R.mipmap.usericon).into(viewHolder.head);
        viewHolder.name.setText(info.getNiurenName());
        viewHolder.layoutTitle.setBackgroundColor(context.getResources().getColor(R.color.write_color));
//        Picasso.with(context).load(info.getNiurenRoundImage()).into(viewHolder.tradeImage);
//        viewHolder.typeTxt.setText(info.getStockType());
        if (info.getShouyiRate() == 0){
            viewHolder.shouyiAll.setText("0.00%");
            viewHolder.shouyiAll.setTextColor(context.getResources().getColor(android.R.color.black));
        }else if (info.getShouyiRate() >0){
            viewHolder.shouyiAll.setText(info.getShouyiRate()+"%");
            viewHolder.shouyiAll.setTextColor(context.getResources().getColor(R.color.red));
        }else {
            viewHolder.shouyiAll.setTextColor(context.getResources().getColor(R.color.green_color));
            viewHolder.shouyiAll.setText(info.getShouyiRate()+"%");
        }
        viewHolder.victor.setText(info.getVictorRate());
        double month = Double.parseDouble(df.format(info.getShouyiByMonth()));
        if (month>0){
            viewHolder.shouyiBymonth.setText(month+"%");
            viewHolder.shouyiBymonth.setTextColor(context.getResources().getColor(R.color.red));
        }else if (month<0){
            viewHolder.shouyiBymonth.setText(month+"%");
            viewHolder.shouyiBymonth.setTextColor(context.getResources().getColor(R.color.green_color));
        }else {
            viewHolder.shouyiBymonth.setText("0.00%");
            viewHolder.shouyiBymonth.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        viewHolder.stockNum.setText(info.getStockNum()+"");
        viewHolder.cangwei.setText(info.getCangweiRate());
//        viewHolder.roundTime.setText(info.getDayNum()+"");
//        viewHolder.tradeTime.setText(info.getTradeTime());

        return view;
    }

    private class ViewHolder{
        ImageView head;
        TextView name,shouyiAll;
        TextView typeTxt,victor,shouyiBymonth,stockNum,cangwei,roundTime,tradeTime;
        RelativeLayout layoutTitle;
    }
}
