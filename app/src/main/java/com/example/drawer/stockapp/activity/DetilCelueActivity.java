package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.CanvasView;
import com.example.drawer.stockapp.customview.CanvasViewTwo;
import com.example.drawer.stockapp.customview.MyScrollView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.NewsInfo;
import com.example.drawer.stockapp.model.StargDetial;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 策略组合详情
 */
public class DetilCelueActivity extends BascActivity implements View.OnClickListener {
    private HashMap<String, Object> map;
    private ArrayList<HashMap<String, Object>> data;
    private StargDetial stargDetial;
    private TextView mGroupTxt, mRecuitmentTime, mNiurenMoney, mMostMoney, mContent, mTarget, mMostDay, mStopLose, mShouyiDiver, mRunTime, mCelueTxt, mFormWhere, mFormName;
    private ImageView mTitleImage, mNiuRenImage, mshouyiImage, mImageOne, mImageTwo, mImageThree,mBackimg;
    private CanvasViewTwo mChartOne, mChartTwo, mChartThree;
    private RelativeLayout mTitleRelat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_celue);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        initWeight();
        getCelueDetail();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColorWhite(this,tintManager);
    }
    /**
     * 设置控件数据
     */
    public void setWidghtData() {
        StargDetial.ResultBean.StrategyDetailBean strategy = stargDetial.getResult().getStrategyDetail();
        StargDetial.ResultBean.StarInfoBean starInfoBean = stargDetial.getResult().getStarInfo();
        ArrayList<StargDetial.ResultBean.FollowInfoBean> followInfoBean = stargDetial.getResult().getFollowInfo();
        mGroupTxt.setText(strategy.getName());
//        if (!TextUtils.isEmpty(strategy.getImgUrl()))
//            Picasso.with(this).load(strategy.getImgUrl()).into(mTitleImage);
        mRecuitmentTime.setText("募集时间：" + strategy.getRecuitmentStartTDay() + " - " + strategy.getRecuitmentEndTimeDay());
        mNiurenMoney.setText(strategy.getStarInvestment()+"");
        mMostMoney.setText(""+strategy.getMostFollow());
        mContent.setText(strategy.getDesc());
        mTarget.setText(strategy.getTargetReturns() + "%");
        mMostDay.setText(strategy.getMaxDay()+"天");
        mStopLose.setText(strategy.getStopLoss() + "%");
        mShouyiDiver.setText(strategy.getShareRatio() + "%");
//        mRunTime.setText("运行期间：" + strategy.getRunStartDay() + " - " + strategy.getRunEndDay());
        if (!TextUtils.isEmpty(starInfoBean.getImgUrl())) {
            Picasso.with(this).load(starInfoBean.getImgUrl()).into(mNiuRenImage);
        }
        mCelueTxt.setText(starInfoBean.getName());
        mFormWhere.setText(starInfoBean.getTitle());


        mChartOne.setRadius(DensityUtils.dp2px(this,40));
        mChartTwo.setRadius(DensityUtils.dp2px(this,40));
        mChartThree.setRadius(DensityUtils.dp2px(this,40));
        setCanvasData(mChartOne, starInfoBean.getMonthlyAverage());
        setCanvasData(mChartTwo, starInfoBean.getPorfolioSucc());
        setCanvasData(mChartThree, starInfoBean.getStockPick());



    }

    public void initWeight() {

        mTitleRelat = (RelativeLayout) findViewById(R.id.celue_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        mBackimg = (ImageView) findViewById(R.id.back_img);
        ImageView mPay = (ImageView) findViewById(R.id.go_to_pay);
        mGroupTxt = (TextView) findViewById(R.id.zuhe_name);    //组合名称
//        mTitleImage = (ImageView) findViewById(R.id.title_image);   //头像图
        mRecuitmentTime = (TextView) findViewById(R.id.muji_time);    //募集时间
        mNiurenMoney = (TextView) findViewById(R.id.niuren_money);    //牛人投资金额
        mMostMoney = (TextView) findViewById(R.id.zuiduo_money);   //最多跟投
        mContent = (TextView) findViewById(R.id.content_celue);    //内容描述
        mTarget = (TextView) findViewById(R.id.goal_shouyi);    //目标收益
        mMostDay = (TextView) findViewById(R.id.max_long_time);   //最长期限
        mStopLose = (TextView) findViewById(R.id.zhishunxian_txt);   //止损线
        mShouyiDiver = (TextView) findViewById(R.id.shouyi_txt);   //收益分成
//        mRunTime = (TextView) findViewById(R.id.run_time_txt);     //运行时间
        mNiuRenImage = (ImageView) findViewById(R.id.celue_item_head_imgae);    //历史牛人头像
        mCelueTxt = (TextView) findViewById(R.id.people_name);     //策略人名称
        mFormWhere = (TextView) findViewById(R.id.from_where);      //来自哪儿
//        mFormName = (TextView) findViewById(R.id.from_name);       //称号
//        mshouyiImage = (ImageView) findViewById(R.id.shouyi_image);   //当前收益
//        mImageOne = (ImageView) findViewById(R.id.image_one);    //实时动态
//        mImageTwo = (ImageView) findViewById(R.id.image_two);    //自动跟投
//        mImageThree = (ImageView) findViewById(R.id.image_three);    //交流经验

        mChartOne = (CanvasViewTwo) findViewById(R.id.chart1);
        mChartTwo = (CanvasViewTwo) findViewById(R.id.chart2);
        mChartThree = (CanvasViewTwo) findViewById(R.id.chart3);

        ImageView mCollect = (ImageView) findViewById(R.id.cellect_icon);     //收藏

        MyScrollView mScrollView = (MyScrollView) findViewById(R.id.scroll_test);

        mScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY>100){
                    mTitleRelat.setBackgroundResource(R.color.write_color);
                    tintManager.setStatusBarTintResource(R.color.write_color);
                    ManagerUtil.FlymeSetStatusBarLightMode(getWindow(),true);
                    ManagerUtil.MIUISetStatusBarLightMode(getWindow(),true);
                    mGroupTxt.setTextColor(getResources().getColor(android.R.color.background_dark));
                    mRecuitmentTime.setTextColor(getResources().getColor(android.R.color.background_dark));
                    mBackimg.setImageResource(R.mipmap.arrowlift);
                }else {
                    mTitleRelat.setBackgroundResource(android.R.color.transparent);
                    tintManager.setStatusBarTintResource(android.R.color.transparent);
                    ManagerUtil.FlymeSetStatusBarLightMode(getWindow(),false);
                    ManagerUtil.MIUISetStatusBarLightMode(getWindow(),false);
                    mGroupTxt.setTextColor(getResources().getColor(R.color.write_color));
                    mRecuitmentTime.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    mBackimg.setImageResource(R.mipmap.w_back);
                }
            }
        });

