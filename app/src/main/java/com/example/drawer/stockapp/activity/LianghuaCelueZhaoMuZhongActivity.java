package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.GenTouAdapter;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewThree;
import com.example.drawer.stockapp.customview.CustomDialog;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.fragment.AutoWisdomFragment;
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
            ,mZuHeName,mRunDay,mZhisunXian,mGentouMoney,mStartEndMoney,
            mTargetMoney,mfenchengMoney,mTitle,mFenCheng,mNoDataGentou;
    private CircleImageView headImg;
    private GenTouAdapter genTouAdapter;
    private MyListView mGenTouLiat;
    private CanvasViewThree canvasViewThree;
    private String LiangHuaId;    //量化id
    private MyDialog dialog;
    private EditText mWriteGentouMoney;
    private double targetshouyi,fengchengRate,money;
    private int totalMoney;
    private String mToken;
    private TextView mGentou;
    private int type;
    private DecimalFormat df =new DecimalFormat("#0.00");   //保留兩位位小数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianghua_celue_zhao_mu_zhong);
        tintManager.setStatusBarTintResource(R.color.write_color);
        LiangHuaId = getIntent().getStringExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID);
        type = getIntent().getIntExtra(LiangHuaCelueDetialActivity.TYPE,0);
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

        RelativeLayout layoutOne = (RelativeLayout) findViewById(R.id.zhaomu_zhong_relat);
        RelativeLayout layoutTwo = (RelativeLayout) findViewById(R.id.gengtou_relat);
        RelativeLayout layoutThree = (RelativeLayout) findViewById(R.id.gentou_money_relat);
        RelativeLayout layoutFour = (RelativeLayout) findViewById(R.id.zuhe_detial_relat);
        RelativeLayout layoutFive = (RelativeLayout) findViewById(R.id.tiaocang_relat);
        RelativeLayout layoutSix = (RelativeLayout) findViewById(R.id.gentou_relat);
        LinearLayout layoutSeven = (LinearLayout) findViewById(R.id.hint_info_lin);

        layoutOne.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutTwo.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutThree.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutFour.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutFive.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutSix.setBackgroundColor(getResources().getColor(R.color.write_color));
        layoutSeven.setBackgroundColor(getResources().getColor(R.color.write_color));

        mTitle = (TextView) findViewById(R.id.back_txt);   //招募中题目
        canvasViewThree = (CanvasViewThree) findViewById(R.id.chart1);
        mParsent = (TextView) findViewById(R.id.celue_persent);    //目标收益
        mZuHeName = (TextView) findViewById(R.id.zuhe_name);     //组合名字
        mRunDay = (TextView) findViewById(R.id.run_time);    //运行天数
        mZhisunXian = (TextView) findViewById(R.id.zhi_zhun_xian);   //止损线
        mFenCheng = (TextView) findViewById(R.id.fencheng_money);  //分成费
        mGentouMoney = (TextView) findViewById(R.id.gentou_money);   //跟投金额
        TextView mFencheng = (TextView) findViewById(R.id.fengcheng_detial);   //分成信息
//        mStartEndMoney = (TextView) findViewById(R.id.gentou_qujian);   //跟投区间
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

        mNoDataGentou = (TextView) findViewById(R.id.no_data_img_gengtou);  //跟投无数据

        canvasViewThree.setRadius(DensityUtils.dp2px(this,40));

        ImageView mDeleteImg = (ImageView) findViewById(R.id.changjianwenti_txt);  //取消跟投
        mGentou = (TextView) findViewById(R.id.now_gentou);   //跟投
        if (type == 1){
            mDeleteImg.setVisibility(View.VISIBLE);
            mGentou.setVisibility(View.GONE);
        }else {
            mDeleteImg.setVisibility(View.GONE);
            mGentou.setVisibility(View.VISIBLE);
        }

        //跟投
        mGenTouLiat = (MyListView) findViewById(R.id.gengtou_listview);
        genTouAdapter = new GenTouAdapter(this);

        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);

        mBackimg.setOnClickListener(this);
        mFencheng.setOnClickListener(this);
        mGentou.setOnClickListener(this);
        mDeleteImg.setOnClickListener(this);


        mWriteGentouMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                DecimalFormat df =new DecimalFormat("#0.00");   //保留兩位位小数
                String gentouMoney = mWriteGentouMoney.getText().toString();
                if (!TextUtils.isEmpty(gentouMoney)){
                    money = Double.parseDouble(gentouMoney);
                }else {
                    money = 0;
                }
                if (money>totalMoney){
//                    mGentouMoney.setText("0 元");
                    money = totalMoney;
                    mWriteGentouMoney.setText(totalMoney+"");
                }
