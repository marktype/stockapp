package com.example.drawer.stockapp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.ChiCangAdapter;
import com.example.drawer.stockapp.adapter.MyZuHeItemAdapter;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CustomDialog;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.customview.MyScrollView;
import com.example.drawer.stockapp.customview.chartview.MyMarkerView;
import com.example.drawer.stockapp.fragment.AutoWisdomFragment;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.ChartInfo;
import com.example.drawer.stockapp.model.ChiCangInfo;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.StargDetial;
import com.example.drawer.stockapp.model.StockBean;
import com.example.drawer.stockapp.model.TiaoCangClass;
import com.example.drawer.stockapp.model.TiaoCangInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

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
            mNum,mPriceChange,mSuccess,mNiuRenName,mNoDataImgChiCang,mSeeHistory;
    private ImageView mStarImage,mNoData,mNoDataChart;
    private StargDetial starDetailInfo;
    private RatingBar mRating;
    private RelativeLayout mTitleRelat;
    private MyListView mListView;
    private ArrayList<HeadIndex> stockList;
    private double remainMoney;    //余额
    private MyDialog dialog,delDialog;
    private String mToken;
    private LineChart mLineChart;
    private RelativeLayout mChartRelat;
    private MyListView mChiCnagList;
    private ChiCangAdapter chiCangAdapter;
    private DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celue_datil);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        zuheId = getIntent().getStringExtra(ZUHE_ID);
        initWight();
        getStargeDetialData(zuheId,mToken);

        dialog = ManagerUtil.getDiaLog(this);
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
//        DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
            List<StargDetial.ResultBean.StockRatioBean> stock = starDetailInfo.getResult().getStockRatio();   //饼图
        StargDetial.ResultBean.PorfolioInfoBean porfolioInfoBean = starDetailInfo.getResult().getPorfolioInfo();
        StargDetial.ResultBean.AchievemntBean achievemntBean = starDetailInfo.getResult().getAchievemnt();
        StargDetial.ResultBean.TransferPositionsBean transferPositionsBean = starDetailInfo.getResult().getTransferPositions();
        mLikes.setText(porfolioInfoBean.getFavorites()+"");
        mBuildTime.setText("创建于："+porfolioInfoBean.getCreateTime());
        mTotal.setText(df.format(porfolioInfoBean.getTotleReturns())+"");
        mDataNum.setText(df.format(porfolioInfoBean.getReturn())+"%");
        mMonthNum.setText(df.format(porfolioInfoBean.getMonthlyAverage())+"%");
        mJingZhi.setText(df.format(porfolioInfoBean.getNetValue()/1000000)+"");
        if (porfolioInfoBean.getDesc() != null&&!TextUtils.isEmpty(porfolioInfoBean.getDesc())){
            mAdavce.setText(porfolioInfoBean.getDesc());
        }else {
            mAdavce.setText("主理人未留下组合投资建议");
        }
        if (porfolioInfoBean.getUserImgUrl() != null&&!TextUtils.isEmpty(porfolioInfoBean.getUserImgUrl())){
            Picasso.with(this).load(porfolioInfoBean.getUserImgUrl()).into(mStarImage);
        }

        remainMoney = porfolioInfoBean.getCash();   //可用余额
        zuheName = porfolioInfoBean.getTitle();
        zuheDesc = porfolioInfoBean.getDesc();

        mNiuRenName.setText(porfolioInfoBean.getNickName());
