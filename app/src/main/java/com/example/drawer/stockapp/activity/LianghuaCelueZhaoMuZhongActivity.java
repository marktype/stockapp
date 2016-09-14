package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.GenTouAdapter;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewThree;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.ChiCangInfo;
import com.example.drawer.stockapp.model.FollowRecord;
import com.example.drawer.stockapp.model.StargDetial;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LianghuaCelueZhaoMuZhongActivity extends BascActivity implements View.OnClickListener{
    private TextView mLimitMoney,mStartMoney,mType,mStartType,
            mMuJiTime,mRunTime,mAdvice,mNiurenName,mParsent
            ,mZuHeName,mRunDay,mZhisunXian,mGentouMoney,mStartEndMoney,mTargetMoney,mfenchengMoney;
    private CircleImageView headImg;
    private GenTouAdapter genTouAdapter;
    private MyListView mGenTouLiat;
    private CanvasViewThree canvasViewThree;
    private String LiangHuaId;    //量化id
    private MyDialog dialog;
    private EditText mWriteGentouMoney;
    private double targetshouyi,fengchengRate,totalMoney,money;
    private String mToken;
    private TextView mGentou;
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

        dialog = ManagerUtil.getDiaLog(this);
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
        TextView mFencheng = (TextView) findViewById(R.id.fengcheng_detial);   //分成信息
        mStartEndMoney = (TextView) findViewById(R.id.gentou_qujian);   //跟投区间
        mWriteGentouMoney = (EditText) findViewById(R.id.write_start_money);   //输入跟投金额
        mTargetMoney = (TextView) findViewById(R.id.target_money);   //目标收益
        mfenchengMoney = (TextView) findViewById(R.id.fengcheng_money);   //分成的收益

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
        mGentou = (TextView) findViewById(R.id.now_gentou);   //跟投

        mBackimg.setOnClickListener(this);
        mFencheng.setOnClickListener(this);
        mGentou.setOnClickListener(this);


        mWriteGentouMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                DecimalFormat df =new DecimalFormat("#0.00");   //保留兩位位小数
                String gentouMoney = mWriteGentouMoney.getText().toString();
                if (!TextUtils.isEmpty(gentouMoney)){
                    money = Double.parseDouble(gentouMoney);
                }else {
                    money = 0;
                }
                if (money>totalMoney){
                    mGentouMoney.setText(totalMoney+" 元");
                    money = totalMoney;
                }else {
                    mGentouMoney.setText((totalMoney-money)+" 元");
                }
                mTargetMoney.setText("目标收益  "+money*targetshouyi/100);
                mfenchengMoney.setText("  分成费 "+df.format((money*targetshouyi/100)*fengchengRate/100));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);

        mToken = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.fengcheng_detial:
                getDiaLogInfo();
                break;
            case R.id.now_gentou:
                if (!TextUtils.isEmpty(mToken)){
                    GentouAsyn gentouAsyn = new GentouAsyn();
                    if (money>0){
                        dialog = ManagerUtil.getDiaLog(this);
                        gentouAsyn.execute(LiangHuaId,money+"",mToken);
                    }else {
//                        Toast.makeText(this,"跟投余额必须大于0",Toast.LENGTH_SHORT).show();
                        TSnackbar.make(mGentou,"跟投余额必须大于0！",TSnackbar.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    /**
     * 分成说明
     */
    private void getDiaLogInfo(){
        final MyDialog dialog = new MyDialog(this, 300, 200,R.layout.fencheng_detial_layout,R.style.MyDialogStyleDia);
        dialog.setCancelable(true);
        dialog.show();

        View view = dialog.getView();
        TextView ok = (TextView) view.findViewById(R.id.fencheng_sure);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
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

            mStartEndMoney.setText("跟投区间"+infoBean.getStarInvestment()+"-"+infoBean.getMostFollow()+"(虚拟资金)");

            StargDetial.ResultBean.StarInfoBean starInfoBean = stargDetial.getResult().getStarInfo();
            if (starInfoBean.getTitle() != null&&!TextUtils.isEmpty(starInfoBean.getTitle())){
                mAdvice.setText(starInfoBean.getTitle());
            }else {
                mAdvice.setText("牛逼的组合不需要解释！");
            }
            mNiurenName.setText(starInfoBean.getName());
            Picasso.with(this).load(starInfoBean.getImgUrl()).placeholder(R.mipmap.img_place).into(headImg);
            mParsent.setText(infoBean.getTargetReturns()+"%");
            targetshouyi = infoBean.getTargetReturns();
            fengchengRate = infoBean.getShareRatio();   //分成率
            setCanvasData(canvasViewThree, Double.parseDouble(infoBean.getTargetReturns()+""));
            mZuHeName.setText(infoBean.getTitle());
            mRunDay.setText(infoBean.getMaxDay()+"天");
            mZhisunXian.setText(infoBean.getStopLoss()+"%");
            mGentouMoney.setText(infoBean.getPorfolioAmount()+"元");
            totalMoney = infoBean.getPorfolioAmount();
        }
    }


    /**
     * 策略跟投
     */
    private class GentouAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            map.put("Money", strings[1]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[2],map,HttpManager.PayStrategy_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            Log.d("tag","sss----跟投---"+s);
            if (!TextUtils.isEmpty(s)){
                if (s.contains("Head")){
                    try {
                        JSONObject object = new JSONObject(s);
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getInt("Status") == 0){
                            Toast.makeText(LianghuaCelueZhaoMuZhongActivity.this,"跟投成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(LianghuaCelueZhaoMuZhongActivity.this,head.getString("Msg"),Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
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
