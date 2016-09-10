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
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

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
    private TextView mTarget,mMostGetMoney,mMostLose,mTradeNum,mLastTime,mLimitMoney,mStartMoney,mType,mStartType,mMuJiTime,mRunTime,mAdvice,mNiurenName,mNoDataImgTiaoCang,mNoDataImgChiCang;
    private String LiangHuaId;    //量化id
    private String LiangHuaName;   //量化name
    private CircleImageView headImg;
    private MyDialog dialog;
    private String mToken;
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
            mLastTime.setText("("+Transfer.getLastTime()+")");
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
            mAdvice.setText(starInfoBean.getTitle());
            mNiurenName.setText(starInfoBean.getName());
            Picasso.with(this).load(starInfoBean.getImgUrl()).placeholder(R.mipmap.img_place).into(headImg);
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
}
