package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.ReturnAdapter;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewTwo;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.fragment.MyListFragment;
import com.example.drawer.stockapp.model.NewsInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 策略组合详情
 */
public class DetilCelueActivity extends BascActivity implements View.OnClickListener{
    private HashMap<String, Object> map;
    private ArrayList<HashMap<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_celue);
        initWeight();
    }

    public void initWeight(){
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        TextView mPay = (TextView) findViewById(R.id.go_to_pay);

        CanvasViewTwo mChartOne = (CanvasViewTwo) findViewById(R.id.chart1);
        CanvasViewTwo mChartTwo = (CanvasViewTwo) findViewById(R.id.chart2);
        CanvasViewTwo mChartThree = (CanvasViewTwo) findViewById(R.id.chart3);
        setCanvasData(mChartOne);
        setCanvasData(mChartTwo);
        setCanvasData(mChartThree);

        RadioGroup mGroup = (RadioGroup) findViewById(R.id.wisdom_group);

        //退款保障
        MyListView myListView = (MyListView) findViewById(R.id.my_listview_refund);
        ReturnAdapter adapter = new ReturnAdapter(this);
        adapter.setData(setReturnData());
        myListView.setAdapter(adapter);

        //调仓页面（第一次进入时加载）
        MyListFragment fragment = MyListFragment.newInstance("调仓","");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_layout,fragment);
        transaction.commit();

        mBackimg.setOnClickListener(this);
        mGroup.setOnCheckedChangeListener(new RadioGroupListener());
        mPay.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.go_to_pay:
                Intent intent = new Intent(this,FollowActivity.class);
                startActivity(intent);
                break;
        }
    }
    //设置历史业绩中的比例和颜色
    public void setCanvasData( CanvasViewTwo canvasView){
        data = new ArrayList<>();
        setDataToView("30%", "#DBBD44", 0.3f);
        canvasView.setData(data);
    }
    private void setDataToView(String title, String color, float weight) {
        map = new HashMap<>();
        map.put(CanvasView.TITLE, title);
        map.put(CanvasView.COLOR, Color.parseColor(color));
        map.put(CanvasView.WEIGHT, weight);
        data.add(map);
    }

    /**
     * 设置退款保障数据
     * @return
     */
    public ArrayList<NewsInfo> setReturnData(){
        ArrayList<NewsInfo> list = new ArrayList<>();
        for (int i = 0;i<4;i++){
            NewsInfo info = new NewsInfo();
            info.setImage("http://h.hiphotos.baidu.com/baike/w%3D268%3Bg%3D0/sign=8a22955c22a446237ecaa264a0191533/3ac79f3df8dcd1005da11707708b4710b9122fd3.jpg");
            info.setTitle("ThinkPHP是为了简化企业级应用开发和敏捷WEB应用开发而诞生的。最早诞生于2006年初，2007年元旦正式更名为ThinkPHP，并且遵循Apache2开源协议发布。");
            list.add(info);
        }
        return list;
    }
    /**
     * 此处建立了4个fragment，但后来调用不需要也能实现，若是需求中都是用的listview列表形式，则可以删除其余3个fragment
     * 一个fragment中的listview设置不同的适配器
     */
    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (i) {
                case R.id.celue_txt:
                    MyListFragment fragment = MyListFragment.newInstance("调仓","");
                    transaction.replace(R.id.fragment_layout,fragment);
                    break;
                case R.id.zuhe_txt:
//                    MyListFragmentTwo fragmentTwo = MyListFragmentTwo.newInstance("持仓","");
//                    transaction.replace(R.id.fragment_layout,fragmentTwo);
                    fragment = MyListFragment.newInstance("持仓","");
                    transaction.replace(R.id.fragment_layout,fragment);
                    break;
                case R.id.my_celue_txt:
//                    MyListFragmentThree fragmentThree = MyListFragmentThree.newInstance("跟投人","");
//                    transaction.replace(R.id.fragment_layout,fragmentThree);
                    fragment = MyListFragment.newInstance("跟投人","");
                    transaction.replace(R.id.fragment_layout,fragment);
                    break;
                case R.id.my_zuhe_txt:
//                    MyListFragmentFour fragmentFour = MyListFragmentFour.newInstance("交流区","");
//                    transaction.replace(R.id.fragment_layout,fragmentFour);
                    fragment = MyListFragment.newInstance("交流区","");
                    transaction.replace(R.id.fragment_layout,fragment);
                    break;
            }
            transaction.commit();
        }
    }
}
