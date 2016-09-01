package com.example.drawer.stockapp.activity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.GenTouAdapter;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewThree;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.ChiCangInfo;
import com.example.drawer.stockapp.model.FollowRecord;
import com.example.drawer.stockapp.model.StargDetial;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LianghuaCelueZhaoMuZhongActivity extends BascActivity implements View.OnClickListener{
    private TextView mLimitMoney,mStartMoney,mType,mStartType,mMuJiTime,mRunTime,mAdvice,mNiurenName,mParsent,mZuHeName,mRunDay,mZhisunXian,mGentouMoney;
    private CircleImageView headImg;
    private GenTouAdapter genTouAdapter;
    private MyListView mGenTouLiat;
    private CanvasViewThree canvasViewThree;
    private String LiangHuaId;    //量化id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianghua_celue_zhao_mu_zhong);
        tintManager.setStatusBarTintResource(R.color.write_color);
        LiangHuaId = getIntent().getStringExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID);
        initWight();

        LiangHuaAsyn liangHuaAsyn = new LiangHuaAsyn();
        liangHuaAsyn.execute(LiangHuaId);
        FollowAsyn followAsyn = new FollowAsyn();
        followAsyn.execute(LiangHuaId);
    }

    private void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.celue_first_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        canvasViewThree = (CanvasViewThree) findViewById(R.id.chart1);
        mParsent = (TextView) findViewById(R.id.celue_persent);    //目标收益
        mZuHeName = (TextView) findViewById(R.id.zuhe_name);     //组合名字
        mRunDay = (TextView) findViewById(R.id.run_time);    //运行天数
        mZhisunXian = (TextView) findViewById(R.id.zhi_zhun_xian);   //止损线
        mGentouMoney = (TextView) findViewById(R.id.gentou_money);   //跟投金额

        mLimitMoney = (TextView) findViewById(R.id.limit_money);   //跟投总金额
        mStartMoney = (TextView) findViewById(R.id.start_price);    //起投金额
        mType = (TextView) findViewById(R.id.celue_zuhe_type);    //组合类型
        mStartType = (TextView) findViewById(R.id.start_type);    //跟投类型
        mMuJiTime = (TextView) findViewById(R.id.muji_time_txt);   //募集时间
        mRunTime = (TextView) findViewById(R.id.run_time_txt);    //运行时间

        mAdvice = (TextView) findViewById(R.id.advice_content);   //描述
        headImg = (CircleImageView) findViewById(R.id.advice_image_txt);   //牛人头像
        mNiurenName = (TextView) findViewById(R.id.niuren_name);    //牛人名字

        canvasViewThree.setRadius(DensityUtils.dp2px(this,40));


        //跟投
        mGenTouLiat = (MyListView) findViewById(R.id.gengtou_listview);
        genTouAdapter = new GenTouAdapter(this);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);

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
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
        }
    }

    /**
     * 获取策略详情
     */
    private class LiangHuaAsyn extends AsyncTask<String,Void,String> {

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


            StargDetial.ResultBean.PorfolioDetailBean proinfo = stargDetial.getResult().getPorfolioDetail();
            mLimitMoney.setText(proinfo.getLimtAmount()+"元");
            mStartMoney.setText(proinfo.getStartAmount()+"元");
            mType.setText(proinfo.getPorfolioType()+"");

            StargDetial.ResultBean.PorfolioInfoBean infoBean = stargDetial.getResult().getPorfolioInfo();
            mMuJiTime.setText(infoBean.getRecuitmentStartTime().substring(0,10)+"-"+infoBean.getRecuitmentEndTime().substring(0,10));
            mRunTime.setText(infoBean.getRunStartDay().substring(0,10)+"-"+infoBean.getRunTargetEndDay().substring(0,10));

            StargDetial.ResultBean.StarInfoBean starInfoBean = stargDetial.getResult().getStarInfo();
            mAdvice.setText(starInfoBean.getTitle());
            mNiurenName.setText(starInfoBean.getName());
            if (starInfoBean.getImgUrl() != null&&!TextUtils.isEmpty(starInfoBean.getImgUrl())){
                Picasso.with(this).load(starInfoBean.getImgUrl()).into(headImg);
            }
            mParsent.setText(infoBean.getTargetReturns()+"%");
            setCanvasData(canvasViewThree, Double.parseDouble(infoBean.getTargetReturns()+""));
            mZuHeName.setText(infoBean.getTitle());
            mRunDay.setText(infoBean.getMaxDay()+"天");
            mZhisunXian.setText(infoBean.getStopLoss()+"%");
            mGentouMoney.setText(infoBean.getMostFollow()+"元");
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
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.PorfolioTrades_URL);
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
                    List<FollowRecord.ResultBean.CodeListBean> codeListBeen = record.getResult().getCodeList();
                    for (int i = 0;i<codeListBeen.size();i++){
                        FollowRecord.ResultBean.CodeListBean bean = codeListBeen.get(i);
                        ChiCangInfo info = new ChiCangInfo();
                        info.setTodayAdd(bean.getCode());
                        info.setNowPrice(bean.getName());
                        info.setBascPrice(bean.getPrice()+"");
                        info.setCangwei(bean.getTradeTime().substring(0,10));
                        chicangList.add(info);
                    }
                    genTouAdapter.setData(chicangList);
                    mGenTouLiat.setAdapter(genTouAdapter);
                }
            }
        }
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
