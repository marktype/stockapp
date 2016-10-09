package com.example.drawer.stockapp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.AgreementWebActivity;
import com.example.drawer.stockapp.activity.CelueDatilActivity;
import com.example.drawer.stockapp.activity.LiangHuaCelueDetialActivity;
import com.example.drawer.stockapp.activity.LianghuaCelueZhaoMuZhongActivity;
import com.example.drawer.stockapp.activity.LoginActivity;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.MyZuHeDatilActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.SetupZuHeActivity;
import com.example.drawer.stockapp.adapter.CeLueAdapter;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.MyZuHeAdapter;
import com.example.drawer.stockapp.adapter.NiuRenAdapter;
import com.example.drawer.stockapp.customview.PagerSlidingTabStrip;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.BannerInfo;
import com.example.drawer.stockapp.model.CeLueInfo;
import com.example.drawer.stockapp.model.CeLueListInfo;
import com.example.drawer.stockapp.model.NiuRenInfo;
import com.example.drawer.stockapp.model.NiuRenListInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.scxh.slider.library.Indicators.PagerIndicator;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;

import java.text.DecimalFormat;
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
    public static final String ZUHETYPE = "type";
    public static final String BROAD_TYPE = "com.stock.flash";   //发送广播
    private Boolean isShowNoNet = false;   //是否显示无网络图（我的组合）
    private View mView, liangHuaZuHeView, niuRenZuHeView, myZuHeView, mSliderVIew, mSliderVIewTwo, mSliderVIewThree;
    private ViewPager mPager;
    private CeLueAdapter ceLueAdapter;
    private CeLueListInfo ceLueListInfo;
    private NiuRenListInfo niuRenListInfo,niuRenListInfoSave,niuRenListInfoMySave;
    private XListView listView, niurenList, myList;
    private RelativeLayout mTitle;
    private ImageView mMessage, mSearch;
    private String mToken;
    private ImageView mLogin,mImageNoDataOne,mImageNoDataTwo,mImageNoDataThree;
    private SharedPreferences sp,imageSp;
    private ArrayList<CeLueInfo> ceLueInfosSave;
    private NiuRenAdapter niuRenAdapter;
    private int page,niurenPage;
    DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
    private String tokenOld;   //刚进入时的token；与在次进入时比较，看是否刷新
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

        /**
         * 跟投，订阅，创建等是否刷新
         */
        IntentFilter filter = new IntentFilter();
        // 向过滤器中添加action
        filter.addAction(BROAD_TYPE);
        // 注册广播
        getContext().registerReceiver(isFlashBroad, filter);
    }

    private Boolean isFlash = false;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_auto_wisdom, container, false);
        sp = ShapePreferenceManager.getMySharedPreferences(getContext());
        imageSp = ShapePreferenceManager.getImageSharePreference(getContext());
        initWight();
        initData();
        if (ceLueListInfo == null){
            ceLueInfosSave = new ArrayList<>();
            getCelueInfo(page);
        }else {
            ceLueAdapter.setData(ceLueInfosSave);
            listView.setAdapter(ceLueAdapter);
        }
        if (niuRenListInfoSave == null){
            niuRenInfosSave = new ArrayList<>();
            getNiuRenListData(niurenPage);
        }else {
            niuRenAdapter.setData(niuRenInfosSave);
            niurenList.setAdapter(niuRenAdapter);
        }

        if (images == null){
            //banner加载
            GetBannerInfo getBannerInfo = new GetBannerInfo();
            getBannerInfo.execute();
        }else {
            getSliderLayoutView(images, strings);
            getSliderLayoutViewTwo(images, strings);
            getSliderLayoutViewThree(images, strings);
        }

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    if (ceLueInfosSave != null){
        ceLueInfosSave.clear();
        ceLueListInfo = null;
    }
        if (myListSave != null){
            myListSave.clear();
            niuRenListInfoMySave = null;
        }
        if (niuRenInfosSave != null){
            niuRenInfosSave.clear();
            niuRenListInfo = null;
        }
        if (isFlashBroad != null){
            getContext().unregisterReceiver(isFlashBroad);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColorWhite(getActivity(), tintManager);
        mToken = sp.getString(ShapePreferenceManager.TOKEN,null);
        if (tokenOld == null &&TextUtils.isEmpty(tokenOld)){
            tokenOld = mToken;
        }
        if (!TextUtils.isEmpty(mToken)){
            Log.d("tag","11111111111111");
            if (tokenOld.equals(mToken)){
                Log.d("tag","2222222222");
                if (isFlash){
                    Log.d("tag","3333333333333");
                    myListSave = new ArrayList<>();
                    mLogin.setVisibility(View.GONE);
                    getMyListData();
                }else if (niuRenListInfoMySave == null){
                    Log.d("tag","4444444444444444");
                    myListSave = new ArrayList<>();
                    mLogin.setVisibility(View.GONE);
                    getMyListData();
                }else {
                    Log.d("tag","555555555555");
                    mLogin.setVisibility(View.GONE);
                    myZuHeAdapter.setData(myListSave);
                    myList.setAdapter(myZuHeAdapter);
                }
            }else {
                Log.d("tag","6666666666666666666");
                myListSave = new ArrayList<>();
                mLogin.setVisibility(View.GONE);
                getMyListData();
            }
        }else {
            mLogin.setVisibility(View.VISIBLE);
        }
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
        mTitle.setBackgroundColor(getResources().getColor(R.color.write_color));
//        tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));

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
    private MyZuHeAdapter myZuHeAdapter;

    /**
     * 初始化适配器数据
     */
    public void initData() {


        List<View> viewList = new ArrayList<View>();
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        liangHuaZuHeView = mInflater.inflate(R.layout.lianghuacelue_layout, null);      //量化策略
        niuRenZuHeView = mInflater.inflate(R.layout.niurenzuhe_layout, null);          //牛人组合
        myZuHeView = mInflater.inflate(R.layout.my_zuhe_layout, null);                //我的组合

        mImageNoDataOne = (ImageView) liangHuaZuHeView.findViewById(R.id.loading_failed);
        mImageNoDataTwo = (ImageView) niuRenZuHeView.findViewById(R.id.loading_failed_two);
        mImageNoDataThree = (ImageView) myZuHeView.findViewById(R.id.loading_failed_three);

        mImageNoDataOne.setOnClickListener(this);
        mImageNoDataTwo.setOnClickListener(this);
        mImageNoDataThree.setOnClickListener(this);

        viewList.add(liangHuaZuHeView);
        viewList.add(niuRenZuHeView);
        viewList.add(myZuHeView);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("智能组合");
        titles.add("牛人组合");
        titles.add("我的组合");
        mSliderVIew = mInflater.inflate(R.layout.imageslider_layout_two, null);    //第一个head imageSlider
        mSliderVIewTwo = mInflater.inflate(R.layout.imageslider_layout_celue_two, null);
        mSliderVIewThree = mInflater.inflate(R.layout.imageslider_layout_celue_three, null);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList, titles);
        mPager.setAdapter(adapter);
        tabs.setViewPager(mPager);

//        Set<String> imagesSet = imageSp.getStringSet(ShapePreferenceManager.IMAGE_CELUE,null);
//        images = new ArrayList<>();
//        if (imagesSet != null&&imagesSet.size()>0){
//            for (String str : imagesSet) {
//                images.add(str);
//            }
//        }

        //量化策略
        listView = (XListView) liangHuaZuHeView.findViewById(R.id.lianghuacelue_list);
        listView.setPullLoadEnable(true);    //设置上拉加载
        listView.setOnItemClickListener(this);
        ceLueAdapter = new CeLueAdapter(getActivity());
        listView.addHeaderView(mSliderVIew);

        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 0;
                getCelueInfo(page);

            }

            @Override
            public void onLoadMore() {
                page++;
                getCelueInfo(page);
            }
        });    //注册监听

        listView.setOnScrollListener(this);

        //牛人组合
        niurenList = (XListView) niuRenZuHeView.findViewById(R.id.niuren_listview);
        niurenList.setOnItemClickListener(this);
        niurenList.setPullLoadEnable(true);    //设置上拉加载
        niurenList.addHeaderView(mSliderVIewTwo);
        niuRenAdapter = new NiuRenAdapter(getActivity());

        niurenList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                niurenPage = 0;
                getNiuRenListData(niurenPage);

            }

            @Override
            public void onLoadMore() {
                niurenPage++;
                getNiuRenListData(niurenPage);
            }
        });    //注册监听
        niurenList.setOnScrollListener(null);


        //我的组合
        myList = (XListView) myZuHeView.findViewById(R.id.my_zuhe_listview);
