package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 欢大哥 on 2016/8/5.
 */
public class AttentionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TrendsInfo> list;
    public AttentionAdapter(Context context){
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
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.attention_item,null);
            viewHolder.circleImageView = (CircleImageView) view.findViewById(R.id.hand_img);
            viewHolder.textView = (TextView) view.findViewById(R.id.name_txt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TrendsInfo info = (TrendsInfo) getItem(i);

        Picasso.with(context).load(info.getImage()).into(viewHolder.circleImageView);
        viewHolder.textView.setText(info.getName());

        return view;
    }
    private class ViewHolder{
        CircleImageView circleImageView;
        TextView textView;
    }
}
