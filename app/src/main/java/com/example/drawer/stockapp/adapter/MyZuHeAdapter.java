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
import com.example.drawer.stockapp.customview.CustomDialog;
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
            viewHolder.type = (TextView) view.findViewById(R.id.stock_name);
            viewHolder.delete = (TextView) view.findViewById(R.id.zuhe_delete);
            viewHolder.guanzhuNum = (TextView) view.findViewById(R.id.guanzhu_name);
            viewHolder.name = (TextView) view.findViewById(R.id.zuhe_name);
            viewHolder.canvasViewThree = (CanvasViewThree) view.findViewById(R.id.zuhe_canvas);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final NiuRenInfo info = (NiuRenInfo) getItem(i);
        if (info.getShouyiRate()>0){
            viewHolder.rate.setText("+"+info.getShouyiRate()+"%");
        }else {
            viewHolder.rate.setText(info.getShouyiRate()+"%");
        }
        viewHolder.type.setText(info.getStockType());
        viewHolder.name.setText(info.getNiurenName());
//        viewHolder.getNum.setText("赚钱"+(i+1)+"号机器");
        viewHolder.guanzhuNum.setText(info.getTradeTime());
        viewHolder.canvasViewThree.setRadius(DensityUtils.dp2px(context,40));
       if (info.getShouyiRate()<100){
           setCanvasData(viewHolder.canvasViewThree,info.getShouyiRate());
       }
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CustomDialog dialog = new CustomDialog(context);
                dialog.setMessageText("确认要删除组合吗？");
                dialog.show();
                dialog.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteCallBack.onDeleteId(info.getId());
                        dialog.dismiss();
                    }
                });
                dialog.setOnNegativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });
        return view;
    }

    private class ViewHolder{
        TextView rate;
        TextView type;
        TextView guanzhuNum;
        TextView name;
        TextView delete;
        CanvasViewThree canvasViewThree;
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
