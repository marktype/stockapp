package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.HeadIndex;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/19.
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HeadIndex> list;
    public SearchAdapter(Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.search_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.search_name);
            viewHolder.num = (TextView) view.findViewById(R.id.search_num);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HeadIndex index = (HeadIndex) getItem(i);
        viewHolder.name.setText(index.getIndexName());
        viewHolder.num.setText(index.getIndexNum());

        return view;
    }


    private class ViewHolder{
        TextView name;    //名字
        TextView num;     //编号
    }
}
