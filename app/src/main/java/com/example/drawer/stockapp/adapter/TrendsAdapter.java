package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/15.
 */
public class TrendsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TrendsInfo> list;
    public TrendsAdapter(Context context){
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
        ImageAdapter adapter = null;
        if (view == null){
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.dongtai_item_layout,null);
            viewHolder.head = (ImageView) view.findViewById(R.id.dongtai_image);
            viewHolder.name = (TextView) view.findViewById(R.id.dongtai_name);
            viewHolder.content = (TextView) view.findViewById(R.id.dongtai_content);
            viewHolder.contentImage = (MyGridView) view.findViewById(R.id.dongtai_cntent_image);
            viewHolder.time = (TextView) view.findViewById(R.id.dongtai_time);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TrendsInfo info = (TrendsInfo) getItem(i);
        if (!TextUtils.isEmpty(info.getImage())){
            Picasso.with(context).load(info.getImage()).into(viewHolder.head);
        }

            adapter = new ImageAdapter(context);
        adapter.setData(info.getContentImage());
        viewHolder.contentImage.setAdapter(adapter);
        viewHolder.name.setText(info.getName());
        viewHolder.content.setText(info.getContent());
        viewHolder.time.setText(info.getTime());
        return view;
    }

    private class ViewHolder{
        TextView name;
        ImageView head;
        TextView content;
        MyGridView contentImage;
        TextView time;
    }
}
