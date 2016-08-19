package com.example.drawer.stockapp.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.CelueDatilActivity;
import com.example.drawer.stockapp.activity.DetilCelueActivity;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.SetupZuHeActivity;
import com.example.drawer.stockapp.adapter.CeLueAdapter;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.MyZuHeAdapter;
import com.example.drawer.stockapp.adapter.NiuRenAdapter;
import com.example.drawer.stockapp.customview.PagerSlidingTabStrip;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.CeLueInfo;
import com.example.drawer.stockapp.model.CeLueListInfo;
import com.example.drawer.stockapp.model.NiuRenInfo;
import com.example.drawer.stockapp.model.NiuRenListInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
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
 * Use the {@link AutoWisdomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoWisdomFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, XListView.OnXScrollListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String CELUENAME = "celuename";
    private View mView, liangHuaZuHeView, niuRenZuHeView, myZuHeView, mSliderVIew, mSliderVIewTwo, mSliderVIewThree;
    private ViewPager mPager;
    private RadioButton mLiangHuaCelue, mNiuRenZuHe, mMyZuHe;
    private CeLueAdapter ceLueAdapter;
    private CeLueListInfo ceLueListInfo;
    private NiuRenListInfo niuRenListInfo;
    private XListView listView, niurenList, myList;
    private RelativeLayout mTitle;
    private ImageView mMessage, mSearch;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AutoWisdomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AutoWisdomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AutoWisdomFragment newInstance(String param1, String param2) {
        AutoWisdomFragment fragment = new AutoWisdomFragment();
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
//        if (ceLueListInfo == null){
        mView = inflater.inflate(R.layout.fragment_auto_wisdom, container, false);
        initWight();
        initData();
//        }

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColorWhite(getActivity(), tintManager);
    }

    private PagerSlidingTabStrip tabs;

    /**
     * 初始化控件
     */
    public void initWight() {

        mTitle = (RelativeLayout) mView.findViewById(R.id.all_title);
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(),50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()), 0, 0);
        mTitle.setLayoutParams(params);


        tintManager = ManagerUtil.newInstance(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        mTitle.setBackgroundResource(R.color.write_color);
        tintManager.setStatusBarTintResource(R.color.write_color);

        mMessage = (ImageView) mView.findViewById(R.id.wisdom_info);
        mSearch = (ImageView) mView.findViewById(R.id.pop_item_img);

        tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.wisdom_group);


        mPager = (ViewPager) mView.findViewById(R.id.wisdom_content_pager);   //viewpager
        mPager.setOffscreenPageLimit(1);
        mPager.setOnPageChangeListener(new TabOnPageChangeListener());

        mMessage.setOnClickListener(this);
        mSearch.setOnClickListener(this);


    }

    private int mCurrentfirstVisibleItem = 0;
    private SparseArray recordSp = new SparseArray(0);
    private SparseArray recordSp2 = new SparseArray(0);
    private SparseArray recordSp3 = new SparseArray(0);
    protected SystemBarTintManager tintManager;

    /**
     * 初始化适配器数据
     */
    public void initData() {


        List<View> viewList = new ArrayList<View>();
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        liangHuaZuHeView = mInflater.inflate(R.layout.lianghuacelue_layout, null);      //量化策略
        niuRenZuHeView = mInflater.inflate(R.layout.niurenzuhe_layout, null);          //牛人组合
        myZuHeView = mInflater.inflate(R.layout.my_zuhe_layout, null);                //我的组合
        viewList.add(liangHuaZuHeView);
        viewList.add(niuRenZuHeView);
        viewList.add(myZuHeView);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("量化策略");
        titles.add("牛人组合");
        titles.add("我的组合");
        mSliderVIew = mInflater.inflate(R.layout.imageslider_layout_two, null);    //第一个head imageSlider
        mSliderVIewTwo = mInflater.inflate(R.layout.imageslider_layout_celue_two, null);
        mSliderVIewThree = mInflater.inflate(R.layout.imageslider_layout_celue_three, null);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList, titles);
        mPager.setAdapter(adapter);
        tabs.setViewPager(mPager);


        //添加轮播图数据
        getSliderLayoutView(images, null);
        getSliderLayoutViewTwo(images, null);
        getSliderLayoutViewThree(images, null);
        //量化策略
        listView = (XListView) liangHuaZuHeView.findViewById(R.id.lianghuacelue_list);
        listView.setPullLoadEnable(true);    //设置上拉加载
        listView.setOnItemClickListener(this);
        ceLueAdapter = new CeLueAdapter(getActivity());
        listView.addHeaderView(mSliderVIew);
        getCelueInfo();
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                onLoad();
            }

            @Override
            public void onLoadMore() {
                onLoad();
            }
        });    //注册监听

        listView.setOnScrollListener(this);

        //牛人组合
        niurenList = (XListView) niuRenZuHeView.findViewById(R.id.niuren_listview);
        niurenList.setOnItemClickListener(this);
        niurenList.setPullLoadEnable(true);    //设置上拉加载
        niurenList.addHeaderView(mSliderVIewTwo);
        NiuRenAdapter niuRenAdapter = new NiuRenAdapter(getActivity());
        getNiuRenListData(niuRenAdapter);
        niurenList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                onLoadNiu();
            }

            @Override
            public void onLoadMore() {
                onLoadNiu();
            }
        });    //注册监听
        niurenList.setOnScrollListener(null);


        //我的组合
        myList = (XListView) myZuHeView.findViewById(R.id.my_zuhe_listview);
        ImageView mAddImg = (ImageView) myZuHeView.findViewById(R.id.add_image);
        mAddImg.setOnClickListener(this);
        myList.setOnItemClickListener(this);
        myList.addHeaderView(mSliderVIewThree);
        myList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                onLoadMy();
            }

            @Override
            public void onLoadMore() {
                onLoadMy();
            }
        });
        myList.setOnScrollListener(null);
        MyZuHeAdapter myZuHeAdapter = new MyZuHeAdapter(getActivity());
        getMyListData();

        myZuHeAdapter.setData(setMyZuHeData());
        myList.setAdapter(myZuHeAdapter);

    }


    private int[] images = {R.mipmap.banner, R.mipmap.banner, R.mipmap.banner, R.mipmap.banner};

    /**
     * imageSlider控件加入
     */
    public void getSliderLayoutView(int[] mImage, final String[] mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIew.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

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

    /**
     * imageSlider控件加入
     */
    public void getSliderLayoutViewTwo(int[] mImage, final String[] mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIewTwo.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIewTwo.findViewById(R.id.custom1_indicator);

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

    /**
     * imageSlider控件加入
     */
    public void getSliderLayoutViewThree(int[] mImage, final String[] mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIewThree.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIewThree.findViewById(R.id.custom1_indicator);

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

    /**
     * 获取量化策略列表
     */
    public void getCelueInfo() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("", map, HttpManager.StrategyList_URL);
                return message;

            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                if (!TextUtils.isEmpty(message)) {
                    Gson gson = new Gson();
                    ceLueListInfo = gson.fromJson(message, CeLueListInfo.class);
                    if (ceLueListInfo.getHead().getStatus() == 0) {
                        ceLueAdapter.setData(setLianghuaCelueData());
                        listView.setAdapter(ceLueAdapter);
                    }
                }

            }
        }.execute();
    }

    /**
     * 获取牛人组合列表数据
     */
    public void getNiuRenListData(final NiuRenAdapter niuRenAdapter) {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("", map, HttpManager.StarPorfolio_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String message = (String) o;
                if (!TextUtils.isEmpty(message)) {
                    Gson gson = new Gson();
                    niuRenListInfo = gson.fromJson(message, NiuRenListInfo.class);
                    if (niuRenListInfo.getHead().getStatus() == 0) {
                        niuRenAdapter.setData(setNiuRenData());
                        niurenList.setAdapter(niuRenAdapter);
                    }
                }
            }
        }.execute();
    }

    /**
     * 我的组合列表
     */
    public void getMyListData() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, Object> hashMap = new HashMap<>();
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                hashMap.put("PageInfo", map);
                hashMap.put("Id", "0");
                String message = HttpManager.newInstance().getHttpDataByThreeLayer("", hashMap, HttpManager.MyPorfolio_URL);
                return message;
            }
        }.execute();
    }

    /**
     * 初始化量化策略数据
     */
    public ArrayList<CeLueInfo> setLianghuaCelueData() {
        ArrayList<CeLueInfo> ceLueInfos = new ArrayList<>();
        ArrayList<CeLueListInfo.ResultBean.StrategiesBean> strategiesBeen = ceLueListInfo.getResult().getStrategies();
        for (int i = 0; i < strategiesBeen.size(); i++) {
            CeLueListInfo.ResultBean.StrategiesBean ben = strategiesBeen.get(i);

            CeLueInfo info = new CeLueInfo();
            if (i == 0) {
                info.setType(1);
            }
            info.setCeluePersent(ben.getRecruitment());
            info.setTitle(ben.getName());
            info.setJingZhiNum(ben.getTargetReturns() + "%");
            info.setMaxNum(ben.getMaxDay() + "天");
            info.setRateNum(ben.getShareRatio() + "%");
            info.setName(ben.getUserName());
            info.setMinGengTou(ben.getMinFollow() + "");
            info.setOtherInfo(ben.getDesc());
            info.setHeadImage(ben.getUserImgUrl());
            info.setLevelImage(ben.getUserLevelImgUrl());
            ceLueInfos.add(info);
        }
        return ceLueInfos;
    }

    /**
     * 初始化牛人数据
     *
     * @return
     */
    public ArrayList<NiuRenInfo> setNiuRenData() {
        ArrayList<NiuRenInfo> niuRenInfos = new ArrayList<>();
        ArrayList<NiuRenListInfo.ResultBean.StarPorfolioBean> starPorfolioBeen = niuRenListInfo.getResult().getStarPorfolio();
        for (int i = 0; i < starPorfolioBeen.size(); i++) {
            NiuRenListInfo.ResultBean.StarPorfolioBean ben = starPorfolioBeen.get(i);
            NiuRenInfo info = new NiuRenInfo();
            info.setNiurenHead("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2516161713,321762461&fm=58");
            info.setNiurenName(ben.getUserName());
            info.setNiurenRoundImage("http://img2.imgtn.bdimg.com/it/u=1689964256,2679873424&fm=21&gp=0.jpg");
            info.setShouyiRate(ben.getTotleReturns());
            info.setVictorRate(ben.getWinRatio() + "%");
            info.setShouyiByMonth(ben.getMonthlyAverage() + "%");
            info.setStockNum(ben.getHolding());
            info.setCangweiRate(ben.getPosition() + "%");
            info.setDayNum(ben.getAveragePosition());
            info.setTradeTime(ben.getAverageTrading() + "");
            info.setStockType(ben.getTitle());
            niuRenInfos.add(info);
        }
        return niuRenInfos;
    }

    /**
     * 初始化我的组合数据
     *
     * @return
     */
    public ArrayList<NiuRenInfo> setMyZuHeData() {
        ArrayList<NiuRenInfo> myInfoList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            NiuRenInfo info = new NiuRenInfo();
            info.setShouyiRate(20.0);
            info.setStockType("沪深");
            info.setTradeTime(i + "人 关注");
            myInfoList.add(info);
        }
        return myInfoList;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * 策略组合点击事件
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.lianghuacelue_list:
                Intent intent = new Intent(getActivity(), DetilCelueActivity.class);
                intent.putExtra(CELUENAME, "量化策略");
                startActivity(intent);
                break;
            case R.id.niuren_listview:
                intent = new Intent(getActivity(), CelueDatilActivity.class);
                intent.putExtra(CELUENAME, "牛人组合");
                startActivity(intent);
                break;
            case R.id.my_zuhe_listview:
                intent = new Intent(getActivity(), CelueDatilActivity.class);
                intent.putExtra(CELUENAME, "我的组合");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wisdom_info:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.pop_item_img:
                intent = new Intent(getActivity(), SerchActivity.class);
                startActivity(intent);
                break;
            case R.id.add_image:
                intent = new Intent(getActivity(), SetupZuHeActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
//        listView.setRefreshTime("刚刚");
    }

    private void onLoadNiu() {
        niurenList.stopRefresh();
        niurenList.stopLoadMore();
//        niurenList.setRefreshTime("刚刚");
    }

    private void onLoadMy() {
        myList.stopRefresh();
        myList.stopLoadMore();
//        myList.setRefreshTime("刚刚");
    }

    @Override
    public void onXScrolling(View view) {

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        if (absListView.getId() == R.id.lianghuacelue_list) {

            mCurrentfirstVisibleItem = i;
            tintManager.setStatusBarTintResource(R.color.write_color);
            View firstView = absListView.getChildAt(0);
            if (null != firstView) {
                ItemRecod itemRecord = (ItemRecod) recordSp.get(i);
                if (null == itemRecord) {
                    itemRecord = new ItemRecod();
                }
                itemRecord.height = firstView.getHeight();
                itemRecord.top = firstView.getTop();
                recordSp.append(i, itemRecord);
                //设置滑动颜色渐变（0-511）
                if (getScrollY(recordSp) <= 100) {
                    //设置渐变
//                        mTitleRelat.getBackground().setAlpha(getScrollY() / 2);
//                        tintManager.setTintAlpha((float) getScrollY() / 510);
                    //不设置渐变
                    mTitle.getBackground().setAlpha(25);
                    tintManager.setTintAlpha(0.1f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.write_color));
                    mMessage.setImageResource(R.mipmap.message_white);
                    mSearch.setImageResource(R.mipmap.search_white);
                } else {       //只执行一次就好
                    mTitle.getBackground().setAlpha(220);
                    tintManager.setTintAlpha(0.85f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                    mMessage.setImageResource(R.mipmap.message_black);
                    mSearch.setImageResource(R.mipmap.searchblack);
                }
            }
        } else if (absListView.getId() == R.id.niuren_listview) {
            mCurrentfirstVisibleItem = i;
            tintManager.setStatusBarTintResource(R.color.write_color);
            View firstView = absListView.getChildAt(0);
            if (null != firstView) {
                ItemRecod itemRecord = (ItemRecod) recordSp2.get(i);
                if (null == itemRecord) {
                    itemRecord = new ItemRecod();
                }
                itemRecord.height = firstView.getHeight();
                itemRecord.top = firstView.getTop();
                recordSp2.append(i, itemRecord);
                //设置滑动颜色渐变（0-511）
                if (getScrollY(recordSp2) <= 100) {
                    //设置渐变
//                        mTitleRelat.getBackground().setAlpha(getScrollY() / 2);
//                        tintManager.setTintAlpha((float) getScrollY() / 510);
                    //不设置渐变
                    mTitle.getBackground().setAlpha(25);
                    tintManager.setTintAlpha(0.1f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.write_color));
                    mMessage.setImageResource(R.mipmap.message_white);
                    mSearch.setImageResource(R.mipmap.search_white);
                } else {       //只执行一次就好
                    mTitle.getBackground().setAlpha(220);
                    tintManager.setTintAlpha(0.85f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                    mMessage.setImageResource(R.mipmap.message_black);
                    mSearch.setImageResource(R.mipmap.searchblack);
                }
            }
        } else if (absListView.getId() == R.id.my_zuhe_listview) {
            mCurrentfirstVisibleItem = i;
            tintManager.setStatusBarTintResource(R.color.write_color);
            View firstView = absListView.getChildAt(0);
            if (null != firstView) {
                ItemRecod itemRecord = (ItemRecod) recordSp3.get(i);
                if (null == itemRecord) {
                    itemRecord = new ItemRecod();
                }
                itemRecord.height = firstView.getHeight();
                itemRecord.top = firstView.getTop();
                recordSp3.append(i, itemRecord);
                //设置滑动颜色渐变（0-511）
                if (getScrollY(recordSp3) <= 100) {
                    //设置渐变
//                        mTitleRelat.getBackground().setAlpha(getScrollY() / 2);
//                        tintManager.setTintAlpha((float) getScrollY() / 510);
                    //不设置渐变
                    mTitle.getBackground().setAlpha(25);
                    tintManager.setTintAlpha(0.1f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.write_color));
                    mMessage.setImageResource(R.mipmap.message_white);
                    mSearch.setImageResource(R.mipmap.search_white);
                } else {       //只执行一次就好
                    mTitle.getBackground().setAlpha(220);
                    tintManager.setTintAlpha(0.85f);

                    ManagerUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
                    ManagerUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
                    tabs.setSelectedTextColor(getActivity().getResources().getColor(android.R.color.background_dark));
                    mMessage.setImageResource(R.mipmap.message_black);
                    mSearch.setImageResource(R.mipmap.searchblack);
                }
            }
        }
    }

    //获取偏移距离
    private int getScrollY(SparseArray recordSp) {
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

            Log.d("tag", "position---" + position + "----pooffest---" + positionOffset + "----pixel---" + positionOffsetPixels);
        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    listView.setOnScrollListener(AutoWisdomFragment.this);
                    niurenList.setOnScrollListener(null);
                    myList.setOnScrollListener(null);
                    break;
                case 1:
                    listView.setOnScrollListener(null);
                    niurenList.setOnScrollListener(AutoWisdomFragment.this);
                    myList.setOnScrollListener(null);
                    break;
                case 2:
                    listView.setOnScrollListener(null);
                    niurenList.setOnScrollListener(null);
                    myList.setOnScrollListener(AutoWisdomFragment.this);
                    break;

            }
        }
    }
}