//        ImageView mAddImg = (ImageView) myZuHeView.findViewById(R.id.add_image);
        mLogin = (ImageView) myZuHeView.findViewById(R.id.my_zuhe_img);
        mLogin.setOnClickListener(this);
        myList.setPullLoadEnable(true);    //设置上拉加载
//        mAddImg.setOnClickListener(this);
        myList.setOnItemClickListener(this);
        myList.addHeaderView(mSliderVIewThree);
        myList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getMyListData();

            }

            @Override
            public void onLoadMore() {
                onLoadMy();
            }
        });
        myList.setOnScrollListener(null);
        myZuHeAdapter = new MyZuHeAdapter(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_zuhe_layout,null);
        TextView mAddZuhe = (TextView) view.findViewById(R.id.add_image_txt);
        myList.addFooterView(view);

        mAddZuhe.setOnClickListener(this);


    }


    private ArrayList<String> images;

    /**
     * imageSlider控件加入
     */
    public void getSliderLayoutView(ArrayList<String> mImage, final ArrayList<String> mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIew.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIew.findViewById(R.id.custom1_indicator);

        mSliderLayout.removeAllSliders();
        int length = mImage.size();
        for (int i = 0; i < length; i++) {
            TextSliderView sliderView = new TextSliderView(getContext());   //向SliderLayout中添加控件
            sliderView.image(mImage.get(i));
//            sliderView.description(mString[i]);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getContext(), AgreementWebActivity.class);
                    intent.putExtra(AgreementWebActivity.URLTYPE,3);
                    intent.putExtra(AgreementWebActivity.URL,mString.get(finalI));
                    startActivity(intent);
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
    public void getSliderLayoutViewTwo(ArrayList<String> mImage, final ArrayList<String> mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIewTwo.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIewTwo.findViewById(R.id.custom1_indicator);

        mSliderLayout.removeAllSliders();
        int length = mImage.size();
        for (int i = 0; i < length; i++) {
            TextSliderView sliderView = new TextSliderView(getContext());   //向SliderLayout中添加控件
            sliderView.image(mImage.get(i));
//            sliderView.description(mString[i]);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getContext(), AgreementWebActivity.class);
                    intent.putExtra(AgreementWebActivity.URLTYPE,3);
                    intent.putExtra(AgreementWebActivity.URL,mString.get(finalI));
                    startActivity(intent);
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
    public void getSliderLayoutViewThree(ArrayList<String> mImage, final ArrayList<String> mString) {
        SliderLayout mSliderLayout = (SliderLayout) mSliderVIewThree.findViewById(R.id.image_slider_layout);
//        mSliderLayout.setMinimumHeight(180);

        PagerIndicator pagerIndicator = (PagerIndicator) mSliderVIewThree.findViewById(R.id.custom1_indicator);

        mSliderLayout.removeAllSliders();
        int length = mImage.size();
        for (int i = 0; i < length; i++) {
            TextSliderView sliderView = new TextSliderView(getContext());   //向SliderLayout中添加控件
            sliderView.image(mImage.get(i));
//            sliderView.description(mString[i]);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getContext(), AgreementWebActivity.class);
                    intent.putExtra(AgreementWebActivity.URLTYPE,3);
                    intent.putExtra(AgreementWebActivity.URL,mString.get(finalI));
                    startActivity(intent);
                }
            });
            mSliderLayout.addSlider(sliderView);
        }
//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);  //将小圆点设置到右下方
        mSliderLayout.setCustomIndicator(pagerIndicator);  //将小圆点设置到右下方(自定义控件指示器)

    }

    private ArrayList<String> strings;
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
                    List<BannerInfo.ResultBean.BannerUrlBean> size = bannerInfo.getResult().getBannerUrl();
                    images = new ArrayList<>();
                    strings = new ArrayList<>();
                    for (int i = 0; i < size.size(); i++) {
                        if (i>3){
                            images.add(size.get(i).getBannerUrl());
                            strings.add(size.get(i).getTargetUrl());     //bar图
                        }
                    }
                    //添加轮播图数据
                    getSliderLayoutView(images, strings);
                    getSliderLayoutViewTwo(images, strings);
                    getSliderLayoutViewThree(images, strings);

                }
            }
        }

    }

    /**
     * 获取量化策略列表
     */
    public void getCelueInfo(final int page) {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", page+"");
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("", map, HttpManager.StrategyList_URL);
                return message;

            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                onLoad();
                String message = (String) o;
                if (!TextUtils.isEmpty(message)&&!message.equals(HttpManager.FAILED)) {
                    Gson gson = new Gson();
                    ceLueListInfo = gson.fromJson(message, CeLueListInfo.class);
                    if (ceLueListInfo.getHead().getStatus() == 0) {
                        ArrayList<CeLueInfo> celueList = setLianghuaCelueData();
                        if (page == 0){
                            ceLueAdapter.setData(celueList);
                            listView.setAdapter(ceLueAdapter);
                        }else if (page>0&&celueList.size()>0){
                            ceLueAdapter.addData(celueList);
                        }else {
                            Toast.makeText(getActivity(),"没有更多了",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
                    mImageNoDataOne.setVisibility(View.VISIBLE);
                }

            }
        }.execute();
    }

    /**
     * 获取牛人组合列表数据
     */
    public void getNiuRenListData(final int page) {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", ""+page);
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer("", map, HttpManager.StarPorfolio_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                onLoadNiu();

                String message = (String) o;
                if (!TextUtils.isEmpty(message)) {
                    Gson gson = new Gson();
                    niuRenListInfo = gson.fromJson(message, NiuRenListInfo.class);
                    if (niuRenListInfo.getHead().getStatus() == 0) {
                        ArrayList<NiuRenInfo> niurenInfoList = setNiuRenData(niuRenListInfo);
                        niuRenListInfoSave = niuRenListInfo;
                        if (niurenPage == 0){
                            niuRenInfosSave = niurenInfoList;
                            niuRenAdapter.setData(niurenInfoList);
                            niurenList.setAdapter(niuRenAdapter);
                        }else if (niurenPage >0&&niurenInfoList.size()>0){
                            niuRenInfosSave.addAll(niurenInfoList);
                            niuRenAdapter.addData(niurenInfoList);
                        }else {
                            Toast.makeText(getActivity(),"没有更多了",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    mImageNoDataTwo.setVisibility(View.VISIBLE);
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
                HashMap<String, String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "100");
                String message = HttpManager.newInstance().getHttpDataByTwoLayer(mToken, map, HttpManager.MyPorfolio_URL);
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
                        niuRenListInfoMySave = niuRenListInfo;
                        getMyCollect(setMyZuHeData());
                    }else {
                        if (myInfoList == null){
                            myInfoList = new ArrayList<>();   //我的组合信息
                        }
                        getMyCollect(myInfoList);
                    }
                }else {
                    isShowNoNet = true;
                    mImageNoDataThree.setVisibility(View.VISIBLE);
                }

            }
        }.execute();
    }

    /**
     * 我的订阅
     */
    public void getMyCollect(final ArrayList<NiuRenInfo> niurenList){
            new AsyncTask(){

                @Override
                protected Object doInBackground(Object[] objects) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("PageIndex", "0");
                    map.put("PageCount", "0");
                    map.put("PageSize", "100");
                    String message = HttpManager.newInstance().getHttpDataByTwoLayer(mToken, map, HttpManager.MyCollectPorfolio_URL);
                    return message;
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    onLoadMy();
                    String message = (String) o;
                    if (!TextUtils.isEmpty(message)) {
                        Gson gson = new Gson();
                        niuRenListInfo = gson.fromJson(message, NiuRenListInfo.class);
                        if (niuRenListInfo.getHead().getStatus() == 0) {
                            setMyZuHeInfo(niurenList);
                        }else {
                            if (niurenList.size() == 0){
                                mLogin.setVisibility(View.VISIBLE);
                            }
                        }
                    }else {
                        if (isShowNoNet){  //显示图片
                            mImageNoDataThree.setVisibility(View.VISIBLE);
                            isShowNoNet = false;
                        }
                    }



                }
            }.execute();
    }

    /**
     * 设置我的组合、订阅等
     * @param niurenList
     */
    public void setMyZuHeInfo(ArrayList<NiuRenInfo> niurenList){
        List<NiuRenListInfo.ResultBean.StrategiesBean> starPorfolioBeen = niuRenListInfo.getResult().getStrategies();
        for (int i = 0; i < starPorfolioBeen.size(); i++) {
            NiuRenListInfo.ResultBean.StrategiesBean ben = starPorfolioBeen.get(i);
            NiuRenInfo info = new NiuRenInfo();
            info.setId(ben.getId());
            info.setNiurenName(ben.getTitle());
            info.setShouyiRate(Double.parseDouble(df.format(ben.getTotleReturns())));
            info.setStockType(ben.getDesc());
            info.setZuheType(3);   //全是订阅
            info.setTradeTime(ben.getFavorites()+"");
            niurenList.add(info);
        }
        if (niurenList.size()>0){
            myListSave = niurenList;
        }
        myZuHeAdapter.setData(niurenList);
        myList.setAdapter(myZuHeAdapter);
    }

    /**
     * 初始化量化策略数据
     */
    public ArrayList<CeLueInfo> setLianghuaCelueData() {

        ArrayList<CeLueInfo> ceLueInfos = new ArrayList<>();
        List<CeLueListInfo.ResultBean.StrategiesBean> strategiesBeen = ceLueListInfo.getResult().getStrategies();
        for (int i = 0; i < strategiesBeen.size(); i++) {
            CeLueListInfo.ResultBean.StrategiesBean ben = strategiesBeen.get(i);

            CeLueInfo info = new CeLueInfo();
            info.setId(ben.getId());
            info.setTitle(ben.getName());
            info.setJingZhiNum(ben.getTargetReturns() + "%");
            info.setMaxNum(ben.getMaxDay() + "天");
            info.setRateNum(ben.getShareRatio() + "%");
            info.setName((String) ben.getUserName());
            info.setMinGengTou(ben.getMinFollow() + "");
            info.setOtherInfo((String) ben.getDesc());
            info.setHeadImage((String) ben.getUserImgUrl());
            info.setEndInvestment(ben.isIsEndInvestment());      //运行是否结束
            info.setEndRecruit(ben.isIsEndRecruit());      //招募是否结束
            info.setStartInvestment(ben.isIsStartInvestment());   //运行是否结束
            if (ben.isIsNotStartRecruit()){
                info.setType(4);
            }else if (ben.isIsStartRecruit()){   //招募中
                info.setCeluePersent(ben.getRecruitment()+"");
                info.setType(2);
                ceLueInfos.add(info);
            }else if (ben.isIsStartInvestment()&&!ben.isIsStartRun()){   //待运行
                info.setCeluePersent(ben.getRecruitment()+"");
                info.setType(5);
                ceLueInfos.add(info);
            }else if (ben.isIsStartInvestment()&&ben.isIsStartRun()&&ben.getRunEndDay() == null){//运行中
                info.setCeluePersent(ben.getTotalReturn()+"");
                info.setRunTime(ben.getRunStartDay());
                info.setType(1);
                ceLueInfos.add(info);
            }else if (ben.isIsEndInvestment()||ben.getRunEndDay() != null){   //已结束
                info.setCeluePersent(ben.getTotalReturn()+"");
                info.setEndStatus(ben.getRunEndState());
                info.setType(3);
                ceLueInfos.add(info);
            }
        }
        ceLueInfosSave = ceLueInfos;
        return ceLueInfos;
    }

    /**
     * 初始化牛人数据
     *
     * @return
     */
    public ArrayList<NiuRenInfo> setNiuRenData(NiuRenListInfo niuRenListInfo) {
        ArrayList<NiuRenInfo> niuRenInfos = new ArrayList<>();
        List<NiuRenListInfo.ResultBean.StrategiesBean> starPorfolioBeen = niuRenListInfo.getResult().getStrategies();
        for (int i = 0; i < starPorfolioBeen.size(); i++) {
            NiuRenListInfo.ResultBean.StrategiesBean ben = starPorfolioBeen.get(i);
            NiuRenInfo info = new NiuRenInfo();
            info.setId(ben.getId());
            info.setNiurenHead(ben.getUserImgUrl());
            info.setNiurenName(ben.getTitle()+"");
            info.setNiurenRoundImage("http://img2.imgtn.bdimg.com/it/u=1689964256,2679873424&fm=21&gp=0.jpg");
            info.setShouyiRate(Double.parseDouble(df.format(ben.getTotleReturns())));
            info.setVictorRate(ben.getWinRatio() + "%");
            info.setShouyiByMonth(df.format(ben.getMonthlyAverage()) + "%");
            info.setStockNum(ben.getHolding());
            info.setCangweiRate(df.format(ben.getPosition()) + "%");
            info.setDayNum(ben.getAveragePosition());
            info.setTradeTime(ben.getAverageTrading() + "");
            info.setStockType(ben.getTitle());
            niuRenInfos.add(info);
        }

        return niuRenInfos;
    }


    private ArrayList<NiuRenInfo> myInfoList,myListSave,niuRenInfosSave;
    /**
     * 初始化我的组合数据
     *
     * @return
     */
    public ArrayList<NiuRenInfo> setMyZuHeData() {
        long nowTime = System.currentTimeMillis();
        Log.d("tag","nowTime----"+nowTime);
        myInfoList = new ArrayList<>();   //我的组合信息
        DecimalFormat df =new DecimalFormat("#0.00");   //保留两位小数
        List<NiuRenListInfo.ResultBean.StrategiesBean> starPorfolioBeen = niuRenListInfo.getResult().getStrategies();
        for (int i = 0; i < starPorfolioBeen.size(); i++) {
            NiuRenListInfo.ResultBean.StrategiesBean ben = starPorfolioBeen.get(i);
            NiuRenInfo info = new NiuRenInfo();
            info.setNiurenName(ben.getTitle());
            info.setId(ben.getId());
            info.setShouyiRate(Double.parseDouble(df.format(ben.getTotleReturns())));
            info.setStockType(ben.getDesc());
            info.setTradeTime(ben.getFavorites() + "");
            if (ben.getPorfolioChooseType() == 1){
                info.setZuheType(ben.getPorfolioChooseType());
                if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime<ManagerUtil.getTime(ben.getRunStartDay())){
                    info.setType(0);    //招募中
                }else if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime>ManagerUtil.getTime(ben.getRunStartDay())&&nowTime<ManagerUtil.getTime(ben.getRunEndDay())){
                    info.setType(1);   //运行中
                }else if (nowTime>ManagerUtil.getTime(ben.getRecuitmentStartTime())&&nowTime>ManagerUtil.getTime(ben.getRunStartDay())&&nowTime>ManagerUtil.getTime(ben.getRunEndDay())){
                    info.setType(2);   //已结束
                }

            }else {
                info.setZuheType(2);   //2和3全是创建
            }
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
                CeLueInfo celueinfo = (CeLueInfo) adapterView.getAdapter().getItem(i);
                if (celueinfo.getType() == 1){   //运行中
                    Intent intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,celueinfo.getId());
                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,celueinfo.getTitle()+"（运行中）");
                    startActivity(intent);
                }else if (celueinfo.getType() == 2||celueinfo.getType() == 5){   //招募中
                    Intent intent = new Intent(getActivity(), LianghuaCelueZhaoMuZhongActivity.class);
                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,celueinfo.getId());
                    startActivity(intent);
                }else if (celueinfo.getType() == 3){    //已结束
                    Intent intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,celueinfo.getId());
                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,celueinfo.getTitle()+"（已结束）");
                    intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_STATUS,celueinfo.getEndStatus());
                    intent.putExtra(LiangHuaCelueDetialActivity.TYPE,4);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"策略状态出错",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.niuren_listview:
                NiuRenInfo info = (NiuRenInfo) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getActivity(), CelueDatilActivity.class);
                intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
                intent.putExtra(CELUENAME, info.getNiurenName());
                startActivity(intent);
                break;
            case R.id.my_zuhe_listview:
                info = (NiuRenInfo) adapterView.getAdapter().getItem(i);
                if (info.getZuheType() == 1){    //跟投
                    if (info.getType() == 0){
                        intent = new Intent(getActivity(), LianghuaCelueZhaoMuZhongActivity.class);
                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,info.getId());
                        intent.putExtra(LiangHuaCelueDetialActivity.TYPE,1);
                        startActivity(intent);
                    }else if (info.getType() == 1){
                        intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,info.getId());
                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,info.getNiurenName()+"（运行中）");
                        intent.putExtra(LiangHuaCelueDetialActivity.TYPE,2);
                        startActivity(intent);
                    }else if (info.getType() == 2){
                        intent = new Intent(getActivity(), LiangHuaCelueDetialActivity.class);
                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_ID,info.getId());
                        intent.putExtra(LiangHuaCelueDetialActivity.LIANGHUA_NAME,info.getNiurenName()+"（已结束）");
                        intent.putExtra(LiangHuaCelueDetialActivity.TYPE,3);
                        startActivity(intent);
                    }