//        RadioGroup mGroup = (RadioGroup) findViewById(R.id.wisdom_group);

//        //退款保障
//        MyListView myListView = (MyListView) findViewById(R.id.my_listview_refund);
//        ReturnAdapter adapter = new ReturnAdapter(this);
//        adapter.setData(setReturnData());
//        myListView.setAdapter(adapter);

//        //调仓页面（第一次进入时加载）
//        MyListFragment fragment = MyListFragment.newInstance("调仓", "");
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.fragment_layout, fragment);
//        transaction.commit();

        mBackimg.setOnClickListener(this);
//        mGroup.setOnCheckedChangeListener(new RadioGroupListener());
        mPay.setOnClickListener(this);
        mCollect.setOnClickListener(this);

    }


    /**
     * 获取策略详情
     */
    public void getCelueDetail() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,String> map = new HashMap<>();
                map.put("Id", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.StrategyDetail_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                if (!TextUtils.isEmpty(message)) {
                    Gson gson = new Gson();
                    stargDetial = gson.fromJson(message, StargDetial.class);
                    if (stargDetial.getHead().getStatus() == 0) {
                        setWidghtData();
                    }
                }
            }
        }.execute();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.go_to_pay:
                Intent intent = new Intent(this, FollowActivity.class);
                startActivity(intent);
                break;
            case R.id.cellect_icon:
                Toast.makeText(this,"收藏成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //设置历史业绩中的比例和颜色
    public void setCanvasData(CanvasViewTwo canvasView, double num) {
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

    /**
     * 设置退款保障数据
     *
     * @return
     */
    public ArrayList<NewsInfo> setReturnData() {
        ArrayList<NewsInfo> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
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
//                    MyListFragment fragment = MyListFragment.newInstance("调仓", "");
//                    transaction.replace(R.id.fragment_layout, fragment);
                    break;
                case R.id.zuhe_txt:
//                    MyListFragmentTwo fragmentTwo = MyListFragmentTwo.newInstance("持仓","");
//                    transaction.replace(R.id.fragment_layout,fragmentTwo);
//                    fragment = MyListFragment.newInstance("持仓", "");
//                    transaction.replace(R.id.fragment_layout, fragment);
                    break;
                case R.id.my_celue_txt:
//                    MyListFragmentThree fragmentThree = MyListFragmentThree.newInstance("跟投人","");
//                    transaction.replace(R.id.fragment_layout,fragmentThree);
//                    fragment = MyListFragment.newInstance("跟投人", "");
//                    transaction.replace(R.id.fragment_layout, fragment);
                    break;
                case R.id.my_zuhe_txt:
//                    MyListFragmentFour fragmentFour = MyListFragmentFour.newInstance("交流区","");
//                    transaction.replace(R.id.fragment_layout,fragmentFour);
//                    fragment = MyListFragment.newInstance("交流区", "");
//                    transaction.replace(R.id.fragment_layout, fragment);
                    break;
            }
            transaction.commit();
        }
    }
}
