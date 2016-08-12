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

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/11.
 */
public class SetUpZuHeAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<HeadIndex> list ;
    public SetUpZuHeAdapter(Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.set_up_item_layout,null);
            viewHolder.name = (TextView) view.findViewById(R.id.culue_name_txt);
            viewHolder.number = (TextView) view.findViewById(R.id.number);
            viewHolder.num = (TextView) view.findViewById(R.id.edit_txt);
            viewHolder.delete = (ImageView) view.findViewById(R.id.image_delete);
            viewHolder.decImg = (ImageView) view.findViewById(R.id.image_dec);
            viewHolder.addImg = (ImageView) view.findViewById(R.id.image_add);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HeadIndex index = (HeadIndex) getItem(i);
        viewHolder.name.setText(index.getIndexName());
        viewHolder.number.setText(index.getIndexNum());
        viewHolder.num.setText(index.getIndexPersent());

        return view;
    }

    private class ViewHolder{
        TextView name;
        TextView number;
        ImageView delete;
        ImageView decImg;
        ImageView addImg;
        TextView num;
    }
}
