package com.example.drawer.stockapp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.AgreementWebActivity;
import com.example.drawer.stockapp.activity.LoginActivity;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.MyDynamicActivity;
import com.example.drawer.stockapp.activity.SendDynamicActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.WebViewActivity;
import com.example.drawer.stockapp.adapter.IndexAdapter;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.TrendsAdapter;
import com.example.drawer.stockapp.customview.PagerSlidingTabStrip;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.listener.OnFragmentInteractionListener;
import com.example.drawer.stockapp.listener.TypeCallBack;
import com.example.drawer.stockapp.model.BannerInfo;
import com.example.drawer.stockapp.model.DynamicsInfo;
import com.example.drawer.stockapp.model.HeadMassageInfo;
import com.example.drawer.stockapp.model.IndexMarkInfo;
import com.example.drawer.stockapp.model.NewsInfo;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.scxh.slider.library.Indicators.PagerIndicator;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FirstNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstNewsFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener,XListView.OnXScrollListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String isFlashType = "com.stock.sendtype";   //是否是动态发送
    private View mView,mSliderVIew,dongtaiView,zixunView;
    private ViewPager mPager;
    private HeadMassageInfo headMassageInfo;
    private DynamicsInfo dynamicsInfo;
    private TrendsAdapter trendsAdapter;
    private RelativeLayout mTitleRelat;
    private XListView mDongTaiList;
    private  String token;
    private int page,dongTaiPage;
    private ImageView mImgHead,mMessage,mSendImg,mBackgroud,loadingFailed,loadingFailedDongtai;
    private Boolean isFlag = false;
    private String[] images;
    private String[] strings = {""};
    private Boolean isFlash = false;   //是否刷新动态列表
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private Handler mHandler;
    private int mDongTaiType;   //动态跳转类型
    private SharedPreferences sharedPreferences;
    private ApplyHttpThread thread;
    private ArrayList<NewsInfo> listInfoSave;
    private ArrayList<TrendsInfo> trendsInfosSave;
    private List<IndexMarkInfo.ResultBean.MarketDataBean> MarketDataSave;

    public FirstNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstNewsFragment newInstance(String param1, String param2) {
        FirstNewsFragment fragment = new FirstNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mHandler = new Handler();
        sharedPreferences = ShapePreferenceManager.getMySharedPreferences(getActivity());

        /**
         * 检测更新
         */
//        ManagerUtil managerUtil = new ManagerUtil(getActivity());
//        managerUtil.checkUserCode(getActivity());


    /**
    * type广播（动态）
    */
        IntentFilter filter = new IntentFilter();
        // 向过滤器中添加action
        filter.addAction(isFlashType);
        // 注册广播
        getContext().registerReceiver(isFlashBroad, filter);

    }

    /**
     * 广播接收者
     */
    private BroadcastReceiver isFlashBroad = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            isFlash = true;
        }

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isFlashBroad != null){
            getContext().unregisterReceiver(isFlashBroad);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        if (headMassageInfo == null) {
            mView = inflater.inflate(R.layout.fragment_first_news, container, false);
            initWight();
            initData();
        if (headMassageInfo == null) {
            listInfoSave = new ArrayList<>();   //保存数据，下次进入时不必加载
            //新闻列表加载
            GetNewsListAsyn getNewsListAsyn = new GetNewsListAsyn();
            getNewsListAsyn.execute(page + "");
            } else {

            indexAdapter.setData(listInfoSave);
            mlist.setAdapter(indexAdapter);
            }
        if (MarketDataSave == null){
            //指数加载
            IndexAsyn indexAsyn = new IndexAsyn();
            indexAsyn.execute();
        }else {
            initListData(MarketDataSave);
        }

        if (images == null){
            //banner加载
            GetBannerInfo getBannerInfo = new GetBannerInfo();
            getBannerInfo.execute();
        }else {
            getSliderLayoutView(images, strings);
        }

        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        isNow = true;
        if (thread == null){
            thread = new ApplyHttpThread();
            thread.start();
        }

        token = sharedPreferences.getString(ShapePreferenceManager.TOKEN,null);
        switch (mPager.getCurrentItem()){
            case 0:
                SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
                ManagerUtil.setStataBarColorWhite(getActivity(),tintManager);
                break;
            case 1:
                tintManager = ManagerUtil.newInstance(getActivity());
                ManagerUtil.setStataBarColor(getActivity(),tintManager);
                if (!TextUtils.isEmpty(token)){
                    if (isFlash){    //判定是否发表动态返回
                        dymnicesData(token,dongTaiPage);
                        isFlash = false;
                    }else if (dynamicsInfo == null){
                        trendsInfosSave = new ArrayList<>();
                        dymnicesData(token,dongTaiPage);    //
                    }else {
                        trendsAdapter.setData(trendsInfosSave);
                        mDongTaiList.setAdapter(trendsAdapter);
                    }
                    mBackgroud.setVisibility(View.GONE);
                }else {
                    mBackgroud.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    private PagerSlidingTabStrip tabs;
    /**
     * 初始化控件
     */
    public void initWight(){
        mTitleRelat = (RelativeLayout) mView.findViewById(R.id.all_order_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(),50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()),0,0);
        mTitleRelat.setLayoutParams(params);

        mImgHead = (ImageView) mView.findViewById(R.id.info_img);   //点击头像
        mMessage = (ImageView) mView.findViewById(R.id.pop_item_img);

        mSendImg = (ImageView) mView.findViewById(R.id.send_dynamic);   //发送动态
        mSendImg.setVisibility(View.GONE);



        tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.first_group);


        mPager = (ViewPager) mView.findViewById(R.id.zixun_content_pager);   //viewpager

        mPager.setOnPageChangeListener(new TabOnPageChangeListener());


        mImgHead.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mSendImg.setOnClickListener(this);

    }

    /**
     * 初始化适配器数据
     */
    public void initData(){

        List<View> viewList = new ArrayList<View>();
        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        mSliderVIew = mInflater.inflate(R.layout.imageslider_layout, null);    //第一个head imageSlider

        tintManager = ManagerUtil.newInstance(getActivity());
        tintManager.setStatusBarTintEnabled(true);
//
//        mTitleRelat.getBackground().setAlpha(255);
//        tintManager.setTintAlpha(1);
        tintManager.setStatusBarTintResource(R.color.write_color);
        mTitleRelat.setBackgroundResource(R.color.write_color);

        zixunView = mInflater.inflate(R.layout.zixun_layout,null);     //资讯大框架在这儿
        dongtaiView = mInflater.inflate(R.layout.dongtai_layout,null);   //动态框架在这儿

        loadingFailed = (ImageView) zixunView.findViewById(R.id.loading_failed);   //加载失败显示图
        loadingFailedDongtai = (ImageView) dongtaiView.findViewById(R.id.loading_failed_two);  //动态加载失败
        loadingFailed.setOnClickListener(this);
        loadingFailedDongtai.setOnClickListener(this);

        viewList.add(zixunView);
        viewList.add(dongtaiView);

        mlist = (XListView) zixunView.findViewById(R.id.listview_zixun);
        mlist.setPullLoadEnable(true);    //设置上拉加载
        mlist.addHeaderView(mSliderVIew);
        indexAdapter = new IndexAdapter(getActivity());
        mlist.setOnItemClickListener(this);
        mlist.setOnScrollListener(this);

        mBackgroud = (ImageView) dongtaiView.findViewById(R.id.img_no_login);     //未登陆显示图片
        mBackgroud.setOnClickListener(this);

//
        ArrayList<String> titles = new ArrayList<>();
        titles.add("资讯");
        titles.add("动态");
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList,titles);
        mPager.setAdapter(adapter);
        tabs.setViewPager(mPager);

        mDongTaiList = (XListView) dongtaiView.findViewById(R.id.fondtai_listview);
        trendsAdapter = new TrendsAdapter(getActivity(),callBack);
        mDongTaiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MyDynamicActivity.class);
                intent.putExtra(MyDynamicActivity.DYNAMICINFO,trendsInfosSave.get(i-1).getId());
                startActivity(intent);
            }
        });
        mDongTaiList.setPullLoadEnable(true);    //设置上拉加载
        mDongTaiList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                dongTaiPage = 0;
                dymnicesData(token,dongTaiPage);

            }

            @Override
            public void onLoadMore() {
                dongTaiPage++;
                dymnicesData(token,dongTaiPage);
            }
        });

        mlist.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 0;
                GetNewsListAsyn getNewsListAsyn = new GetNewsListAsyn();
                getNewsListAsyn.execute(page+"");


            }

            @Override
            public void onLoadMore() {
                page++;
                GetNewsListAsyn getNewsListAsyn = new GetNewsListAsyn();
                getNewsListAsyn.execute(page+"");

            }
        });

    }

    private XListView mlist;
    private IndexAdapter indexAdapter;

    /**
     * 设置首页数据
     */
    public void getDataZixun(){
        ArrayList<NewsInfo> listInfo = setNewsInfo();
        if (page == 0){
            listInfoSave.clear();
            indexAdapter.setData(listInfo);
            mlist.setAdapter(indexAdapter);
        }else if (page>0&&listInfo.size()>0){
            indexAdapter.addData(listInfo);
        }else {
            Toast.makeText(getActivity(),"已经到底了哦",Toast.LENGTH_SHORT).show();
        }
        listInfoSave.addAll(listInfo);

    }
    /**
     * /初始化股票数据（资讯）
     */
    public void initListData(List<IndexMarkInfo.ResultBean.MarketDataBean> MarketData){

        LinearLayout layout = (LinearLayout) mSliderVIew.findViewById(R.id.first_lin);   //scrollview下的布局
        layout.setClickable(true);
        layout.removeAllViews();
        DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数

        for (int i = 0;i<MarketData.size();i++){
            double addOrDec = Double.parseDouble(df.format(MarketData.get(i).getVariabilityPoints()));
            LinearLayout layout1 = new LinearLayout(getContext());
            LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lay.weight = 1;
            layout1.setBackgroundColor(getResources().getColor(R.color.write_color));
            layout1.setLayoutParams(lay);
            layout1.setGravity(Gravity.CENTER);
            layout1.setOrientation(LinearLayout.VERTICAL);

            TextView txt = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa.setMargins(10,10,10,10);
            txt.setLayoutParams(aaaa);
            txt.setText( MarketData.get(i).getName());
            txt.setGravity(Gravity.CENTER);
            txt.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格

            layout1.addView(txt);
            TextView txt1 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa1.setMargins(10,10,10,10);
            txt1.setLayoutParams(aaaa1);
            txt1.setText( Double.parseDouble(df.format(MarketData.get(i).getPoints()))+"");
            txt1.setGravity(Gravity.CENTER);
            txt1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
            if (addOrDec>0){
                txt1.setTextColor(getActivity().getResources().getColor(R.color.red));
            }else {
                txt1.setTextColor(getActivity().getResources().getColor(R.color.green_color));
            }
            txt1.setTextSize(18);

            layout1.addView(txt1);

            TextView txt2 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa2.setMargins(10,10,10,10);
            txt2.setLayoutParams(aaaa2);
            txt2.setGravity(Gravity.CENTER);
            txt2.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
            if (addOrDec>0){
                txt2.setText( ""+addOrDec+"   "+ Double.parseDouble(df.format(MarketData.get(i).getVariabilityRate()))+"%");
                txt2.setTextColor(getActivity().getResources().getColor(R.color.red));
            }else {
                txt2.setText( ""+addOrDec+"   "+ Double.parseDouble(df.format(MarketData.get(i).getVariabilityRate()))+"%");
                txt2.setTextColor(getActivity().getResources().getColor(R.color.green_color));
            }
            txt2.setTextSize(12);
            layout1.addView(txt2);

            layout.addView(layout1);
        }
    }

    protected SystemBarTintManager tintManager;
    private int mCurrentfirstVisibleItem = 0;
    private SparseArray recordSp = new SparseArray(0);


    /**
     * 初始化动态数据
     */
    public void dymnicesData(final String token, final int page){

        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,String> map = new HashMap<>();
                 map.put("PageIndex",page+"");
                 map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer(token,map,HttpManager.SHARE_LIST_URL);

                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                onLoadDt();

                String message = (String) o;
                if (!TextUtils.isEmpty(message)){
                    Gson gson = new Gson();
                    dynamicsInfo = gson.fromJson(message, DynamicsInfo.class);
                    if (dynamicsInfo.getHead().getStatus()==0){
                        mBackgroud.setVisibility(View.GONE);
                        ArrayList<TrendsInfo> trendList = initdongtaiData();
                        if (dongTaiPage == 0){
                            trendsInfosSave=trendList;
                            trendsAdapter.setData(trendList);
                            mDongTaiList.setAdapter(trendsAdapter);
                        }else if (dongTaiPage >0 &&trendList.size()>0){
                            trendsInfosSave.addAll(trendList);
                            trendsAdapter.addData(trendList);
                        }else {
                            Toast.makeText(getActivity(),"没有更多了哦",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        mBackgroud.setVisibility(View.VISIBLE);
                    }

                }else {
                    loadingFailedDongtai.setVisibility(View.VISIBLE);
                }
            }
        }.execute();
    }
    /**
     * 初始化动态数据
     */
    public ArrayList<TrendsInfo> initdongtaiData(){
        ArrayList<TrendsInfo> trendsInfos = new ArrayList<>();
        List<DynamicsInfo.ResultBean.ShareBean> share = dynamicsInfo.getResult().getShare();
        for (int i = 0;i<share.size();i++){
            TrendsInfo info = new TrendsInfo();
            ArrayList<String> list = new ArrayList<>();
            DynamicsInfo.ResultBean.ShareBean ben = share.get(i);
            info.setId(ben.getId());
            info.setShareUrl(ben.getImgUrl());
            info.setImage(ben.getImgUrl());
            info.setName(ben.getNickName());
            info.setContent(ben.getContent());
            for (int j = 0;j<ben.getImgs().size();j++){
                list.add(ben.getImgs().get(j));
            }
            info.setContentImage(list);
            info.setTime(ben.getUpdateTime());
            info.setZhuanFaNum(ben.getForward());
            info.setCommentNum(ben.getComments());
            info.setGoodNum(ben.getLikes());
            info.setCollect(ben.isHasFavorites());
            info.setLikes(ben.isHasLike());
            trendsInfos.add(info);

        }

        return trendsInfos;

    }





    /**
     * 初始化新闻数据
     * @return
     */
    public ArrayList<NewsInfo> setNewsInfo(){
        ArrayList<NewsInfo> newsInfos = new ArrayList<>();
        List<HeadMassageInfo.ResultBean.NewsBean> newsBeen = headMassageInfo.getResult().getNews();
        for (int i = 0;i<newsBeen.size();i++){
            NewsInfo info = new NewsInfo();
            HeadMassageInfo.ResultBean.NewsBean newsBean = newsBeen.get(i);
            info.setTitle(newsBean.getTitle());
            info.setContent(newsBean.getSecondTitle());
            info.setTime(newsBean.getUpdateTime());
            info.setPeopleNum(newsBean.getComments()+"");
            info.setLinkUrl(newsBean.getId()+"");
            if (newsBean.getBannerUrl().size()>1){
                info.setType(2);
                ArrayList<String> list = new ArrayList<>();
                for (int j = 0;j<newsBean.getBannerUrl().size();j++){
                    list.add(newsBean.getBannerUrl().get(j)+"");
                }
                info.setImgaes(list);
            }else {
                info.setImage(newsBean.getBannerUrl().get(0)+"");
                info.setType(1);
            }
            newsInfos.add(info);
        }
        return newsInfos;
    }
        /**
        * imageSlider控件加入
        * */
    public void getSliderLayoutView(String[] mImage, final String[] mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIew.findViewById(R.id.image_slider_layout);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIew.findViewById(R.id.custom1_indicator);

        mSliderLayout.removeAllSliders();
        int length = mImage.length;
        for (int i = 0; i < length; i++) {
            TextSliderView sliderView = new TextSliderView(getContext());   //向SliderLayout中添加控件
            sliderView.image(mImage[i]);
//            sliderView.description(mString[i]);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getContext(), AgreementWebActivity.class);
                    intent.putExtra(AgreementWebActivity.URLTYPE,3);
                    intent.putExtra(AgreementWebActivity.URL,mString[finalI]);
                    startActivity(intent);
                }
            });
            mSliderLayout.addSlider(sliderView);
        }
