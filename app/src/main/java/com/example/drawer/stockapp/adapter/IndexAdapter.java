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
    private static final int TYPE_ONE_IMAGE = 1;
    private static final int TYPE_THREE_IMAGE = 2;
    private Context context;
    private ArrayList<NewsInfo> list;
    private LayoutInflater mInflater;
    public IndexAdapter(Context context){
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }
    public void setData(ArrayList<NewsInfo> list){
        this.list = list;
        notifyDataSetChanged();

    }
    public void addData(ArrayList<NewsInfo> list){
        this.list.addAll(list);
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
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ViewHolderTwo viewHolderTwo;
        int type = getItemViewType(i);
        if (type == TYPE_ONE_IMAGE){
            if (view == null){
                view = mInflater.inflate(R.layout.index_item,null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.index_name);   //副标题
                viewHolder.time = (TextView) view.findViewById(R.id.index_num);
                viewHolder.persent = (TextView) view.findViewById(R.id.index_persent);
                viewHolder.headImahe = (ImageView) view.findViewById(R.id.head_zixun_item);
                viewHolder.title = (TextView) view.findViewById(R.id.index_name_title);     //标题
                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            NewsInfo headIndex = (NewsInfo) getItem(i);
            viewHolder.title.setText(headIndex.getTitle());
            viewHolder.name.setText(headIndex.getContent());
            viewHolder.time.setText(headIndex.getTime());
            viewHolder.persent.setText(headIndex.getPeopleNum());
            Picasso.with(context).load(headIndex.getImage()).placeholder(R.mipmap.img_replace).into(viewHolder.headImahe);
        }else if (type == TYPE_THREE_IMAGE){
            if (view == null){
                view = mInflater.inflate(R.layout.zixun_list_three_image_item,null);
                viewHolderTwo = new ViewHolderTwo();
                viewHolderTwo.title = (TextView) view.findViewById(R.id.title_txt);
                viewHolderTwo.xontent = (TextView) view.findViewById(R.id.content_txt);
                viewHolderTwo.oneImage = (ImageView) view.findViewById(R.id.news_image_one);
                viewHolderTwo.twoImage = (ImageView) view.findViewById(R.id.news_image_two);
                viewHolderTwo.threeImage = (ImageView) view.findViewById(R.id.news_image_three);
                viewHolderTwo.commentNum = (TextView) view.findViewById(R.id.index_persent_three);
                viewHolderTwo.timeThree = (TextView) view.findViewById(R.id.index_num_three);
                view.setTag(viewHolderTwo);
            }else {
                viewHolderTwo = (ViewHolderTwo) view.getTag();
            }
            NewsInfo headIndex = (NewsInfo) getItem(i);
            viewHolderTwo.title.setText(headIndex.getTitle());
            viewHolderTwo.xontent.setText(headIndex.getContent());
            viewHolderTwo.timeThree.setText(headIndex.getTime());
            viewHolderTwo.commentNum.setText(headIndex.getPeopleNum());
            Picasso.with(context).load(headIndex.getImgaes().get(0)).placeholder(R.mipmap.img_replace).into(viewHolderTwo.oneImage);
            Picasso.with(context).load(headIndex.getImgaes().get(1)).placeholder(R.mipmap.img_replace).into(viewHolderTwo.twoImage);
            Picasso.with(context).load(headIndex.getImgaes().get(2)).placeholder(R.mipmap.img_replace).into(viewHolderTwo.threeImage);
        }



        return view;
    }

   private class ViewHolder{
       TextView name;
       TextView time;
       TextView persent;
       ImageView headImahe;
       TextView title;
   }
    private class ViewHolderTwo{
        TextView title;
        TextView xontent;
        TextView timeThree;
        TextView commentNum;
        ImageView oneImage;
        ImageView twoImage;
        ImageView threeImage;
    }
}
