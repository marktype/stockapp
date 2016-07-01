package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.NewsInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/30.
 */
public class ReturnAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NewsInfo> list;
    public ReturnAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<NewsInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.return_money_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.headImage = (ImageView) view.findViewById(R.id.return_money_image);
            viewHolder.content = (TextView) view.findViewById(R.id.return_money_txt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NewsInfo info = (NewsInfo) getItem(i);
        Picasso.with(context).load(info.getImage()).into(viewHolder.headImage);
        viewHolder.content.setText(info.getTitle());

        return view;
    }

    private class ViewHolder{
        ImageView headImage;
        TextView content;
    }
}
