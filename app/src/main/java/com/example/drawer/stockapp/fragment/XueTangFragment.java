package com.example.drawer.stockapp.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.MessageActivity;
import com.example.drawer.stockapp.activity.SerchActivity;
import com.example.drawer.stockapp.activity.WebViewUpTitleActivity;
import com.example.drawer.stockapp.adapter.HeJiAdapter;
import com.example.drawer.stockapp.customview.view.XListView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.HeadIndex;
import com.example.drawer.stockapp.model.XueTangInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class XueTangFragment extends Fragment implements View.OnClickListener{

    private View mView;
    private XListView mList;
    private HeJiAdapter adapter;
    private XueTangInfo findInfo;
    private  ArrayList<HeadIndex> listSave;
    private int page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragent_xuetang_layout, container, false);
        initWight();
        if (findInfo == null){
            listSave = new ArrayList<>();
            SchoolFindAsyn asyn = new SchoolFindAsyn();
            asyn.execute(page+"");
        }else {
            adapter.setData(listSave);
            mList.setAdapter(adapter);
        }

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColor(getActivity(),tintManager);

    }


    /**
     * 初始化控件
     */
    public void initWight(){
        RelativeLayout  mTitle = (RelativeLayout) mView.findViewById(R.id.xuetang_title);
        //设置距离顶部状态栏高度
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(),50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()),0,0);
        mTitle.setLayoutParams(params);


        ImageView mMessage = (ImageView) mView.findViewById(R.id.my_class_img);
        ImageView mSearch = (ImageView) mView.findViewById(R.id.search_img);

        mMessage.setOnClickListener(this);
        mSearch.setOnClickListener(this);

        mList = (XListView) mView.findViewById(R.id.xuetang_list);   //学堂列表
        adapter = new HeJiAdapter(getActivity());

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HeadIndex headIndex = (HeadIndex) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getContext(), WebViewUpTitleActivity.class);
                intent.putExtra(WebViewUpTitleActivity.URLID,headIndex.getXuetangId());
                startActivity(intent);
            }
        });

        mList.setPullLoadEnable(true);
        mList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 0;
                SchoolFindAsyn asyn = new SchoolFindAsyn();
                asyn.execute(page+"");

            }

            @Override
            public void onLoadMore() {
                page++;
                SchoolFindAsyn asyn = new SchoolFindAsyn();
                asyn.execute(page+"");
            }
        });
    }

    /**
     * 策略组合停止加载和刷新
     */
    private void onLoad() {
        mList.stopRefresh();
        mList.stopLoadMore();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_class_img:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.search_img:
                intent = new Intent(getActivity(), SerchActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 学堂
     */
    private class SchoolFindAsyn extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("PageIndex", strings[0]);
            map.put("PageCount", "0");
            map.put("PageSize", "0");
            String message = HttpManager.newInstance().getHttpDataByTwoLayer("",map,HttpManager.CourseInfoList_URL);

            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onLoad();
            if (!TextUtils.isEmpty(s)&&s.length()>10){
                Gson gson = new Gson();
                findInfo = gson.fromJson(s,XueTangInfo.class);
                parseData(findInfo);
            }
        }
    }

    private void parseData(XueTangInfo findInfo){
        if (findInfo.getHead().getStatus() == 0){
            List<XueTangInfo.ResultBean.CoursesBean> Courses = findInfo.getResult().getCourses();
            ArrayList<HeadIndex> list = new ArrayList<>();
            for (int i = 0;i<Courses.size();i++){
                HeadIndex headIndex = new HeadIndex();
                XueTangInfo.ResultBean.CoursesBean coursesBean = Courses.get(i);
                headIndex.setIndexImage(coursesBean.getImgUrl());
                headIndex.setXuetangId(coursesBean.getId());
                list.add(headIndex);
            }
            if (page == 0){
                listSave = list;
                adapter.setData(list);
                mList.setAdapter(adapter);
            }else if (page >0&&list.size()>0){
                listSave.addAll(list);
                adapter.addData(list);
            }else {
                Toast.makeText(getActivity(),"没有更多了哦",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
