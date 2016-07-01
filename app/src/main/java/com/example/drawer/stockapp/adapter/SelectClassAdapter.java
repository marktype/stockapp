package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.HeadIndex;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/17.
 */
public class SelectClassAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HeadIndex> list ;
    public SelectClassAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<HeadIndex> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.jingxuan_class_two_item_layout,null);
            viewHolder.name = (TextView) view.findViewById(R.id.title_name);
            viewHolder.image = (ImageView) view.findViewById(R.id.jingxuan_class_img);
            viewHolder.persent = (TextView) view.findViewById(R.id.pinglun_num);    //评论人数
            viewHolder.time = (TextView) view.findViewById(R.id.dingzhi_num);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HeadIndex info = (HeadIndex) getItem(i);
        Picasso.with(context).load(info.getIndexImage()).into(viewHolder.image);
        viewHolder.name.setText(info.getIndexName());
        viewHolder.persent.setText(info.getIndexPersent());
        viewHolder.time.setText(info.getIndexNum());

        return view;
    }

    private class ViewHolder{
        TextView name;
        TextView time;
        TextView persent;
        ImageView image;
    }
}
