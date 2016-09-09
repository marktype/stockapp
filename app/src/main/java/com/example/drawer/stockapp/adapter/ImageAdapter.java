package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.ShowImageActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class ImageAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> list;
    public ImageAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<String> list){
        this.list = list;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
        view = LayoutInflater.from(context).inflate(R.layout.item_image_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.head = (ImageView) view.findViewById(R.id.image_item_layout);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String str = (String) getItem(i);
        if (!TextUtils.isEmpty(str))
        Picasso.with(context).load(str).placeholder(R.mipmap.img_replace).resize(180,180).into(viewHolder.head);
        viewHolder.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putStringArrayListExtra(ShowImageActivity.SHOWIMAGE, list);
                intent.putExtra(ShowImageActivity.CURRENT_POSITION, i);
                context.startActivity(intent);
            }
        });

        return view;
    }

    private class ViewHolder{
        ImageView head;
    }
}