//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);  //将小圆点设置到右下方
        mSliderLayout.setCustomIndicator(pagerIndicator);  //将小圆点设置到右下方(自定义控件指示器)

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.info_img:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.pop_item_img:   //搜索
                intent = new Intent(getActivity(), SerchActivity.class);
                startActivity(intent);
                break;
            case R.id.send_dynamic:   //发表动态
                if (!TextUtils.isEmpty(token)){
                    intent = new Intent(getContext(), SendDynamicActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.img_no_login:   //未登录
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.loading_failed:   //加载失败图片
                loadingFailed.setVisibility(View.GONE);
                GetNewsListAsyn getNewsListAsyn = new GetNewsListAsyn();
                getNewsListAsyn.execute(page+"");

                GetBannerInfo getBannerInfo = new GetBannerInfo();
                getBannerInfo.execute();
                break;
            case R.id.loading_failed_two:
                loadingFailedDongtai.setVisibility(View.GONE);
                dymnicesData(token,dongTaiPage);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()){
            case R.id.listview_zixun:
                NewsInfo info = (NewsInfo) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.URLID,info.getLinkUrl());
                startActivity(intent);
                break;
        }
    }

    private void onLoadZx() {
        mlist.stopRefresh();
        mlist.stopLoadMore();
    }
    private void onLoadDt() {
        mDongTaiList.stopRefresh();
        mDongTaiList.stopLoadMore();
    }


    @Override
    public void onXScrolling(View view) {

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            switch (absListView.getId()){
                case R.id.listview_zixun:
                    mCurrentfirstVisibleItem = i;
                    View firstView = absListView.getChildAt(0);
                    if (null != firstView) {
                        ItemRecod itemRecord = (ItemRecod) recordSp.get(i);
                        if (null == itemRecord) {
                            itemRecord = new ItemRecod();
                        }
                        itemRecord.height = firstView.getHeight();
                        itemRecord.top = firstView.getTop();
                        recordSp.append(i, itemRecord);
                        //动态返回时此代码有用，其余时候没用
                        if (getScrollY()>100){
                            tintManager.setStatusBarTintResource(R.color.write_color);
                            tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                        }
                        //设置滑动颜色渐变（0-511）
                        if (getScrollY() <= 100) {
                            //设置渐变
//                        mTitleRelat.getBackground().setAlpha(getScrollY() / 2);
//                        tintManager.setTintAlpha((float) getScrollY() / 510);
                            //不设置渐变
                            mTitleRelat.getBackground().setAlpha(1);
                            tintManager.setTintAlpha(0);

                            ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
                            ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
                            tabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.write_color));
                            mImgHead.setImageResource(R.mipmap.message_white);
                            mMessage.setImageResource(R.mipmap.search_white);
                            isFlag = true;
                        } else {       //只执行一次就好
                            mTitleRelat.getBackground().setAlpha(255);
                            tintManager.setTintAlpha(1);
                            ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                            ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                            tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                            mImgHead.setImageResource(R.mipmap.message_black);
                            mMessage.setImageResource(R.mipmap.searchblack);
                            isFlag = false;
                        }
                    }
                    break;

            }

    }
    //获取偏移距离
    private int getScrollY() {
        int height = 0;
        for (int i = 0; i < mCurrentfirstVisibleItem; i++) {
            ItemRecod itemRecod = (ItemRecod) recordSp.get(i);
            if (itemRecod != null)
                height += itemRecod.height;
        }
        ItemRecod itemRecod = (ItemRecod) recordSp.get(mCurrentfirstVisibleItem);
        if (null == itemRecod) {
            itemRecod = new ItemRecod();
        }
        return height - itemRecod.top;
    }