//                    intent = new Intent(getActivity(), CelueDatilActivity.class);
//                    intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
//                    intent.putExtra(CELUENAME, info.getNiurenName());
//                    intent.putExtra(ZUHETYPE,1);
//                    startActivity(intent);
                }else if (info.getZuheType() == 2){   //创建
                    intent = new Intent(getActivity(), MyZuHeDatilActivity.class);
                    intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
                    intent.putExtra(CELUENAME, info.getNiurenName());
                    startActivity(intent);
                }else {    //订阅
                    intent = new Intent(getActivity(), CelueDatilActivity.class);
                    intent.putExtra(CelueDatilActivity.ZUHE_ID,info.getId());
                    intent.putExtra(CELUENAME, info.getNiurenName());
                    intent.putExtra(ZUHETYPE,3);
                    startActivity(intent);
                }
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
//                intent.putExtra(SerchActivity.URL_SEARCH,HttpManager.FindCode_URL);
                startActivity(intent);
                break;
            case R.id.add_image_txt:
                intent = new Intent(getActivity(), SetupZuHeActivity.class);
                intent.putExtra(SetupZuHeActivity.TYPE,0);
                startActivity(intent);
                break;
            case R.id.my_zuhe_img:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.loading_failed:
                mImageNoDataOne.setVisibility(View.GONE);
                getCelueInfo(page);
                break;
            case R.id.loading_failed_two:
                mImageNoDataTwo.setVisibility(View.GONE);
                getNiuRenListData(niurenPage);
                break;
            case R.id.loading_failed_three:
                mImageNoDataThree.setVisibility(View.GONE);
                getMyListData();
                break;
        }
    }

    /**
     * 策略组合停止加载和刷新
     */
    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
//        listView.setRefreshTime("刚刚");
    }
    /**
     * 牛人组合停止加载和刷新
     */
    private void onLoadNiu() {
        niurenList.stopRefresh();
        niurenList.stopLoadMore();
//        niurenList.setRefreshTime("刚刚");
    }

    /**
     * 我的组合停止加载和刷新
     */
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
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
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
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
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
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.write_color));
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

//            Log.d("tag", "position---" + position + "----pooffest---" + positionOffset + "----pixel---" + positionOffsetPixels);
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
//                    Log.d("tag","yoken------"+mToken);
//                    if (TextUtils.isEmpty(mToken)){
//                        mLogin.setVisibility(View.VISIBLE);
//                    }else {
//                        mLogin.setVisibility(View.GONE);
//
//                    }
                    listView.setOnScrollListener(null);
                    niurenList.setOnScrollListener(null);
                    myList.setOnScrollListener(AutoWisdomFragment.this);
                    break;

            }
        }
    }
}
