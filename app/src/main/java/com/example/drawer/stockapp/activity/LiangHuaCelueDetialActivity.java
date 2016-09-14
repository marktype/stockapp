package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.ChiCangAdapter;
import com.example.drawer.stockapp.adapter.GenTouAdapter;
import com.example.drawer.stockapp.adapter.TiaoCangAdapter;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.ChiCangInfo;
import com.example.drawer.stockapp.model.FollowRecord;
import com.example.drawer.stockapp.model.StargDetial;
import com.example.drawer.stockapp.model.TiaoCangInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LiangHuaCelueDetialActivity extends BascActivity implements View.OnClickListener{

    private MyListView mTiaoCangList,mChiCnagList,mGenTouLiat;
    public static final String LIANGHUA_ID = "lianghuaid";
    public static final String LIANGHUA_NAME = "lianghuaname";
    private TiaoCangAdapter tiaoCangAdapter;
    private ChiCangAdapter chiCangAdapter;
    private GenTouAdapter genTouAdapter;
    private TextView mTarget,mMostGetMoney,mMostLose,mTradeNum,mLastTime,
            mLimitMoney,mStartMoney,mType,mStartType,mMuJiTime,mRunTime,
            mAdvice,mNiurenName,mNoDataImgTiaoCang,mNoDataImgChiCang;
    private String LiangHuaId;    //量化id
    private String LiangHuaName;   //量化name
    private CircleImageView headImg;
    private MyDialog dialog;
    private String mToken;
    private LineChart mLineChart;
    private ImageView mNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.celue_detial_first_layout);
        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        Intent intent = getIntent();
        tintManager.setStatusBarTintResource(R.color.write_color);
        LiangHuaId = intent.getStringExtra(LIANGHUA_ID);
        LiangHuaName = intent.getStringExtra(LIANGHUA_NAME);
        initWight();
        LiangHuaAsyn liangHuaAsyn = new LiangHuaAsyn();
        liangHuaAsyn.execute(LiangHuaId);
        FollowAsyn followAsyn = new FollowAsyn();
        followAsyn.execute(LiangHuaId);

        dialog = ManagerUtil.getDiaLog(this);
    }

    private void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.celue_first_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        TextView mTitle = (TextView) findViewById(R.id.back_txt);   //题目title
        mTitle.setText(LiangHuaName);

        mTarget = (TextView) findViewById(R.id.goal_shouyi);    //目标收益
        mMostGetMoney = (TextView) findViewById(R.id.max_long_time);   //最大收益
        mMostLose = (TextView) findViewById(R.id.zhishunxian_txt);   //最大亏损
        mTradeNum = (TextView) findViewById(R.id.shouyi_txt);   //交易次数
        mLastTime = (TextView) findViewById(R.id.last_time);   //最后调仓时间

        mLimitMoney = (TextView) findViewById(R.id.limit_money);   //跟投总金额
        mStartMoney = (TextView) findViewById(R.id.start_price);    //起投金额
        mType = (TextView) findViewById(R.id.celue_zuhe_type);    //组合类型
        mStartType = (TextView) findViewById(R.id.start_type);    //跟投类型
        mMuJiTime = (TextView) findViewById(R.id.muji_time_txt);   //募集时间
        mRunTime = (TextView) findViewById(R.id.run_time_txt);    //运行时间

        mAdvice = (TextView) findViewById(R.id.advice_content);   //描述
        headImg = (CircleImageView) findViewById(R.id.advice_image_txt);   //牛人头像
        mNiurenName = (TextView) findViewById(R.id.niuren_name);    //牛人名字

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);

        mLineChart = (LineChart) findViewById(R.id.lineChart);
        mNoData = (ImageView) findViewById(R.id.nodata_img);  //曲线图无数据
        mNoDataImgTiaoCang = (TextView) findViewById(R.id.no_data_img);    //无数据显示图片
        mNoDataImgChiCang = (TextView) findViewById(R.id.no_data_img_chicang);   //持仓无数据

        //调仓
        mTiaoCangList = (MyListView) findViewById(R.id.tiaocang_listview);
        tiaoCangAdapter = new TiaoCangAdapter(this);


        //持仓
        mChiCnagList = (MyListView) findViewById(R.id.chicang_listview);
        chiCangAdapter = new ChiCangAdapter(this);


        //跟投
        mGenTouLiat = (MyListView) findViewById(R.id.gengtou_listview);
        genTouAdapter = new GenTouAdapter(this);



        mBackimg.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
        }
    }

    /**
     * 获取策略详情
     */
    private class LiangHuaAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.StrategyDetail_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           dialog.dismiss();
            Log.d("tag","量化策略---详情----"+s);
            if (s != null&& !TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                StargDetial stargDetial = gson.fromJson(s,StargDetial.class);
                parseData(stargDetial);
            }
        }
    }

    private void parseData(StargDetial stargDetial){
        if (stargDetial.getHead().getStatus() == 0){
            StargDetial.ResultBean.TransferPositionsBean Transfer = stargDetial.getResult().getTransferPositions();
            if (Transfer.getLastTime() != null&&!TextUtils.isEmpty(Transfer.getLastTime())){
                mLastTime.setText("("+Transfer.getLastTime()+")");
            }else {
                mLastTime.setText("(暂无调仓信息)");
            }
            List<StargDetial.ResultBean.TransferPositionsBean.TransferPositionsInfoBean> infoBeanList = Transfer.getTransferPositionsInfo();
            ArrayList<TiaoCangInfo> tiaoCang = new ArrayList<>();
            for (int i = 0;i<infoBeanList.size();i++){
                StargDetial.ResultBean.TransferPositionsBean.TransferPositionsInfoBean bean = infoBeanList.get(i);
                TiaoCangInfo info = new TiaoCangInfo();
                if (bean.getBuyType() == 0){
                    info.setBuyCome(true);
                }else {
                    info.setBuyCome(false);
                }
                info.setStockName(bean.getName());
                info.setStockNum(bean.getCode());
                info.setTradeNumStart(bean.getBefor());
                info.setTradeNumEnd(bean.getAfter());
                info.setTradePrice(bean.getPrice()+"");
                tiaoCang.add(info);
            }
            tiaoCangAdapter.setData(tiaoCang);

            if (!TextUtils.isEmpty(mToken)){
                mTiaoCangList.setAdapter(tiaoCangAdapter);
                if (tiaoCang.size() == 0){
                    mNoDataImgTiaoCang.setVisibility(View.VISIBLE);
                    mNoDataImgTiaoCang.setText("");
                }
            }else {
                mNoDataImgTiaoCang.setVisibility(View.VISIBLE);
            }

            List<StargDetial.ResultBean.HoldingDetailBean> holdingDetailBeen = stargDetial.getResult().getHoldingDetail();
            ArrayList<ChiCangInfo> chicangList = new ArrayList<>();
            for (int j = 0;j<holdingDetailBeen.size();j++){
                StargDetial.ResultBean.HoldingDetailBean holding = holdingDetailBeen.get(j);
                ChiCangInfo info = new ChiCangInfo();
                info.setStockName(holding.getName());
                info.setStockNum(holding.getCode());
                info.setTodayAdd(holding.getProfitRate()+"%");
                info.setNowPrice(holding.getPrice()+"");
                info.setBascPrice(holding.getAvgPrice()+"");
                info.setCangwei(holding.getVolumn()+"");
                info.setFuYing(holding.getCumulativeReturnRate()+"%");
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

            StargDetial.ResultBean.PorfolioDetailBean proinfo = stargDetial.getResult().getPorfolioDetail();
            mLimitMoney.setText(proinfo.getLimtAmount()+"元");
            mStartMoney.setText(proinfo.getStartAmount()+"元");
            if (proinfo.getPorfolioType() == 0){
                mType.setText("短线");
            }else if (proinfo.getPorfolioType() == 1){
                mType.setText("中线");
            }else if (proinfo.getPorfolioType() == 2){
                mType.setText("长线");
            }
            if (proinfo.getRecruitType() == 0){
                mStartType.setText("稳健型");
            }else if (proinfo.getRecruitType() == 1){
                mStartType.setText("激进型");
            }else if (proinfo.getRecruitType() == 2){
                mStartType.setText("保本型");
            }

            StargDetial.ResultBean.PorfolioInfoBean infoBean = stargDetial.getResult().getPorfolioInfo();
            mMuJiTime.setText(infoBean.getRecuitmentStartTime().substring(0,10)+"-"+infoBean.getRecuitmentEndTime().substring(0,10));
            mRunTime.setText(infoBean.getRunStartDay().substring(0,10)+"-"+infoBean.getRunTargetEndDay().substring(0,10));

            StargDetial.ResultBean.StarInfoBean starInfoBean = stargDetial.getResult().getStarInfo();
            if (starInfoBean.getTitle() != null&&!TextUtils.isEmpty(starInfoBean.getTitle())){
                mAdvice.setText(starInfoBean.getTitle());
            }else {
                mAdvice.setText("牛逼的组合不需要解释！");
            }
            mNiurenName.setText(starInfoBean.getName());
            Picasso.with(this).load(starInfoBean.getImgUrl()).placeholder(R.mipmap.img_place).into(headImg);


            List<StargDetial.ResultBean.PorfolioInfoBean.ImgDataBean> ImgData =  infoBean.getImgData();
            List<StargDetial.ResultBean.PorfolioInfoBean.BenchmarkImgDataBean> BenchmarkImgData = infoBean.getBenchmarkImgData();
            if (ImgData != null&&BenchmarkImgData != null){
                StockQuxainMap(mLineChart,ImgData,BenchmarkImgData);
                mNoData.setVisibility(View.GONE);
                mLineChart.setVisibility(View.VISIBLE);
            }else {
                mNoData.setVisibility(View.VISIBLE);
                mLineChart.setVisibility(View.GONE);
            }
        }

    }

    /**
     * 跟头记录
     */
    private class FollowAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            map.put("AlongHistoryType", "PorfolioTrades");
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.AlongRecords_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("tag","跟投记录------"+s);
            if (s != null&&!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                FollowRecord record = gson.fromJson(s,FollowRecord.class);
                if (record.getHead().getStatus() == 0){
                    ArrayList<ChiCangInfo> chicangList = new ArrayList<>();
                    List<FollowRecord.ResultBean.AlongRecordsBean> codeListBeen = record.getResult().getAlongRecords();
                    for (int i = 0;i<codeListBeen.size();i++){
                        FollowRecord.ResultBean.AlongRecordsBean bean = codeListBeen.get(i);
                        ChiCangInfo info = new ChiCangInfo();
                        info.setTodayAdd(i+"");
                        info.setNowPrice(bean.getAlongUserName().replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*"));    //中间4位用*代替
                        info.setBascPrice(bean.getAlongAmount()+"");
                        info.setCangwei(bean.getAlongTime().substring(0,10));
                        chicangList.add(info);
                    }
                    genTouAdapter.setData(chicangList);
                    mGenTouLiat.setAdapter(genTouAdapter);
                }
            }
        }
    }

    /**
     * 股票走势曲线图
     * @param mLineChart
     */
    private void StockQuxainMap(LineChart mLineChart, List<StargDetial.ResultBean.PorfolioInfoBean.ImgDataBean> ImgData, List<StargDetial.ResultBean.PorfolioInfoBean.BenchmarkImgDataBean> BenchmarkImgData){
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
            xValues.add(dataBean.getDate().substring(5,10));
        }

        //模拟一组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一

        ArrayList<Entry> yValue = new ArrayList<>();
        for (int i = 0; i < ImgData.size(); i++) {

            StargDetial.ResultBean.PorfolioInfoBean.ImgDataBean dataBean = ImgData.get(i);
            yValue.add(new Entry(Float.parseFloat(df.format(dataBean.getCumulativeReturn())), i));
        }

        //构建一个LineDataSet 代表一组Y轴数据
        LineDataSet dataSet = new LineDataSet(yValue, "沪深300");
        dataSet.setColor(R.color.quxian_nan);
        dataSet.setCircleColor(R.color.quxian_nan);
        dataSet.setDrawCircles(false);
        dataSet.setLineWidth(2f);

        //模拟第二组组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一

        ArrayList<Entry> yValue1 = new ArrayList<>();
        for (int i = 0; i < ImgData.size(); i++) {
            StargDetial.ResultBean.PorfolioInfoBean.BenchmarkImgDataBean benchmarkImgDataBean = BenchmarkImgData.get(i);
            yValue1.add(new Entry(Float.parseFloat(df.format(benchmarkImgDataBean.getCumulativeReturn())), i));
        }

        Log.e("wing", yValue.size() + "");

        //构建一个LineDataSet 代表一组Y轴数据 （比如不同的彩票： 七星彩  双色球）

        LineDataSet dataSet1 = new LineDataSet(yValue1, "组合收益");

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