//                else {
//                    mGentouMoney.setText(df.format(totalMoney-money)+" 元");
//                }
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
                if (TextUtils.isEmpty(mToken)){
                    Toast.makeText(getApplicationContext(),"您还未登陆，请先登录",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                }else if (totalMoney == 0){
                    Toast.makeText(getApplicationContext(),"该组合已募集满",Toast.LENGTH_SHORT).show();
                }else {
                    GentouAsyn gentouAsyn = new GentouAsyn();
                    if (money>0){
                        dialog = ManagerUtil.getDiaLog(this);
                        gentouAsyn.execute(LiangHuaId,money+"",mToken);
                    }else {
                        Toast.makeText(getApplicationContext(),"跟投金额必须大于0",Toast.LENGTH_SHORT).show();
//                        TSnackbar.make(mGentou,"跟投余额必须大于0！",TSnackbar.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.changjianwenti_txt:
                initPopView(view);
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
            TextView cancal = (TextView) contentView.findViewById(R.id.delete_zuhe);
            cancal.setText("取消跟投");
            mPopWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setBackgroundDrawable(new BitmapDrawable());
            cancal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopWindow.dismiss();
                    final CustomDialog dialog = new CustomDialog(LianghuaCelueZhaoMuZhongActivity.this);
                    dialog.setMessageText("确认要取消跟投吗？");
                    dialog.show();
                    dialog.setOnPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DeleteMyZuheAsyn deleteMyZuheAsyn = new DeleteMyZuheAsyn();
                            deleteMyZuheAsyn.execute(LiangHuaId,mToken);
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
     * q取消跟投
     */
    private class DeleteMyZuheAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[1], map, HttpManager.CancelAlong_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject head = object.getJSONObject("Head");
                    if (head.getInt("Status") == 0){
                        Intent in = new Intent();
                        in.setAction(AutoWisdomFragment.BROAD_TYPE);
                        //发送广播,销毁此界面
                        sendBroadcast(in);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"取消失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 分成说明
     */
    private void getDiaLogInfo(){
        final MyDialog dialog = new MyDialog(this, 250, 200,R.layout.fencheng_detial_layout,R.style.MyDialogStyleDia);
        dialog.setCancelable(true);
        dialog.show();

        View view = dialog.getView();
        view.setBackgroundColor(getResources().getColor(R.color.write_color));
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
            StargDetial.ResultBean.PorfolioInfoBean infoBean = stargDetial.getResult().getPorfolioInfo();
            if (infoBean.getPorfolioAmount()>=10000){
                mLimitMoney.setText(df.format(infoBean.getPorfolioAmount()/10000)+"万元");
            }else {
                mLimitMoney.setText(df.format(infoBean.getPorfolioAmount())+"元");
            }
            if (infoBean.getMostFollow()>=10000){
                mStartMoney.setText(df.format(infoBean.getMostFollow()/10000)+"万元");
            }else {
                mStartMoney.setText(df.format(infoBean.getMostFollow())+"元");
            }

            if (proinfo.getPorfolioType() == 0){
                mType.setText("短线");
            }else if (proinfo.getPorfolioType() == 1){
                mType.setText("中线");
            }else if (proinfo.getPorfolioType() == 2){
                mType.setText("长线");
            }
            if (infoBean.getRecruitType() == 0){
                mStartType.setText("稳健型");
            }else if (infoBean.getRecruitType() == 1){
                mStartType.setText("激进型");
            }else if (infoBean.getRecruitType() == 2){
                mStartType.setText("保本型");
            }
            mTitle.setText(infoBean.getTitle()+"（招募中）");
            mMuJiTime.setText(infoBean.getRecuitmentStartTime().substring(0,10)+"至"+infoBean.getRecuitmentEndTime().substring(0,10));
            mRunTime.setText(infoBean.getRunStartDay().substring(0,10)+"至"+infoBean.getRunTargetEndDay().substring(0,10));

            if (proinfo.getStartAmount()>=10000){
                mWriteGentouMoney.setHint("跟投范围"+proinfo.getStartAmount()/10000+"万-"+proinfo.getLimtAmount()/10000+"万(虚拟资金)");
//                mStartEndMoney.setText("跟投区间"+proinfo.getStartAmount()/10000+"万-"+proinfo.getLimtAmount()/10000+"万(虚拟资金)");
            }else {
                mWriteGentouMoney.setHint("跟投范围"+proinfo.getStartAmount()+"元-"+proinfo.getLimtAmount()+"元(虚拟资金)");
//                mStartEndMoney.setText("跟投区间"+proinfo.getStartAmount()+"元-"+proinfo.getLimtAmount()+"元(虚拟资金)");
            }

            StargDetial.ResultBean.StarInfoBean starInfoBean = stargDetial.getResult().getStarInfo();
            if (starInfoBean.getTitle() != null&&!TextUtils.isEmpty(starInfoBean.getTitle())){
                mAdvice.setText(starInfoBean.getTitle());
            }else {
                mAdvice.setText("牛逼的组合不需要解释！");
            }
            if (infoBean.getShareRatio() == 0){
                mFenCheng.setText("免费");
            }else {
                mFenCheng.setText(infoBean.getShareRatio()+"%");
            }
            if (starInfoBean.getName() != null&&!TextUtils.isEmpty(starInfoBean.getName())){
                mNiurenName.setText(starInfoBean.getName());
            }else {
                mNiurenName.setText("实盈量化策略");
            }

            Picasso.with(this).load(starInfoBean.getImgUrl()).placeholder(R.mipmap.img_place).into(headImg);
            mParsent.setText(infoBean.getTargetReturns()+"%");
            targetshouyi = infoBean.getTargetReturns();
            fengchengRate = infoBean.getShareRatio();   //分成率
            setCanvasData(canvasViewThree, Double.parseDouble(infoBean.getTargetReturns()+""));
            mZuHeName.setText(infoBean.getTitle());
            mRunDay.setText(infoBean.getMaxDay()+"天");
            mZhisunXian.setText(infoBean.getStopLoss()+"%");
            mGentouMoney.setText((int)(infoBean.getPorfolioAmount()-proinfo.getCompleteAlongAmount())+"元");
            totalMoney = (int)(infoBean.getPorfolioAmount()-proinfo.getCompleteAlongAmount());
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
                            Toast.makeText(getApplicationContext(),"跟投成功",Toast.LENGTH_SHORT).show();
                            Intent in = new Intent();
                            in.setAction(AutoWisdomFragment.BROAD_TYPE);
                            //发送广播,销毁此界面
                            sendBroadcast(in);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),head.getString("Msg"),Toast.LENGTH_SHORT).show();
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
                    DecimalFormat df =new DecimalFormat("#0.00");   //保留兩位位小数
                    List<FollowRecord.ResultBean.AlongRecordsBean> codeListBeen = record.getResult().getAlongRecords();
                    for (int i = 0;i<codeListBeen.size();i++){
                        FollowRecord.ResultBean.AlongRecordsBean bean = codeListBeen.get(i);
                        ChiCangInfo info = new ChiCangInfo();
                        info.setTodayAdd((i+1)+"");
                        info.setNowPrice(bean.getAlongUserName().replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*"));    //中间4位用*代替
                        info.setBascPrice(df.format(bean.getAlongAmount())+"");
                        info.setCangwei(bean.getAlongTime().substring(0,10));
                        chicangList.add(info);
                    }
                    if (chicangList != null&&chicangList.size()>0){
                        genTouAdapter.setData(chicangList);
                        mGenTouLiat.setAdapter(genTouAdapter);
                        mNoDataGentou.setVisibility(View.GONE);
                    }else {
                        mNoDataGentou.setVisibility(View.VISIBLE);
                    }
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
