package com.example.drawer.stockapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewThree;
import com.example.drawer.stockapp.listener.DeleteCallBack;
import com.example.drawer.stockapp.model.NiuRenInfo;
import com.example.drawer.stockapp.utils.DensityUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 欢大哥 on 2016/6/16.
 */
public class MyZuHeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NiuRenInfo> list;
    private DeleteCallBack deleteCallBack;
    public MyZuHeAdapter(Context context){
        this.context = context;
    }
    public void setData(ArrayList<NiuRenInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnClickDeleteListener(DeleteCallBack deleteCallBack){
        this.deleteCallBack = deleteCallBack;
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
            view = LayoutInflater.from(context).inflate(R.layout.my_zuhe_item_layout,null);
            viewHolder.rate = (TextView) view.findViewById(R.id.zuhe_persent);
            viewHolder.guanzhuNum = (TextView) view.findViewById(R.id.guanzhu_name);
            viewHolder.name = (TextView) view.findViewById(R.id.zuhe_name);
            viewHolder.canvasViewThree = (CanvasViewThree) view.findViewById(R.id.zuhe_canvas);
            viewHolder.contentBg = (RelativeLayout) view.findViewById(R.id.my_zuhe_relat_item);
            viewHolder.image = (ImageView) view.findViewById(R.id.difference_zuhe);
            viewHolder.status = (TextView) view.findViewById(R.id.status_name);   //跟投状态
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final NiuRenInfo info = (NiuRenInfo) getItem(i);
        viewHolder.contentBg.setBackgroundColor(context.getResources().getColor(R.color.write_color));
        if (info.getShouyiRate() == 0){
            viewHolder.rate.setText("0.00%");
            setCanvasDataGreen(viewHolder.canvasViewThree,info.getShouyiRate());
        }else if (info.getShouyiRate() > 0){
            if (info.getShouyiRate()<100){
                setCanvasDataRed(viewHolder.canvasViewThree,info.getShouyiRate());
            }else {
                setCanvasDataRed(viewHolder.canvasViewThree,100);
            }
            viewHolder.rate.setTextColor(context.getResources().getColor(R.color.red));
            viewHolder.rate.setText("+"+info.getShouyiRate()+"%");
        }else if (info.getShouyiRate()<0){
            setCanvasDataGreen(viewHolder.canvasViewThree,info.getShouyiRate());
            viewHolder.rate.setTextColor(context.getResources().getColor(R.color.green_color));
            viewHolder.rate.setText(info.getShouyiRate()+"%");
        }
        viewHolder.name.setText(info.getNiurenName());
        if (info.getZuheType() == 1){
            viewHolder.guanzhuNum.setText(info.getStockType());
            viewHolder.image.setImageResource(R.mipmap.gengtou);
            viewHolder.status.setVisibility(View.VISIBLE);
            if (info.getType() == 0){   //招募中
                viewHolder.status.setText("招募中");
            }else if (info.getType() == 1){   //运行中
                viewHolder.status.setText("运行中");
            }else {   //已结束
                viewHolder.status.setText("已结束");
            }
        }else if (info.getZuheType() == 2){
            viewHolder.guanzhuNum.setText(info.getStockType());
            viewHolder.image.setImageResource(R.mipmap.chuangjian);
            viewHolder.status.setVisibility(View.GONE);
        }else {
//            viewHolder.guanzhuNum.setText("订阅人数:"+info.getTradeTime());
            viewHolder.guanzhuNum.setText(info.getStockType());
            viewHolder.image.setImageResource(R.mipmap.dingyue);
            viewHolder.status.setVisibility(View.GONE);
        }
        viewHolder.canvasViewThree.setRadius(DensityUtils.dp2px(context,40));

//        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final CustomDialog dialog = new CustomDialog(context);
//                dialog.setMessageText("确认要删除组合吗？");
//                dialog.show();
//                dialog.setOnPositiveListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        deleteCallBack.onDeleteId(info.getId());
//                        dialog.dismiss();
//                    }
//                });
//                dialog.setOnNegativeListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//
//            }
//        });
        return view;
    }

    private class ViewHolder{
        TextView rate;
        TextView guanzhuNum;
        TextView name;
        CanvasViewThree canvasViewThree;
        RelativeLayout contentBg;
        ImageView image;
        TextView status;
    }

    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, Object> map;
    //设置历史业绩中的比例和颜色
    public void setCanvasDataRed(CanvasViewThree canvasView, double num) {
        data = new ArrayList<>();
        setDataToView(num + "%", "#E53739", (float) (num / 100));
        canvasView.setData(data);
    }

    public void setCanvasDataGreen(CanvasViewThree canvasView, double num) {
        data = new ArrayList<>();
        setDataToView(num + "%", "#49A854", (float) (num / 100));
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