class ItemRecod {
    int height = 0;
    int top = 0;
}

    /**
     * 页卡改变事件
     */
    private class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {

        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mSendImg.setVisibility(View.GONE);
                    mlist.setOnScrollListener(FirstNewsFragment.this);
                    mDongTaiList.setOnScrollListener(null);
                    break;
                case 1:
                    mSendImg.setVisibility(View.VISIBLE);
                    mlist.setOnScrollListener(null);     //此处为了不让滚动监听事件冲突
                    mDongTaiList.setOnScrollListener(FirstNewsFragment.this);
                    mTitleRelat.getBackground().setAlpha(255);
//                    tintManager.setTintAlpha(1);
                    tintManager.setStatusBarTintColor(getContext().getResources().getColor(R.color.write_color));
                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);

                    mImgHead.setImageResource(R.mipmap.message_black);
                    mMessage.setImageResource(R.mipmap.searchblack);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));

                    if (!TextUtils.isEmpty(token)){
                        if (dynamicsInfo == null) {
                            dymnicesData(token, dongTaiPage);    //
                        }
                        mBackgroud.setVisibility(View.GONE);
                    }else {
                        mBackgroud.setVisibility(View.VISIBLE);
                    }
                    break;

            }
        }
    }

    private TypeCallBack callBack = new TypeCallBack() {
        @Override
        public void setDongTaiType(int i,int type) {
            Intent intent = new Intent(getActivity(), MyDynamicActivity.class);
            intent.putExtra(MyDynamicActivity.DYNAMICINFO,dynamicsInfo.getResult().getShare().get(i));
            intent.putExtra(MyDynamicActivity.TYPE,type);
            startActivity(intent);
        }

        @Override
        public void setCollectOrLikes(int i,String likeOrfor) {
            LikeOrCollectAsyn likeOrCollectAsyn = new LikeOrCollectAsyn();
            likeOrCollectAsyn.execute(dynamicsInfo.getResult().getShare().get(i).getId(),likeOrfor,token);
        }
    };

    /**
     * 搜索收藏、点赞
     */
    private class LikeOrCollectAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("Id", strings[0]);
            map.put("Type", strings[1]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[2], map, HttpManager.Favorites_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONObject object = new JSONObject(s);
                    if (object.has("Head")){
                        JSONObject head = object.getJSONObject("Head");
                        if (head.getString("Status").equals("1")){
//                            Toast.makeText(context,"发布失败",Toast.LENGTH_SHORT).show();
                        }else {
//                            Toast.makeText(context,"发布成功",Toast.LENGTH_SHORT).show();
//                            dymnicesData(token);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isNow = false;
//        thread.interrupt();
        thread = null;
    }



    private Boolean isNow = true;   //是否停止线程
    /**
     * 执行循环请求
     */
    private class ApplyHttpThread extends Thread{


        @Override
        public void run() {
            super.run();
            while (isNow){
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                IndexAsyn asyn = new IndexAsyn();
                asyn.execute();
            }
        }
    }

    /**
     * 获取指数信息
     */
    private class IndexAsyn extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {

            String message = HttpManager.newInstance().getHttpData(HttpManager.MarketData_URL);
            return message;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                IndexMarkInfo indexMarkInfo = gson.fromJson(s,IndexMarkInfo.class);
                if (indexMarkInfo.getHead().getStatus() == 0){
                    MarketDataSave = indexMarkInfo.getResult().getMarketData();
                    initListData(MarketDataSave);
                }
            }
        }
    }

    /**
     * 获取banner图
     */
    private class GetBannerInfo extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            HashMap<String, String> map = new HashMap<>();
            map.put("PageIndex", "0");
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("", map, HttpManager.BannerList_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)) {
                Gson gson = new Gson();
                BannerInfo bannerInfo = gson.fromJson(s, BannerInfo.class);
                if (bannerInfo.getHead().getStatus() == 0) {
                    SharedPreferences.Editor edit = ShapePreferenceManager.getImageSharePreference(getActivity()).edit();
                    Set<String> set = new HashSet<>();
                    List<BannerInfo.ResultBean.BannerUrlBean> size = bannerInfo.getResult().getBannerUrl();
                    images = new String[3];
                    strings = new String[3];
                    for (int i = 0; i < size.size(); i++) {
                        if (i<3){
                            images[i] = size.get(i).getBannerUrl();
                            strings[i] = size.get(i).getTargetUrl();     //bar图
                        }else {
                            set.add(size.get(i).getBannerUrl());
                        }
                    }
                    edit.putStringSet(ShapePreferenceManager.IMAGE_CELUE,set);
                    edit.commit();
                    getSliderLayoutView(images, strings);
                }
            }
        }

    }
    /**
     * 获取新闻列表
     */
    private class GetNewsListAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("PageIndex", strings[0]);
            map.put("PageCount","0");
            map.put("PageSize", "0");
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("", map, HttpManager.NewsList_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onLoadZx();

            if (!TextUtils.isEmpty(s)) {
                Gson gson = new Gson();
                headMassageInfo = gson.fromJson(s, HeadMassageInfo.class);
                if (headMassageInfo.getHead().getStatus() == 0) {
                    getDataZixun();   //解析数据稍后放开
                }else {
                    Toast.makeText(getActivity(),"还没找到相关信息哦",Toast.LENGTH_SHORT).show();
                }
            } else {
                loadingFailed.setVisibility(View.VISIBLE);
            }
        }
    }
}

