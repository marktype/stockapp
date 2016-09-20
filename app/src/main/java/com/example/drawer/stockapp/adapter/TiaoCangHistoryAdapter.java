package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.model.HistoryTiaoCangInfo;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/9/19.
 */
public class TiaoCangHistoryAdapter extends BaseAdapter {

    private ArrayList<HistoryTiaoCangInfo> list;
    private Context context;
    public TiaoCangHistoryAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<HistoryTiaoCangInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.tiaocang_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.tiaocang_ttx);
            viewHolder.time = (TextView) view.findViewById(R.id.last_time);
            viewHolder.listView = (MyListView) view.findViewById(R.id.tiaocang_listview);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HistoryTiaoCangInfo historyTiaoCangInfo = (HistoryTiaoCangInfo) getItem(i);
        viewHolder.title.setText("调仓时间");
        viewHolder.time.setText("("+historyTiaoCangInfo.getTime()+")");
        TiaoCangAdapter adapter = new TiaoCangAdapter(context);
        adapter.setData(historyTiaoCangInfo.getList());
        viewHolder.listView.setAdapter(adapter);

        return view;
    }


    private class ViewHolder{
        TextView title;
        TextView time;
        MyListView listView;
    }
}