//        if (achievemntBean.getLastTime() != null&&!TextUtils.isEmpty(achievemntBean.getLastTime())){
//            mLastTime.setText("（最后评估时间："+achievemntBean.getLastTime()+")");
//        }else {
//            mLastTime.setText("（最后评估时间：暂无)");
//        }

        mflashTime.setText("("+transferPositionsBean.getLastTime()+")");
        List<StargDetial.ResultBean.TransferPositionsBean.TransferPositionsInfoBean> list = transferPositionsBean.getTransferPositionsInfo();

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
                info.setTradeNumStart(list.get(i).getBefor());
                info.setTradeNumEnd(list.get(i).getAfter());
                info.setTradePrice(df.format(list.get(i).getPrice())+"");
                listInfo.add(info);
            }
        MyZuHeItemAdapter zuHeItemAdapter = new MyZuHeItemAdapter(this);
        zuHeItemAdapter.setData(listInfo);
        mListView.setAdapter(zuHeItemAdapter);

        setChartData(achievemntBean);
        ArrayList<ChartInfo> chartList = new ArrayList<>();
        float star = 0;
        for (int i = 0;i<stock.size();i++){
            ChartInfo info = new ChartInfo();
            info.setName(stock.get(i).getName());
            info.setParsent(stock.get(i).getRatio()+"");
            star += stock.get(i).getRatio();
            chartList.add(info);
        }

        setCanvasData(chartList);


        //持仓股票
        List<StargDetial.ResultBean.HoldingDetailBean> hold =  starDetailInfo.getResult().getHoldingDetail();
        stockList = new ArrayList<>();
        for (int i = 0;i<hold.size();i++){
            StargDetial.ResultBean.HoldingDetailBean holdDetial = hold.get(i);
            HeadIndex headIndex = new HeadIndex();
            headIndex.setIndexName(holdDetial.getName());
            headIndex.setIndexNum(holdDetial.getCode());
            headIndex.setPrice(holdDetial.getPrice());
            headIndex.setStockNum((int) holdDetial.getVolumn());
            headIndex.setType(1);    //标记为老仓位
            stockList.add(headIndex);
        }


        List<StargDetial.ResultBean.PorfolioInfoBean.ImgDataBean> ImgData =  porfolioInfoBean.getImgData();
        List<StargDetial.ResultBean.PorfolioInfoBean.BenchmarkImgDataBean> BenchmarkImgData = porfolioInfoBean.getBenchmarkImgData();
        if (ImgData != null&&BenchmarkImgData != null){
            StockQuxainMap(mLineChart,ImgData,BenchmarkImgData);
            mNoData.setVisibility(View.GONE);
            mLineChart.setVisibility(View.VISIBLE);
        }else {
            mNoData.setVisibility(View.VISIBLE);
            mLineChart.setVisibility(View.GONE);
        }


        List<StargDetial.ResultBean.HoldingDetailBean> holdingDetailBeen = starDetailInfo.getResult().getHoldingDetail();
        ArrayList<ChiCangInfo> chicangList = new ArrayList<>();
        for (int j = 0;j<holdingDetailBeen.size();j++){
            StargDetial.ResultBean.HoldingDetailBean holding = holdingDetailBeen.get(j);
            ChiCangInfo info = new ChiCangInfo();
            info.setStockName(holding.getName());
            info.setStockNum(holding.getCode());
            info.setTodayAdd(df.format(holding.getProfitRate())+"%");
            info.setTodayAddDecNum(holding.getProfitRate());
            info.setNowPrice(df.format(holding.getPrice())+"");
            info.setBascPrice(df.format(holding.getAvgPrice())+"");
            info.setCangwei(holding.getVolumn()+"");
            info.setFuYing(df.format(holding.getCumulativeReturnRate())+"%");
            info.setFuYingNum(holding.getCumulativeReturnRate());
            chicangList.add(info);
        }
        chiCangAdapter.setData(chicangList);
        if (!TextUtils.isEmpty(mToken)){
            mChiCnagList.setAdapter(chiCangAdapter);
            if (chicangList.size() == 0){
                mNoDataImgChiCang.setVisibility(View.VISIBLE);
                mNoDataImgChiCang.setText("");
            }
        }else {
            mNoDataImgChiCang.setVisibility(View.VISIBLE);
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

        RelativeLayout layoutOne = (RelativeLayout) findViewById(R.id.advice_txt);
        RelativeLayout layoutTwo = (RelativeLayout) findViewById(R.id.yeji_rank_lin);
        RelativeLayout layoutThree = (RelativeLayout) findViewById(R.id.dioacang_layout);
        RelativeLayout layoutFour = (RelativeLayout) findViewById(R.id.set_stock);
        RelativeLayout layoutFive = (RelativeLayout) findViewById(R.id.set_quxian);
        RelativeLayout layoutSix = (RelativeLayout) findViewById(R.id.chicang_relat);

        layoutOne.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutTwo.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutThree.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutFour.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutFive.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutSix.setBackgroundColor(getResources().getColor(R.color.write_color));


        String  title = getIntent().getStringExtra(AutoWisdomFragment.CELUENAME);    //传参

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        TextView mTitle = (TextView) findViewById(R.id.back_txt);
        ImageView mGoOrder = (ImageView) findViewById(R.id.order_txt);
        mGoOrder.setImageResource(R.mipmap.tiaocang);

//        mPersent = (TextView) findViewById(R.id.rank_parsent);    //百分比字体设置
//        mTimes = (TextView) findViewById(R.id.rank_times);      //倍数设置
        mLikes = (TextView) findViewById(R.id.guanzhu_num);   //关注人数
        mBuildTime = (TextView) findViewById(R.id.build_time);   //创建时间
        mDataNum = (TextView) findViewById(R.id.jingzhi_num);   //日增长
        mMonthNum = (TextView) findViewById(R.id.max_num);      //月增长
        mJingZhi = (TextView) findViewById(R.id.rate_num);      //净值
        mTotal = (TextView) findViewById(R.id.persent_num);     //总收益
        mAdavce = (TextView) findViewById(R.id.advice_content);  //建议描述
        mStarImage = (ImageView) findViewById(R.id.advice_image_txt);    //牛人头像
//        mLastTime = (TextView) findViewById(R.id.yeji_rank_time);    //最后评估时间
        mflashTime = (TextView) findViewById(R.id.diaocang_time_txt);   //调仓跟新时间
//        mName = (TextView) findViewById(R.id.diaocang_name);       //股票名字
//        mNum = (TextView) findViewById(R.id.diaocang_num);     //股票编号
//        mPriceChange = (TextView) findViewById(R.id.price_num);    //变动价格
//        mSuccess = (TextView) findViewById(R.id.price_start);     //参考成交价
        mRating = (RatingBar) findViewById(R.id.celue_seekbar);   //评分
        mNiuRenName = (TextView) findViewById(R.id.niuren_name);   //牛人名字
        mListView = (MyListView) findViewById(R.id.zuhe_list_item);
        mNoData = (ImageView) findViewById(R.id.nodata_img);    //无数据显示
        mNoDataChart = (ImageView) findViewById(R.id.nodata_img_two);  //雷达图无数据显示
        mChartRelat = (RelativeLayout) findViewById(R.id.yeji_rank_relat);   //业绩评级
        mNoDataImgChiCang = (TextView) findViewById(R.id.no_data_img_chicang);   //持仓无数据

        ImageView mDelete = (ImageView) findViewById(R.id.changjianwenti_txt);

        MyScrollView mScrollview = (MyScrollView) findViewById(R.id.celue_scroll);   //滑动条
        mSeeHistory = (TextView) findViewById(R.id.history_see);    //查看历史

        mScrollview.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
               if (scrollY>50){
                   mTitleRelat.setBackgroundColor(getResources().getColor(R.color.write_color));
                   tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
               }else {
                   mTitleRelat.setBackgroundResource(android.R.color.transparent);
                   tintManager.setStatusBarTintResource(android.R.color.transparent);
               }
            }
        });

        //持仓
        mChiCnagList = (MyListView) findViewById(R.id.chicang_listview);
        chiCangAdapter = new ChiCangAdapter(this);

        mLineChart = (LineChart) findViewById(R.id.lineChart);
        mChart = (RadarChart) findViewById(R.id.chart1);
        canvasView = (CanvasView) findViewById(R.id.canvas_view);
        canvasView.setRadius(220f);    //设置图形半径

        mTitle.setText(title);

        mBackimg.setOnClickListener(this);
        mGoOrder.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mSeeHistory.setOnClickListener(this);
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
     * 我的组合详情
     */
    public void getStargeDetialData(final String id, final String token){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,String> map = new HashMap<>();
                map.put("Id", id);
                String message = HttpManager.newInstance().getHttpDataByTwoLayer(token,map,HttpManager.StarPorfolioDetail_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                dialog.dismiss();
                String message = (String) o;
                Log.d("tag","我的组合--------"+message);
                if (!TextUtils.isEmpty(message)){
                    Gson gson = new Gson();
                    starDetailInfo = gson.fromJson(message, StargDetial.class);
                    if (starDetailInfo.getHead().getStatus()==0){
                        setWidghtData();     //
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();
    }
    /**
     * 设置雷达图数据
     */
    public void setChartData(StargDetial.ResultBean.AchievemntBean achievemntBean){
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

        if (achievemntBean.getProfitability() == 0&&achievemntBean.getAntiRiskAbility() == 0&&achievemntBean.getStability() == 0&&achievemntBean.getDispersion() == 0&&achievemntBean.getReplication() ==0){
            mChartRelat.setVisibility(View.GONE);
            mNoDataChart.setVisibility(View.VISIBLE);
        }else {
            mChartRelat.setVisibility(View.VISIBLE);
            mNoDataChart.setVisibility(View.GONE);
            setData(achievemntBean);
        }

        mChart.animateXY(1000, 1000,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(0, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinValue(0f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);    //7f
        l.setYEntrySpace(5f);    //5f
    }

    public void setData(StargDetial.ResultBean.AchievemntBean achievemntBean) {

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

        //设置星星数量
        mRating.setRating((float) (achievemntBean.getProfitability()+achievemntBean.getAntiRiskAbility()+achievemntBean.getStability()+achievemntBean.getDispersion()+achievemntBean.getReplication())/100);
//        list.add(222);
//        list.add(333);
        for (int i = 0; i < list.size(); i++) {
            yVals2.add(new Entry(list.get(i).getPercent(), i));
        }
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++)
            xVals.add(list.get(i % list.size()).getName());

        RadarDataSet set1 = new RadarDataSet(yVals1, "");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(yVals2, "");
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
            case R.id.changjianwenti_txt:
                initPopView(view);
                break;
            case R.id.history_see:
                intent = new Intent(this,HistoryRecordActivity.class);
                intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,zuheId);
                startActivity(intent);
                break;
        }
    }
    private PopupWindow mPopWindow;
    /**
     * 初始化popWindow
     * @param view
     */
    public void initPopView(View view){
        if (mPopWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.pop_item_layout, null);
            mPopWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setBackgroundDrawable(new BitmapDrawable());
            contentView.findViewById(R.id.delete_zuhe).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopWindow.dismiss();
                    final CustomDialog dialog = new CustomDialog(MyZuHeDatilActivity.this);
                    dialog.setMessageText("确认要删除组合吗？");
                    dialog.show();
                    dialog.setOnPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            delDialog = ManagerUtil.getDiaLog(MyZuHeDatilActivity.this);
                            DeleteMyZuheAsyn deleteMyZuheAsyn = new DeleteMyZuheAsyn();
                            deleteMyZuheAsyn.execute(zuheId,mToken);
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

        }
        mPopWindow.setOutsideTouchable(true);
//                mPopWindow.showAsDropDown(view, 100, 10);
        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {
            mPopWindow.showAsDropDown(view, 20, 0);

        }

    }

    /**
     * 删除我的组合
     */
    private class DeleteMyZuheAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[1], map, HttpManager.DeletePorfolio_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            delDialog.dismiss();
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject head = object.getJSONObject("Head");
                    if (head.getInt("Status") == 0){
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"删除失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 股票走势曲线图
     * @param mLineChart
     */
    private void StockQuxainMap(LineChart mLineChart,List<StargDetial.ResultBean.PorfolioInfoBean.ImgDataBean> ImgData,List<StargDetial.ResultBean.PorfolioInfoBean.BenchmarkImgDataBean> BenchmarkImgData){
        DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setAxisLineColor(getResources().getColor(android.R.color.transparent));
        xAxis.setGridColor(getResources().getColor(android.R.color.transparent));

        YAxis yAxis = mLineChart.getAxisLeft();
        yAxis.setAxisLineColor(getResources().getColor(android.R.color.transparent));
        yAxis.setGridColor(getResources().getColor(android.R.color.transparent));

        YAxis y1Axis = mLineChart.getAxisRight();
        y1Axis.setAxisLineColor(getResources().getColor(android.R.color.transparent));
        y1Axis.setGridColor(getResources().getColor(R.color.circle_con_bg));

        //设置X轴的文字在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //设置描述文字
        mLineChart.setDescription("收益率曲线图");

        //模拟一个x轴的数据  12/1 12/2 ... 12/7
        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < ImgData.size(); i++) {
            StargDetial.ResultBean.PorfolioInfoBean.ImgDataBean dataBean = ImgData.get(i);
            xValues.add(dataBean.getDate().substring(4,10));
        }

        //模拟一组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一

        ArrayList<Entry> yValue = new ArrayList<>();
        for (int i = 0; i < ImgData.size(); i++) {
            StargDetial.ResultBean.PorfolioInfoBean.ImgDataBean dataBean = ImgData.get(i);
            yValue.add(new Entry(Float.parseFloat(df.format(dataBean.getCumulativeReturn())), i));
        }

        //模拟第二组组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一

        ArrayList<Entry> yValue1 = new ArrayList<>();
        for (int i = 0; i < ImgData.size(); i++) {
            StargDetial.ResultBean.PorfolioInfoBean.BenchmarkImgDataBean benchmarkImgDataBean = BenchmarkImgData.get(i);
            yValue1.add(new Entry(Float.parseFloat(df.format(benchmarkImgDataBean.getCumulativeReturn())), i));
        }


        //构建一个LineDataSet 代表一组Y轴数据
        LineDataSet dataSet = new LineDataSet(yValue1, "沪深300");
        dataSet.setColor(R.color.quxian_nan);
        dataSet.setCircleColor(R.color.quxian_nan);
        dataSet.setDrawCircles(false);
        dataSet.setLineWidth(2f);

        //构建一个LineDataSet 代表一组Y轴数据 （比如不同的彩票： 七星彩  双色球）

        LineDataSet dataSet1 = new LineDataSet(yValue, "组合收益");
        dataSet1.setLineWidth(4f); // 线宽
        dataSet1.setDrawCircles(false);
        dataSet1.setColor(getResources().getColor(R.color.quxian_huang));// 显示颜色
        dataSet1.setCircleColor(getResources().getColor(R.color.quxian_huang));// 圆形的颜色
        //构建一个类型为LineDataSet的ArrayList 用来存放所有 y的LineDataSet   他是构建最终加入LineChart数据集所需要的参数
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        //将数据加入dataSets
        dataSets.add(dataSet);
        dataSets.add(dataSet1);

        //构建一个LineData  将dataSets放入
        LineData lineData = new LineData(xValues,dataSets);
        lineData.setValueTextColor(getResources().getColor(android.R.color.transparent));
        //将数据插入
        mLineChart.setData(lineData);


    }


}
