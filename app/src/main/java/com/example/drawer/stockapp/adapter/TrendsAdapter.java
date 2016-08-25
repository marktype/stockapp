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
import com.example.drawer.stockapp.listener.TypeCallBack;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/15.
 * 动态item
 */
public class TrendsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TrendsInfo> list;
    private TypeCallBack callBack;
    public TrendsAdapter(Context context,TypeCallBack callBack){
        this.context = context;
        this.callBack = callBack;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        ImageAdapter adapter = null;
        if (view == null){
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.dongtai_item_layout,null);
            viewHolder.head = (ImageView) view.findViewById(R.id.dongtai_image);
            viewHolder.name = (TextView) view.findViewById(R.id.dongtai_name);
            viewHolder.content = (TextView) view.findViewById(R.id.dongtai_content);
            viewHolder.contentImage = (MyGridView) view.findViewById(R.id.dongtai_cntent_image);
            viewHolder.time = (TextView) view.findViewById(R.id.dongtai_time);
            viewHolder.collect = (ImageView) view.findViewById(R.id.collect_info_img);
            viewHolder.zhuanFaNum = (TextView) view.findViewById(R.id.dongtai_zhuanfa);
            viewHolder.commentNum = (TextView) view.findViewById(R.id.dongtai_pinglun);
            viewHolder.goodNum = (TextView) view.findViewById(R.id.dongtai_zan);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final TrendsInfo info = (TrendsInfo) getItem(i);
        if (!TextUtils.isEmpty(info.getImage())){
            Picasso.with(context).load(info.getImage()).into(viewHolder.head);
        }

            adapter = new ImageAdapter(context);
        adapter.setData(info.getContentImage());
        viewHolder.contentImage.setAdapter(adapter);
        viewHolder.name.setText(info.getName());
        viewHolder.content.setText(info.getContent());
        viewHolder.time.setText(info.getTime());
        viewHolder.zhuanFaNum.setText(info.getZhuanFaNum()+"");
        viewHolder.commentNum.setText(info.getCommentNum()+"");
        viewHolder.goodNum.setText(info.getGoodNum()+"");
        if (info.getCollect()){
            viewHolder.collect.setImageResource(R.mipmap.collection_yes);
        }else {
            viewHolder.collect.setImageResource(R.mipmap.collection);
        }

        //收藏
        viewHolder.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.getCollect()){
                    viewHolder.collect.setImageResource(R.mipmap.collection);
                    info.setCollect(false);
                }else {
                    viewHolder.collect.setImageResource(R.mipmap.collection_yes);
                    info.setCollect(true);
                }
            }
        });
        //转发
        viewHolder.zhuanFaNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.setDongTaiType(i,2);
            }
        });
        //评论
        viewHolder.commentNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.setDongTaiType(i,1);
            }
        });
        //点赞
        viewHolder.goodNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,"点赞",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private class ViewHolder{
        TextView name;
        ImageView head;
        TextView content;
        MyGridView contentImage;
        TextView time;
        TextView zhuanFaNum;
        TextView commentNum;
        TextView goodNum;
        ImageView collect;
    }


}
