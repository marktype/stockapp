package com.example.drawer.stockapp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.MyZuHeItemAdapter;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.customview.MyScrollView;
import com.example.drawer.stockapp.customview.chartview.MyMarkerView;
import com.example.drawer.stockapp.fragment.AutoWisdomFragment;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.ChartInfo;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.StarDetailInfo;
import com.example.drawer.stockapp.model.StockBean;
import com.example.drawer.stockapp.model.TiaoCangClass;
import com.example.drawer.stockapp.model.TiaoCangInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
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
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 我的组合详情
 *
 */
public class MyZuHeDatilActivity extends BascActivity implements View.OnClickListener{
    public static final String ZUHE_ID = "zuheid";
    private String[] colors = {"#FFF000","#74E7D3","#74D3E7","#51B4F3","#5173F3"};     //饼图颜色
    private String zuheId,zuheName,zuheDesc;
    private RadarChart mChart;
    private ArrayList<StockBean> list;
    private CanvasView canvasView;
    private HashMap<String, Object> map;
    private ArrayList<HashMap<String, Object>> data;
    private TextView mPersent,mTimes,mLikes,mBuildTime,mDataNum,mMonthNum,
            mJingZhi,mTotal,mAdavce,mLastTime,mflashTime,mName,
            mNum,mPriceChange,mSuccess,mNiuRenName;
    private ImageView mStarImage;
    private StarDetailInfo starDetailInfo;
    private RatingBar mRating;
    private RelativeLayout mTitleRelat;
    private MyListView mListView;
    private ArrayList<HeadIndex> stockList;
    private double remainMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celue_datil);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        zuheId = getIntent().getStringExtra(ZUHE_ID);
        initWight();
        getStargeDetialData(zuheId);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColorBlack(this,tintManager);
    }
    /**
     * 设置数据
     */
    public void setWidghtData(){
        DecimalFormat df =new DecimalFormat("#0.000");   //保留三位小数
            List<StarDetailInfo.ResultBean.StockRatioBean> stock = starDetailInfo.getResult().getStockRatio();   //饼图
        StarDetailInfo.ResultBean.PorfolioInfoBean porfolioInfoBean = starDetailInfo.getResult().getPorfolioInfo();
        StarDetailInfo.ResultBean.AchievemntBean achievemntBean = starDetailInfo.getResult().getAchievemnt();
        StarDetailInfo.ResultBean.TransferPositionsBean transferPositionsBean = starDetailInfo.getResult().getTransferPositions();
        mLikes.setText(porfolioInfoBean.getFavorites()+"");
        mBuildTime.setText("创建于："+porfolioInfoBean.getCreateTime());
        mTotal.setText(df.format(porfolioInfoBean.getTotleReturns())+"");
        mDataNum.setText(df.format(porfolioInfoBean.getReturn())+"%");
        mMonthNum.setText(df.format(porfolioInfoBean.getMonthlyAverage())+"%");
        mJingZhi.setText(df.format(porfolioInfoBean.getNetValue())+"");
        mAdavce.setText(porfolioInfoBean.getDesc());
        if (porfolioInfoBean.getUserImgUrl() != null&&!TextUtils.isEmpty(porfolioInfoBean.getUserImgUrl())){
            Picasso.with(this).load(porfolioInfoBean.getUserImgUrl()).into(mStarImage);
        }

        remainMoney = porfolioInfoBean.getCash();   //可用余额
        zuheName = porfolioInfoBean.getTitle();
        zuheDesc = porfolioInfoBean.getDesc();

        mNiuRenName.setText(porfolioInfoBean.getNickName());
        mLastTime.setText("（最后评估时间："+achievemntBean.getLastTime()+")");
        mflashTime.setText("("+transferPositionsBean.getLastTime()+")");
        List<StarDetailInfo.ResultBean.TransferPositionsBean.TransferPositionsInfoBean> list = transferPositionsBean.getTransferPositionsInfo();

            ArrayList<TiaoCangInfo> listInfo = new ArrayList<>();
            for (int i = 0;i<list.size();i++){
                TiaoCangInfo info = new TiaoCangInfo();
                if (list.get(i).getBuyType() == 0){
                    info.setBuyCome(true);
                }else {
                    info.setBuyCome(false);
                }
                info.setStockName(list.get(i).getName());
                info.setStockNum(list.get(i).getCode());
                info.setTradeNumStart(list.get(i).getBefor()+"");
                info.setTradeNumEnd(list.get(i).getAfter()+"");
                info.setTradePrice(list.get(i).getPrice()+"");
                listInfo.add(info);
            }
        MyZuHeItemAdapter zuHeItemAdapter = new MyZuHeItemAdapter(this);
        zuHeItemAdapter.setData(listInfo);
        mListView.setAdapter(zuHeItemAdapter);

        mRating.setRating((float) achievemntBean.getGeneral());
        setChartData(achievemntBean);
        ArrayList<ChartInfo> chartList = new ArrayList<>();
        for (int i = 0;i<stock.size();i++){
            ChartInfo info = new ChartInfo();
            info.setName(stock.get(i).getName());
            info.setParsent(stock.get(i).getRatio()+"");
            chartList.add(info);
        }
        setCanvasData(chartList);
        //持仓股票
        List<StarDetailInfo.ResultBean.HoldingDetailBean> hold =  starDetailInfo.getResult().getHoldingDetail();
        stockList = new ArrayList<>();
        for (int i = 0;i<hold.size();i++){
            StarDetailInfo.ResultBean.HoldingDetailBean holdDetial = hold.get(i);
            HeadIndex headIndex = new HeadIndex();
            headIndex.setIndexName(holdDetial.getName());
            headIndex.setIndexNum(holdDetial.getCode());
            headIndex.setPrice(holdDetial.getPrice());
            headIndex.setStockNum((int) holdDetial.getVolumn());
            headIndex.setType(1);    //标记为老仓位
            stockList.add(headIndex);
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void initWight(){

        mTitleRelat = (RelativeLayout) findViewById(R.id.all_celue_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        String  title = getIntent().getStringExtra(AutoWisdomFragment.CELUENAME);    //传参

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        TextView mTitle = (TextView) findViewById(R.id.back_txt);
        ImageView mGoOrder = (ImageView) findViewById(R.id.order_txt);
        mPersent = (TextView) findViewById(R.id.rank_parsent);    //百分比字体设置
        mTimes = (TextView) findViewById(R.id.rank_times);      //倍数设置
        mLikes = (TextView) findViewById(R.id.guanzhu_num);   //关注人数
        mBuildTime = (TextView) findViewById(R.id.build_time);   //创建时间
        mDataNum = (TextView) findViewById(R.id.jingzhi_num);   //日增长
        mMonthNum = (TextView) findViewById(R.id.max_num);      //月增长
        mJingZhi = (TextView) findViewById(R.id.rate_num);      //净值
        mTotal = (TextView) findViewById(R.id.persent_num);     //总收益
        mAdavce = (TextView) findViewById(R.id.advice_content);  //建议描述
        mStarImage = (ImageView) findViewById(R.id.advice_image_txt);    //牛人头像
        mLastTime = (TextView) findViewById(R.id.yeji_rank_time);    //最后评估时间
        mflashTime = (TextView) findViewById(R.id.diaocang_time_txt);   //调仓跟新时间
//        mName = (TextView) findViewById(R.id.diaocang_name);       //股票名字
//        mNum = (TextView) findViewById(R.id.diaocang_num);     //股票编号
//        mPriceChange = (TextView) findViewById(R.id.price_num);    //变动价格
//        mSuccess = (TextView) findViewById(R.id.price_start);     //参考成交价
        mRating = (RatingBar) findViewById(R.id.celue_seekbar);   //评分
        mNiuRenName = (TextView) findViewById(R.id.niuren_name);   //牛人名字
        mListView = (MyListView) findViewById(R.id.zuhe_list_item);

        MyScrollView mScrollview = (MyScrollView) findViewById(R.id.celue_scroll);   //滑动条

        mScrollview.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
               if (scrollY>100){
                   mTitleRelat.setBackgroundResource(R.color.write_color);
                   tintManager.setStatusBarTintResource(R.color.write_color);
               }else {
                   mTitleRelat.setBackgroundResource(android.R.color.transparent);
                   tintManager.setStatusBarTintResource(android.R.color.transparent);
               }
            }
        });


        mChart = (RadarChart) findViewById(R.id.chart1);
        canvasView = (CanvasView) findViewById(R.id.canvas_view);
        canvasView.setRadius(220f);    //设置图形半径

        mTitle.setText(title);

        mBackimg.setOnClickListener(this);
        mGoOrder.setOnClickListener(this);
    }

    public void setCanvasData(ArrayList<ChartInfo> list){
        data = new ArrayList<>();
        for (int i= 0;i<list.size();i++){
            ChartInfo info = list.get(i);
            setDataToView(info.getName(), colors[i], Float.parseFloat(info.getParsent()));
        }
//        setDataToView("农林牧渔", "#74E7D3", 0.15f);
//        setDataToView("电器设备", "#74D3E7", 0.15f);
//        setDataToView("公用事业", "#51B4F3", 0.4f);
//        setDataToView("食品饮料", "#5173F3", 0.1f);
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
     * 牛人组合/我的组合详情
     */
    public void getStargeDetialData(final String id){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,String> map = new HashMap<>();
                map.put("Id", id);
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.StarPorfolioDetail_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                Log.d("tag","我的组合--------"+message);
                if (!TextUtils.isEmpty(message)){
                    Gson gson = new Gson();
                    starDetailInfo = gson.fromJson(message, StarDetailInfo.class);
                    if (starDetailInfo.getHead().getStatus()==0){
                        setWidghtData();     //

                    }

                }

            }
        }.execute();
    }
    /**
     * 设置雷达图数据
     */
    public void setChartData(StarDetailInfo.ResultBean.AchievemntBean achievemntBean){
        /**
         * 用来描述该雷达图是什么用途
         */
        mChart.setDescription("");

        mChart.setWebLineWidth(1.5f);
        mChart.setWebLineWidthInner(0.75f);
        mChart.setWebAlpha(50);
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

        // set the marker to the chart
        mChart.setMarkerView(mv);

        setData(achievemntBean);

        mChart.animateXY(1000, 1000,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(0, true);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinValue(0f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);    //7f
        l.setYEntrySpace(5f);    //5f
    }

    public void setData(StarDetailInfo.ResultBean.AchievemntBean achievemntBean) {

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
        list.add(new StockBean((int) achievemntBean.getProfitability(), "盈利能力"));
        list.add(new StockBean((int) achievemntBean.getAntiRiskAbility(), "抗风险能力"));
        list.add(new StockBean((int) achievemntBean.getStability(), "稳定性"));
        list.add(new StockBean((int) achievemntBean.getDispersion(), "持股分散度"));
        list.add(new StockBean((int) achievemntBean.getReplication(), "可复制性"));

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
        data.setValueTextSize(15f);
        data.setDrawValues(false);
        data.setHighlightEnabled(true);

        mChart.setSkipWebLineCount(5);      //设置蜘蛛网的连接线


        mChart.setData(data);


        mChart.invalidate();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.order_txt:     //
                TiaoCangClass tiaoCangClass = new TiaoCangClass();
                tiaoCangClass.setStockID(zuheId);
                tiaoCangClass.setTotalMoney(remainMoney);
                tiaoCangClass.setName(zuheName);
                tiaoCangClass.setDesc(zuheDesc);
                tiaoCangClass.setList(stockList);
                Intent intent = new Intent(this, SetupZuHeActivity.class);
                intent.putExtra(SetupZuHeActivity.TYPE,1);     //调仓
                intent.putExtra(SetupZuHeActivity.CHICANG_STOCK,tiaoCangClass);
                startActivity(intent);
                break;
        }
    }
}
