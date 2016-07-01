package com.example.drawer.stockapp.customview.chartview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.WindowManager;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.model.StockBean;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {
    private PieChart mChart;
    private ArrayList<StockBean> dataList;

    /**
     * 设置数据
     */
    private void setDataToChart() {
        dataList = new ArrayList<>();
        dataList.add(new StockBean(3, "股票走势"));
        dataList.add(new StockBean(4, "今日头条"));
        dataList.add(new StockBean(3, "腾讯新闻"));
        dataList.add(new StockBean(5, "网页新闻"));
        dataList.add(new StockBean(2, "搜狐新闻"));
        dataList.add(new StockBean(1, "华西都市报"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pie);
        initChart();
        setDataToChart();
        // add a selection listener
        /**
         * 10是最多有10个块，100是总的比例
         */
        setData(20, 100);
    }


    private void initChart() {
        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.setDescription("饼状图");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);
/**
 * 设置中间显示的文字
 */
        mChart.setCenterText(generateCenterSpannableText());
/**
 * 设置中间的圈圈是否显示
 */
        mChart.setDrawHoleEnabled(true);
        /**
         *中间l圈圈的颜色
         */
        mChart.setHoleColor(Color.WHITE);
/**
 * 中间圈圈四周的那个颜色
 */
        mChart.setTransparentCircleColor(Color.WHITE);
        //那个四周的alpha值
        mChart.setTransparentCircleAlpha(10);

        /**
         * 中间圆圈的半径
         */
        mChart.setHoleRadius(50f);
        mChart.setTransparentCircleRadius(61f);
/**
 * 绘制圈圈中间的文字
 */
        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }


    private void setData(int count, float range) {
        float mult = range;
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
//        for (int i = 0; i < count + 1; i++) {
//            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
//        }
        /**
         * 这里讲list里面的值赋给yvals
         */
        for (int i = 0; i < dataList.size(); i++) {
            yVals1.add(new Entry(dataList.get(i).getPercent(), i));
        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count + 1; i++) {
            xVals.add(dataList.get(i % dataList.size()).getName());
        }
        /**
         *
         */
        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        // add a lot of colors
        /**
         * 显示饼状图的颜色list
         */
        ArrayList<Integer> colors = new ArrayList<Integer>();
//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        /**
         * 点击了饼状图以后  突出去的距离
         */
        dataSet.setSelectionShift(10f);
        /**
         * 字体的设置
         */
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        //字体大小
        data.setValueTextSize(12f);
        //字体颜色
        data.setValueTextColor(Color.WHITE);
        //
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("股票软件——杨杰");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 4, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 3, s.length() - 3, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 6, s.length(), 0);
        s.setSpan(new RelativeSizeSpan(.8f), 3, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 3, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 3, s.length(), 0);
        return s;
    }
}
