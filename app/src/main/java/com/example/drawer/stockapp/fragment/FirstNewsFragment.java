package com.example.drawer.stockapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.MyDynamicActivity;
import com.example.drawer.stockapp.activity.SendDynamicActivity;
import com.example.drawer.stockapp.activity.WebViewActivity;
import com.example.drawer.stockapp.adapter.IndexAdapter;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.TrendsAdapter;
import com.example.drawer.stockapp.customview.PagerSlidingTabStrip;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.listener.OnFragmentInteractionListener;
import com.example.drawer.stockapp.model.DynamicsInfo;
import com.example.drawer.stockapp.model.HeadMassageInfo;
import com.example.drawer.stockapp.model.NewsInfo;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.scxh.slider.library.Indicators.PagerIndicator;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FirstNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstNewsFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener,XListView.IXListViewListener,XListView.OnXScrollListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View mView,mSliderVIew,dongtaiView,zixunView;
    private ViewPager mPager;
    private HeadMassageInfo headMassageInfo;
    private DynamicsInfo dynamicsInfo;
    private TrendsAdapter trendsAdapter;
    private RelativeLayout mTitleRelat;
    private ListView mDongTaiList;
    private ImageView mImgHead,mMessage,mSendImg;
    private Boolean isFlag = false;
    private String[] images = {""};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            mView = inflater.inflate(R.layout.fragment_first_news, container, false);
        initWight();
        initData();
//        if (headMassageInfo == null){
        getMessageInfo();
        dymnicesData();
//        }
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (mPager.getCurrentItem()){
            case 0:
                SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
                ManagerUtil.setStataBarColorWhite(getActivity(),tintManager);
                break;
            case 1:
                tintManager = ManagerUtil.newInstance(getActivity());
                ManagerUtil.setStataBarColor(getActivity(),tintManager);
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
                100);
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
     * 获取资讯信息
     */
    public void getMessageInfo(){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                String message = HttpManager.newInstance().getHttpData(HttpManager.Information_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                Log.d("tag","message--"+message);
                if (!TextUtils.isEmpty(message)){
                    Gson gson = new Gson();
                    headMassageInfo = gson.fromJson(message, HeadMassageInfo.class);
                    Log.d("tag","---"+headMassageInfo.getHead().getStatus());
                    if (headMassageInfo.getHead().getStatus()==0){
                        ArrayList<HeadMassageInfo.ResultBean.BannerUrlBean> size = headMassageInfo.getResult().getBannerUrl();
                        images = new String[size.size()];
                        for (int i = 0;i<size.size();i++){
//                            images[i] = headMassageInfo.getResult().getBannerUrl().get(i);
                            images[i] = "http://m2.quanjing.com/2m/ivary_photorf001/stp005_00051.jpg";     //bar图
                        }
                        initListData();
                    }

                }
            }
        }.execute();
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
        mTitleRelat.setBackgroundResource(R.color.write_color);
        tintManager.setStatusBarTintResource(R.color.write_color);

        zixunView = mInflater.inflate(R.layout.zixun_layout,null);     //资讯大框架在这儿
        dongtaiView = mInflater.inflate(R.layout.dongtai_layout,null);   //动态框架在这儿

        viewList.add(zixunView);
        viewList.add(dongtaiView);

        ImageView mBackgroud = (ImageView) dongtaiView.findViewById(R.id.img_no_login);     //未登陆显示图片

//
        ArrayList<String> titles = new ArrayList<>();
        titles.add("资讯");
        titles.add("动态");
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList,titles);
        mPager.setAdapter(adapter);
        tabs.setViewPager(mPager);
