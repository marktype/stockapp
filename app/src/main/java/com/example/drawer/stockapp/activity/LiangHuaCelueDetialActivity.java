package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.ChiCangAdapter;
import com.example.drawer.stockapp.adapter.GenTouAdapter;
import com.example.drawer.stockapp.adapter.TiaoCangAdapter;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.model.ChiCangInfo;
import com.example.drawer.stockapp.model.TiaoCangInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class LiangHuaCelueDetialActivity extends BascActivity implements View.OnClickListener{

    private MyListView mTiaoCangList,mChiCnagList,mGenTouLiat;

    private TiaoCangAdapter tiaoCangAdapter;
    private ChiCangAdapter chiCangAdapter;
    private GenTouAdapter genTouAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.celue_detial_first_layout);
        tintManager.setStatusBarTintResource(R.color.write_color);
        initWight();
    }

    private void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.celue_first_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);


        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        //调仓
        mTiaoCangList = (MyListView) findViewById(R.id.tiaocang_listview);
        tiaoCangAdapter = new TiaoCangAdapter(this);
        tiaoCangAdapter.setData(getTiaoCangData());
        mTiaoCangList.setAdapter(tiaoCangAdapter);

        //持仓
        mChiCnagList = (MyListView) findViewById(R.id.chicang_listview);
        chiCangAdapter = new ChiCangAdapter(this);
        chiCangAdapter.setData(getChiCangData());
        mChiCnagList.setAdapter(chiCangAdapter);

        //跟投
        mGenTouLiat = (MyListView) findViewById(R.id.gengtou_listview);
        genTouAdapter = new GenTouAdapter(this);
        genTouAdapter.setData(getGenTouData());
        mGenTouLiat.setAdapter(genTouAdapter);


        mBackimg.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
        }
    }

    private ArrayList<TiaoCangInfo> getTiaoCangData(){
        ArrayList<TiaoCangInfo> tiaoCang = new ArrayList<>();
        TiaoCangInfo info = new TiaoCangInfo();
        info.setBuyCome(true);
        info.setStockName("中国平安");
        info.setStockNum("601318");
        info.setTradeNumStart("0");
        info.setTradeNumEnd("500");
        info.setTradePrice("11");
        tiaoCang.add(info);
        return tiaoCang;
    }

    private ArrayList<ChiCangInfo> getChiCangData(){
        ArrayList<ChiCangInfo> chicangList = new ArrayList<>();
        ChiCangInfo info = new ChiCangInfo();
        info.setStockName("中国平安");
        info.setStockNum("601318");
        info.setTodayAdd("2%");
        info.setNowPrice("123");
        info.setBascPrice("111");
        info.setCangwei("32");
        info.setFuYing("23%");
        chicangList.add(info);
        return chicangList;
    }

    private ArrayList<ChiCangInfo> getGenTouData(){
        ArrayList<ChiCangInfo> chicangList = new ArrayList<>();
        ChiCangInfo info = new ChiCangInfo();

        info.setTodayAdd("11");
        info.setNowPrice("张三");
        info.setBascPrice("111");
        info.setCangwei("2016.3.2");

        chicangList.add(info);
        return chicangList;
    }
}
