package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.HeadIndex;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/20.
 */
public class HeJiAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HeadIndex> list ;
    public HeJiAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<HeadIndex> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(ArrayList<HeadIndex> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.heji_item_layout,null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.heji_item_image);
//            viewHolder.txtClass = (TextView) view.findViewById(R.id.heji_class_name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HeadIndex info = (HeadIndex) getItem(i);
        Picasso.with(context).load(info.getIndexImage()).placeholder(R.mipmap.img_replace).into(viewHolder.imageView);
//        viewHolder.txtClass.setText(info.getIndexName());

        return view;
    }

    private class ViewHolder{
        ImageView imageView;
//        TextView txtClass;
    }
}
