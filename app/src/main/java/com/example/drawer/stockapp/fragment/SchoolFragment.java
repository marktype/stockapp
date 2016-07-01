package com.example.drawer.stockapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.HeJiDetailActivity;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.WebViewUpTitleActivity;
import com.example.drawer.stockapp.adapter.MyViewPagerAdapter;
import com.example.drawer.stockapp.adapter.SelectClassAdapter;
import com.example.drawer.stockapp.customview.MyGridView;
import com.example.drawer.stockapp.model.HeadIndex;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SchoolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolFragment extends Fragment  implements AdapterView.OnItemClickListener,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View mView,myClassView,findView;
    private ViewPager mPager;
    private RadioButton mFind,mClass;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SchoolFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchoolFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchoolFragment newInstance(String param1, String param2) {
        SchoolFragment fragment = new SchoolFragment();
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
        mView = inflater.inflate(R.layout.fragment_school, container, false);
        initWight();
        initData();
        return mView;
    }

    /**
     * 初始化控件
     */
    public void initWight(){
        RadioGroup mGroup = (RadioGroup) mView.findViewById(R.id.class_group);
        ImageView mMessage = (ImageView) mView.findViewById(R.id.my_class_img);
        ImageView mSearch = (ImageView) mView.findViewById(R.id.search_img);

        mFind = (RadioButton) mView.findViewById(R.id.find_txt);
        mClass = (RadioButton) mView.findViewById(R.id.class_txt);

        mPager = (ViewPager) mView.findViewById(R.id.class_content_pager);   //viewpager

        mPager.setOnPageChangeListener(new TabOnPageChangeListener());
        mGroup.setOnCheckedChangeListener(new RadioGroupListener() );
        mMessage.setOnClickListener(this);
        mSearch.setOnClickListener(this);

    }
    /**
     * 初始化适配器数据
     */
    public void initData(){
        List<View> viewList = new ArrayList<View>();
        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        findView = mInflater.inflate(R.layout.find_layout,null);     //发现布局
        myClassView = mInflater.inflate(R.layout.my_class_layout,null);   //我的课程布局
        viewList.add(findView);
        viewList.add(myClassView);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList);
        mPager.setAdapter(adapter);

        setMyClassData();

        initFindWight();

        initMyClassWight();
    }

    /**
     * 初始化发现界面控件
     */
    public void initFindWight(){
        ImageView mImgaeOne = (ImageView) findView.findViewById(R.id.heji_image_one);
        mImgaeOne.setOnClickListener(this);
        ImageView mImageTwo = (ImageView) findView.findViewById(R.id.heji_image_two);
        mImageTwo.setOnClickListener(this);


        //精选课程
        GridView gridView = (GridView) findView.findViewById(R.id.gridview_layout);
        gridView.setOnItemClickListener(this);
        SelectClassAdapter selectClassAdapter = new SelectClassAdapter(getActivity());
        selectClassAdapter.setData(setGridviewData());
        gridView.setAdapter(selectClassAdapter);

        initScrollData();

        MyGridView myGridView = (MyGridView) findView.findViewById(R.id.my_gridview);
        myGridView.setOnItemClickListener(this);
        SelectClassAdapter ClassAdapter = new SelectClassAdapter(getActivity());
        ClassAdapter.setData(setMyGridviewData());
        myGridView.setAdapter(ClassAdapter);

    }

    /**
     * 初始化我的课堂界面控件
     */
    public void initMyClassWight(){
        ImageView mMyclass = (ImageView) myClassView.findViewById(R.id.my_class_img_txt);
        ImageView mMyOpenClass = (ImageView) myClassView.findViewById(R.id.my_class_start_txt);

        mMyclass.setOnClickListener(this);
        mMyOpenClass.setOnClickListener(this);
    }
    /**
     * 推荐数据
     */
    public ArrayList<HeadIndex> recommendData(){
        ArrayList<HeadIndex> indices = new ArrayList<>();
        for (int i = 0;i<10;i++){
            HeadIndex headIndex = new HeadIndex();
            headIndex.setIndexImage("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=434429458,1397364987&fm=80");
            headIndex.setIndexName("测试名称"+i);
            headIndex.setIndexPersent("martix推荐");
            indices.add(headIndex);
        }
        return indices;
    }

    /**
     * 初始化推荐人信息
     */
    public void initScrollData(){
        LinearLayout layout = (LinearLayout) findView.findViewById(R.id.scroll_lin);

        for (int i = 0;i<recommendData().size();i++){
            HeadIndex index = recommendData().get(i);

            LinearLayout layout1 = new LinearLayout(getActivity());
            ListView.LayoutParams lay = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout1.setLayoutParams(lay);
            layout1.setGravity(Gravity.CENTER);
            layout1.setOrientation(LinearLayout.VERTICAL);

            ImageView txt = new ImageView(getActivity());
            LinearLayout.LayoutParams aaaa = new LinearLayout.LayoutParams(100, 100);
            aaaa.setMargins(10,10,10,10);
            txt.setLayoutParams(aaaa);
            txt.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load(index.getIndexImage()).into(txt);

            layout1.addView(txt);
            TextView txt1 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa1.setMargins(10,10,10,10);
            txt1.setLayoutParams(aaaa1);
            txt1.setText(index.getIndexName());
            txt1.setGravity(Gravity.CENTER);
            txt1.setTextSize(18);

            layout1.addView(txt1);

            TextView txt2 = new TextView(getActivity());
            LinearLayout.LayoutParams aaaa2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            aaaa2.setMargins(10,10,10,10);
            txt2.setLayoutParams(aaaa2);
            txt2.setText(index.getIndexPersent());
            txt2.setGravity(Gravity.CENTER);
            layout1.addView(txt2);

            layout.addView(layout1);
        }
    }

    /**
     * 精选课程数据
     */
    public ArrayList<HeadIndex> setGridviewData(){
        ArrayList<HeadIndex> headIndices = new ArrayList<>();
        for (int i = 0;i<2;i++){
            HeadIndex headIndex = new HeadIndex();
            headIndex.setIndexImage("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=987454709,2145354371&fm=80");
            headIndex.setIndexName("测试标题"+i);
            headIndex.setIndexNum("56");
            headIndex.setIndexPersent("22");
            headIndices.add(headIndex);
        }
        return headIndices;
    }
    /**
     * 课程数据
     */
    public ArrayList<HeadIndex> setMyGridviewData(){
        ArrayList<HeadIndex> headIndices = new ArrayList<>();
        for (int i = 0;i<4;i++){
            HeadIndex headIndex = new HeadIndex();
            headIndex.setIndexImage("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=987454709,2145354371&fm=80");
            headIndex.setIndexName("测试标题"+i);
            headIndex.setIndexNum("55");
            headIndex.setIndexPersent("25");
            headIndices.add(headIndex);
        }
        return headIndices;
    }

    /**
     * 设置签到天数字体大小
     */
    public void setMyClassData(){
        TextView mDayTxt = (TextView) myClassView.findViewById(R.id.class_day);
        SpannableString ss = new SpannableString(mDayTxt.getText().toString());
        ss.setSpan(new AbsoluteSizeSpan(30,true), 0, mDayTxt.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
        mDayTxt.setText(ss);    //重新设置回去（不设置则没有用）

        TextView mDayTxt2 = (TextView) myClassView.findViewById(R.id.success_class);
        SpannableString ss2 = new SpannableString(mDayTxt2.getText().toString());
        ss2.setSpan(new AbsoluteSizeSpan(20,true), 0, mDayTxt2.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
        mDayTxt2.setText(ss2);    //重新设置回去（不设置则没有用）

        TextView mDayTxt3 = (TextView) myClassView.findViewById(R.id.leiji_class);
        SpannableString ss3 = new SpannableString(mDayTxt3.getText().toString());
        ss3.setSpan(new AbsoluteSizeSpan(20,true), 0, mDayTxt3.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
        mDayTxt3.setText(ss3);    //重新设置回去（不设置则没有用）

        TextView mDayTxt4 = (TextView) myClassView.findViewById(R.id.jifen_class);
        SpannableString ss4 = new SpannableString(mDayTxt4.getText().toString());
        ss4.setSpan(new AbsoluteSizeSpan(20,true), 0, mDayTxt4.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
        mDayTxt4.setText(ss4);    //重新设置回去（不设置则没有用）


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.gridview_layout:
                Intent intent = new Intent(getActivity(), WebViewUpTitleActivity.class);
                startActivity(intent);
                break;
            case R.id.my_gridview:
                intent = new Intent(getActivity(), WebViewUpTitleActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.heji_image_one:
                Intent intent = new Intent(getActivity(), HeJiDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.heji_image_two:
                intent = new Intent(getActivity(), HeJiDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.my_class_img_txt:
                intent = new Intent(getActivity(), HeJiDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.my_class_start_txt:
                intent = new Intent(getActivity(), HeJiDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.my_class_img:
                intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.search_img:
                intent = new Intent(getActivity(), SerchActivity.class);
                startActivity(intent);
                break;
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
                    mFind.setChecked(true);
                    break;
                case 1:
                    mClass.setChecked(true);
                    break;

            }
        }
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.find_txt:
                    mPager.setCurrentItem(0);//选择某一页
                    break;
                case R.id.class_txt:
                    mPager.setCurrentItem(1);//选择某一页
                    break;

            }
        }
    }

}
