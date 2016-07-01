package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.chartview.MyMarkerView;
import com.example.drawer.stockapp.fragment.AutoWisdomFragment;
import com.example.drawer.stockapp.model.StockBean;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 牛人组合和我的组合详情
 */
public class CelueDatilActivity extends BascActivity implements View.OnClickListener{
    private RadarChart mChart;
    private ArrayList<StockBean> list;
    private CanvasView canvasView;
    private HashMap<String, Object> map;
    private ArrayList<HashMap<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celue_datil);
        initWight();
    }

    public void initWight(){
        String  title = getIntent().getStringExtra(AutoWisdomFragment.CELUENAME);
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        TextView mTitle = (TextView) findViewById(R.id.back_txt);
        TextView mGoOrder = (TextView) findViewById(R.id.order_txt);
        TextView mPersent = (TextView) findViewById(R.id.rank_parsent);    //百分比字体设置
        TextView mTimes = (TextView) findViewById(R.id.rank_times);      //倍数设置


        mChart = (RadarChart) findViewById(R.id.chart1);
        canvasView = (CanvasView) findViewById(R.id.canvas_view);
        canvasView.setRadius(220f);    //设置图形半径

        mTitle.setText(title);

        setChartData();
        setCanvasData();

        mBackimg.setOnClickListener(this);
        mGoOrder.setOnClickListener(this);
    }

    public void setCanvasData(){
        data = new ArrayList<>();
        setDataToView("医药生物", "#DBBD44", 0.1f);
        setDataToView("农林牧渔", "#2AB98F", 0.15f);
        setDataToView("电器设备", "#DBBD44", 0.15f);
        setDataToView("公用事业", "#0054AB", 0.5f);
        setDataToView("食品饮料", "#E95859", 0.1f);
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
     * 设置饼图数据
     */
    public void setChartData(){
        /**
         * 用来描述该雷达图是什么用途
         */
        mChart.setDescription("雷达图描述");

        mChart.setWebLineWidth(1.5f);
        mChart.setWebLineWidthInner(0.75f);
        mChart.setWebAlpha(100);
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

        // set the marker to the chart
        mChart.setMarkerView(mv);

        setData();

        mChart.animateXY(1000, 1000,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinValue(0f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
    }

    public void setData() {

        float mult = 150;
        /**
         * 这个cnt就是控制显示几个数量的
         */
        int cnt = 10;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
//        for (int i = 0; i < cnt; i++) {
//            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
//        }

//        for (int i = 0; i < cnt; i++) {
//            yVals2.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
//        }
        /**
         * 这里讲list里面的值赋给yvals2
         */
        list = new ArrayList<>();
        list.add(new StockBean(113, "盈利能力"));
        list.add(new StockBean(124, "抗风险能力"));
        list.add(new StockBean(143, "稳定性"));
        list.add(new StockBean(243, "持股分散度"));
        list.add(new StockBean(432, "可复制性"));

//        list.add(222);
//        list.add(333);
        for (int i = 0; i < list.size(); i++) {
            yVals2.add(new Entry(list.get(i).getPercent(), i));
        }
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++)
            xVals.add(list.get(i % list.size()).getName());

        RadarDataSet set1 = new RadarDataSet(yVals1, "Set 1");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setFillColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setDrawHighlightCircleEnabled(true);
        // 显示中间区域颜色
        set2.setDrawFilled(true);
        //线条的跨度
        set2.setLineWidth(2f);

        //
        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(xVals, sets);
        data.setValueTextSize(18f);
        data.setDrawValues(false);
        data.setHighlightEnabled(true);

        mChart.setData(data);

        mChart.invalidate();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.order_txt:
                Intent intent = new Intent(this,PayActivity.class);
                startActivity(intent);
                break;
        }
    }
}
