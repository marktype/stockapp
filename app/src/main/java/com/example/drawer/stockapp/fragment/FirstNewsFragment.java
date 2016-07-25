package com.example.drawer.stockapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.WebViewActivity;
import com.example.drawer.stockapp.activity.WebViewUpTitleActivity;
import com.example.drawer.stockapp.adapter.IndexAdapter;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.TrendsAdapter;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.listener.OnFragmentInteractionListener;
import com.example.drawer.stockapp.model.DynamicsInfo;
import com.example.drawer.stockapp.model.HeadMassageInfo;
import com.example.drawer.stockapp.model.NewsInfo;
import com.example.drawer.stockapp.model.TrendsInfo;
import com.google.gson.Gson;
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
public class FirstNewsFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View mView,zixunView,mSliderVIew,mScrollView,dongtaiView;
    private ViewPager mPager;
    private RadioButton mZinXun,mDongTai;
    private HeadMassageInfo headMassageInfo;
    private DynamicsInfo dynamicsInfo;
    private TrendsAdapter trendsAdapter;
    private ListView mDongTaiList;
//    private String[] images = {"http://img.lanrentuku.com/img/allimg/1605/5-1605291106390-L.jpg","http://img.lanrentuku.com/img/allimg/1605/5-1605291055080-L.jpg","http://img.lanrentuku.com/img/allimg/1605/5-1605291114570-L.jpg","http://img.lanrentuku.com/img/allimg/1605/5-1605042201270-L.jpg"};
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
        if (headMassageInfo == null){
            mView = inflater.inflate(R.layout.fragment_first_news, container, false);
            initWight();
            getMessageInfo();
            initData();
            dymnicesData();
        }
        return mView;
    }


    /**
     * 初始化控件
     */
    public void initWight(){
        ImageView mImgHead = (ImageView) mView.findViewById(R.id.info_img);   //点击头像
        ImageView mMessage = (ImageView) mView.findViewById(R.id.pop_item_img);

        RadioGroup mGroup = (RadioGroup) mView.findViewById(R.id.first_group);
        mZinXun = (RadioButton) mView.findViewById(R.id.zixun_txt);
        mDongTai = (RadioButton) mView.findViewById(R.id.dongtai_txt);

        mPager = (ViewPager) mView.findViewById(R.id.zixun_content_pager);   //viewpager

        mPager.setOnPageChangeListener(new TabOnPageChangeListener());
        mGroup.setOnCheckedChangeListener(new RadioGroupListener() );


        mImgHead.setOnClickListener(this);
        mMessage.setOnClickListener(this);


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
                if (!TextUtils.isEmpty(message)){
                    Gson gson = new Gson();
                    headMassageInfo = gson.fromJson(message, HeadMassageInfo.class);
                    Log.d("tag","---"+headMassageInfo.getHead().getStatus());
                    if (headMassageInfo.getHead().getStatus()==0){
                        ArrayList<String> size = headMassageInfo.getResult().getBannerUrl();
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
//        mScrollView = mInflater.inflate(R.layout.first_scroll_layout,null);    //第二个head（如上证指数）


//        final EditText mSearchTxt = (EditText) mSliderVIew.findViewById(R.id.slider_edit);    //搜索框


        zixunView = mInflater.inflate(R.layout.zixun_layout,null);     //资讯大框架在这儿
        dongtaiView = mInflater.inflate(R.layout.dongtai_layout,null);   //动态框架在这儿

        viewList.add(zixunView);
        viewList.add(dongtaiView);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList);
        mPager.setAdapter(adapter);

//        initListData();

        mDongTaiList = (ListView) dongtaiView.findViewById(R.id.fondtai_listview);
        trendsAdapter = new TrendsAdapter(getActivity());
//        trendsAdapter.setData(initdongtaiData());
//        mDongTaiList.setAdapter(trendsAdapter);
//
        mDongTaiList.setOnItemClickListener(this);
//        //获取焦点，跳转搜索页面
//        mSearchTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b){
//                    Intent intent = new Intent(getActivity(), SerchActivity.class);
//                    startActivity(intent);
//                    mSearchTxt.clearFocus();
//                }
//            }
//        });


    }

    //初始化listview数据（资讯）
    public void initListData(){
        getSliderLayoutView(images,null);

        ListView mlist = (ListView) zixunView.findViewById(R.id.listview_zixun);
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

            layout1.addView(txt);
            TextView txt1 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa1.setMargins(10,10,10,10);
            txt1.setLayoutParams(aaaa1);
            txt1.setText( marketDataBeen.get(i).getPoints()+"");
            txt1.setGravity(Gravity.CENTER);
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
//        mlist.addHeaderView(mScrollView);


        IndexAdapter indexAdapter = new IndexAdapter(getActivity());
        indexAdapter.setData(setNewsInfo());
        mlist.setAdapter(indexAdapter);

        mlist.setOnItemClickListener(this);

    }

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
     * 获取动态评论
     */
    public void commentList(){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,Object> hashMap = new HashMap<>();
               HashMap<String,String> map = new HashMap<>();
                map.put("PageIndex", "0");
                map.put("PageCount", "0");
                map.put("PageSize", "0");
                hashMap.put("PageInfo",map);
                hashMap.put("Id","0");
                hashMap.put("Type","0");
                String message = HttpManager.newInstance().getHttpDataByThreeLayer("",hashMap,HttpManager.COMMENT_LIST_URL);
                return message;
            }
        }.execute();
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
            info.setTime(newsBean.getUpdateTime());
            info.setPeopleNum(newsBean.getComments()+"");
            info.setImage(newsBean.getBannerUrl());
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
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.listview_zixun:
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.fondtai_listview:

                commentList();

                intent = new Intent(getActivity(), WebViewUpTitleActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.zixun_txt:
                    mPager.setCurrentItem(0);//选择某一页
                    break;
                case R.id.dongtai_txt:
                    mPager.setCurrentItem(1);//选择某一页
                    break;

            }
        }
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
                    mZinXun.setChecked(true);
                    break;
                case 1:
                    mDongTai.setChecked(true);
                    break;

            }
        }
    }
}

