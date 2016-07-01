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
 * Created by 欢大哥 on 2016/6/15.
 */
public class IndexAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NewsInfo> list ;
    public IndexAdapter(Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.index_item,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.index_name);
            viewHolder.time = (TextView) view.findViewById(R.id.index_num);
            viewHolder.persent = (TextView) view.findViewById(R.id.index_persent);
            viewHolder.headImahe = (ImageView) view.findViewById(R.id.head_zixun_item);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NewsInfo headIndex = (NewsInfo) getItem(i);
        viewHolder.name.setText(headIndex.getTitle());
        viewHolder.time.setText(headIndex.getTime());
        viewHolder.persent.setText(headIndex.getPeopleNum());
        Picasso.with(context).load(headIndex.getImage()).into(viewHolder.headImahe);

        return view;
    }

   private class ViewHolder{
       TextView name;
       TextView time;
       TextView persent;
       ImageView headImahe;
   }
}
