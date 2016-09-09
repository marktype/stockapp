package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewThree;
import com.example.drawer.stockapp.model.CeLueInfo;
import com.example.drawer.stockapp.utils.DensityUtils;

import java.util.ArrayList;
import java.util.HashMap;

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
//            viewHolder.headImage = (ImageView) view.findViewById(R.id.celue_image);
            viewHolder.persent = (TextView) view.findViewById(R.id.celue_persent);
            viewHolder.title = (TextView) view.findViewById(R.id.celue_title);
            viewHolder.content = (TextView) view.findViewById(R.id.celue_content);
            viewHolder.jingzhi = (TextView) view.findViewById(R.id.jingzhi_num);
            viewHolder.maxNum = (TextView) view.findViewById(R.id.max_num);
            viewHolder.rateNum = (TextView) view.findViewById(R.id.rate_num);
//            viewHolder.name = (TextView) view.findViewById(R.id.celue_people_name);
            viewHolder.gengtouTxt = (TextView) view.findViewById(R.id.gengtou_num);
            viewHolder.canvasViewThree = (CanvasViewThree) view.findViewById(R.id.chart1);
            viewHolder.nowGen = (TextView) view.findViewById(R.id.celue_name_other);
            viewHolder.status = (TextView) view.findViewById(R.id.status_celue);
            viewHolder.zhaomuName = (TextView) view.findViewById(R.id.zhaomu_name);
//            viewHolder.layout = (RelativeLayout) view.findViewById(R.id.lianghuacelue_item_relat);
//            viewHolder.zuheName = (TextView) view.findViewById(R.id.lianghuacelue_title);
//            viewHolder.levelImage = (ImageView) view.findViewById(R.id.line_celue_three);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CeLueInfo info = (CeLueInfo) getItem(i);
        if (info.getType() == 1){
            viewHolder.status.setText("运行中");
            viewHolder.nowGen.setVisibility(View.GONE);
            viewHolder.zhaomuName.setText("当前收益");
        }else if (info.getType() == 2){
            viewHolder.status.setText("招募中");
            viewHolder.nowGen.setVisibility(View.VISIBLE);
            viewHolder.zhaomuName.setText("招募进度");
        }else if (info.getType() == 3){
            viewHolder.status.setText("已结束");
            viewHolder.nowGen.setVisibility(View.GONE);
            viewHolder.zhaomuName.setText("实现收益");
        }else {
            viewHolder.status.setText("");
            viewHolder.nowGen.setVisibility(View.GONE);
        }

        viewHolder.persent.setText(info.getCeluePersent()+"%");
        viewHolder.title.setText(info.getTitle());
        viewHolder.content.setText(info.getOtherInfo());
        viewHolder.jingzhi.setText(info.getJingZhiNum()+"");
        viewHolder.maxNum.setText(info.getMaxNum());
        viewHolder.rateNum.setText(info.getRateNum());
        viewHolder.gengtouTxt.setText(info.getMinGengTou());

//        viewHolder.name.setText(info.getName());
//        Picasso.with(context).load(info.getHeadImage()).into(viewHolder.headImage);
//        Picasso.with(context).load(info.getLevelImage()).into(viewHolder.levelImage);
        viewHolder.canvasViewThree.setRadius(DensityUtils.dp2px(context,40));
        setCanvasData(viewHolder.canvasViewThree, Double.parseDouble(info.getCeluePersent()));

        return view;
    }

    private class ViewHolder{
        TextView persent;
        TextView title;
        TextView content;
        TextView jingzhi;
        TextView maxNum;
        TextView rateNum;
        TextView nowGen;
//        ImageView headImage,levelImage;
        TextView zhaomuName;
        TextView gengtouTxt;
        CanvasViewThree canvasViewThree;
        TextView status;
//        RelativeLayout layout;
    }

    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, Object> map;
    //设置历史业绩中的比例和颜色
    public void setCanvasData(CanvasViewThree canvasView, double num) {
        data = new ArrayList<>();
        setDataToView(num + "%", "#DBBD44", (float) (num / 100));
        canvasView.setData(data);
    }

    private void setDataToView(String title, String color, float weight) {
        map = new HashMap<>();
        map.put(CanvasView.TITLE, title);
        map.put(CanvasView.COLOR, Color.parseColor(color));
        map.put(CanvasView.WEIGHT, weight);
        data.add(map);
    }
}
