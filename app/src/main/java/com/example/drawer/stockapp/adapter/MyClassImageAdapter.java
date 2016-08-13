package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.drawer.stockapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/8/12.
 */
public class MyClassImageAdapter extends BaseAdapter {
    private ArrayList<String> list;
    private Context context;
    public MyClassImageAdapter(Context context){
        this.context = context;

    }

    public void setData(ArrayList<String> list){
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
        if (view == null)
        {view = LayoutInflater.from(context).inflate(R.layout.image_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.my_class_img_txt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String str = (String) getItem(i);
        if (!TextUtils.isEmpty(str)){
            Picasso.with(context).load(str).resize(getWindowWight(),200).into(viewHolder.imageView);
        }

        return view;
    }


    private class ViewHolder{
        ImageView imageView;
    }


    /**
     * 获取屏幕宽度
     * @return
     */
    public int getWindowWight(){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        return width;

    }
}
