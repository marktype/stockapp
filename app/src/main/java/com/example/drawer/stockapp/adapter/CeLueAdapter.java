package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.CeLueInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/6/15.
 */
public class CeLueAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CeLueInfo> list;
    public CeLueAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<CeLueInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.lianghuacelue_item_layout,null);
            viewHolder.headImage = (ImageView) view.findViewById(R.id.celue_image);
            viewHolder.persent = (TextView) view.findViewById(R.id.celue_persent);
            viewHolder.title = (TextView) view.findViewById(R.id.celue_title);
            viewHolder.content = (TextView) view.findViewById(R.id.celue_content);
            viewHolder.jingzhi = (TextView) view.findViewById(R.id.jingzhi_num);
            viewHolder.maxNum = (TextView) view.findViewById(R.id.max_num);
            viewHolder.rateNum = (TextView) view.findViewById(R.id.rate_num);
            viewHolder.name = (TextView) view.findViewById(R.id.celue_people_name);
            viewHolder.gengtouTxt = (TextView) view.findViewById(R.id.gengtou_num);
            viewHolder.layout = (RelativeLayout) view.findViewById(R.id.lianghuacelue_item_relat);
            viewHolder.zuheName = (TextView) view.findViewById(R.id.lianghuacelue_title);
            viewHolder.levelImage = (ImageView) view.findViewById(R.id.line_celue_three);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CeLueInfo info = (CeLueInfo) getItem(i);
        if (info.getType() == 1){
            viewHolder.zuheName.setText("量化策略组合");
        }else {
            viewHolder.layout.setVisibility(View.GONE);
        }

        viewHolder.persent.setText(info.getCeluePersent());
        viewHolder.title.setText(info.getTitle());
        viewHolder.content.setText(info.getOtherInfo());
        viewHolder.jingzhi.setText(info.getJingZhiNum()+"");
        viewHolder.maxNum.setText(info.getMaxNum());
        viewHolder.rateNum.setText(info.getRateNum());
        viewHolder.name.setText(info.getName());
        viewHolder.gengtouTxt.setText(info.getMinGengTou());
        Picasso.with(context).load(info.getHeadImage()).into(viewHolder.headImage);
        Picasso.with(context).load(info.getLevelImage()).into(viewHolder.levelImage);

        return view;
    }

    private class ViewHolder{
        TextView persent;
        TextView title;
        TextView content;
        TextView jingzhi;
        TextView maxNum;
        TextView rateNum;
        ImageView headImage,levelImage;
        TextView name;
        TextView gengtouTxt;
        TextView zuheName;
        RelativeLayout layout;
    }
}