//        initListData();

        mDongTaiList = (ListView) dongtaiView.findViewById(R.id.fondtai_listview);
        trendsAdapter = new TrendsAdapter(getActivity());
        mDongTaiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MyDynamicActivity.class);
                intent.putExtra(MyDynamicActivity.DYNAMICINFO,dynamicsInfo.getResult().getShare().get(i));
                startActivity(intent);
            }
        });


    }
    private XListView mlist;
    //初始化listview数据（资讯）
    public void initListData(){
        getSliderLayoutView(images,null);

        mlist = (XListView) zixunView.findViewById(R.id.listview_zixun);
        mlist.setPullLoadEnable(true);    //设置上拉加载
        mlist.addHeaderView(mSliderVIew);

        LinearLayout layout = (LinearLayout) mSliderVIew.findViewById(R.id.first_lin);   //scrollview下的布局

        List<HeadMassageInfo.ResultBean.MarketDataBean> marketDataBeen = headMassageInfo.getResult().getMarketData();
        for (int i = 0;i<marketDataBeen.size();i++){
            double addOrDec = marketDataBeen.get(i).getVariabilityPoints();
            LinearLayout layout1 = new LinearLayout(getActivity());
            LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lay.weight = 1;
            layout1.setLayoutParams(lay);
            layout1.setGravity(Gravity.CENTER);
            layout1.setOrientation(LinearLayout.VERTICAL);

            TextView txt = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa.setMargins(10,10,10,10);
            txt.setLayoutParams(aaaa);
            txt.setText( marketDataBeen.get(i).getName());
            txt.setGravity(Gravity.CENTER);
            txt.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格

            layout1.addView(txt);
            TextView txt1 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa1.setMargins(10,10,10,10);
            txt1.setLayoutParams(aaaa1);
            txt1.setText( marketDataBeen.get(i).getPoints()+"");
            txt1.setGravity(Gravity.CENTER);
            txt1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
            if (addOrDec>0){
                txt1.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
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
                txt2.setText( "+"+addOrDec+"+"+ marketDataBeen.get(i).getVariabilityRate()+"%");
                txt2.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
            }else {
                txt2.setText( ""+addOrDec+""+ marketDataBeen.get(i).getVariabilityRate()+"%");
                txt2.setTextColor(getActivity().getResources().getColor(R.color.green_color));
            }
            layout1.addView(txt2);

            layout.addView(layout1);
        }


        IndexAdapter indexAdapter = new IndexAdapter(getActivity());
        indexAdapter.setData(setNewsInfo());
        mlist.setAdapter(indexAdapter);

        mlist.setOnItemClickListener(this);
        mlist.setXListViewListener(this);

        mlist.setOnScrollListener(this);
    }
    protected SystemBarTintManager tintManager;
    private int mCurrentfirstVisibleItem = 0;
    private SparseArray recordSp = new SparseArray(0);


    /**
     * 初始化动态数据
     */
    public void dymnicesData(){

        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,String> map = new HashMap<>();
                 map.put("PageIndex", "0");
                 map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.SHARE_LIST_URL);

                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                if (!TextUtils.isEmpty(message)){
                    Gson gson = new Gson();
                    dynamicsInfo = gson.fromJson(message, DynamicsInfo.class);
                    if (dynamicsInfo.getHead().getStatus()==0){
                        trendsAdapter.setData(initdongtaiData());
                        mDongTaiList.setAdapter(trendsAdapter);


                    }

                }
            }
        }.execute();
    }
    /**
     * 初始化动态数据
     */
    public ArrayList<TrendsInfo> initdongtaiData(){
        ArrayList<TrendsInfo> trendsInfos = new ArrayList<>();
        ArrayList<DynamicsInfo.ResultBean.ShareBean> share = dynamicsInfo.getResult().getShare();
        for (int i = 0;i<share.size();i++){
            TrendsInfo info = new TrendsInfo();
            ArrayList<String> list = new ArrayList<>();
            DynamicsInfo.ResultBean.ShareBean ben = share.get(i);
            info.setImage(ben.getImgUrl());
            info.setName(ben.getUserName());
            info.setContent(ben.getContent());
            for (int j = 0;j<ben.getImgs().size();j++){
                list.add(ben.getImgs().get(j));
            }
            info.setContentImage(list);
            info.setTime(ben.getUpdateTime());
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
        for (int i = 0;i<10;i++){
            NewsInfo info = new NewsInfo();
            HeadMassageInfo.ResultBean.NewsBean newsBean = newsBeen.get(0);
            info.setTitle(newsBean.getTitle());
            info.setTime(newsBean.getUpdateTime());
            info.setPeopleNum(newsBean.getComments()+"");
            info.setImage(newsBean.getBannerUrl().get(0));
            if (i==5||i==9){
                info.setType(2);
            }else {
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
                onButtonPressed();
                break;
            case R.id.pop_item_img:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.send_dynamic:
                intent = new Intent(getContext(), SendDynamicActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()){
            case R.id.listview_zixun:
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {
        onLoad();
    }

    @Override
    public void onLoadMore() {
        onLoad();
    }
    private void onLoad() {
        mlist.stopRefresh();
        mlist.stopLoadMore();
        mlist.setRefreshTime("刚刚");
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
                        Log.d("tag", "onScroll: --"+getScrollY());
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
                    break;
                case 1:
                    mSendImg.setVisibility(View.VISIBLE);
                    mlist.setOnScrollListener(null);     //此处为了不让滚动监听事件冲突
                    mTitleRelat.getBackground().setAlpha(255);
                    tintManager.setTintAlpha(1);
                    mImgHead.setImageResource(R.mipmap.message_black);
                    mMessage.setImageResource(R.mipmap.searchblack);
                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                    break;

            }
        }
    }
}

