package com.example.drawer.stockapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.activity.BascActivity;
import com.example.drawer.stockapp.activity.LoginActivity;
import com.example.drawer.stockapp.activity.UserInfoActivity;
import com.example.drawer.stockapp.fragment.AutoWisdomFragment;
import com.example.drawer.stockapp.fragment.FirstNewsFragment;
import com.example.drawer.stockapp.fragment.MyFragment;
import com.example.drawer.stockapp.fragment.XueTangFragment;
import com.example.drawer.stockapp.listener.OnFragmentInteractionListener;
import com.example.drawer.stockapp.utils.ManagerUtil;

public class MainActivity extends BascActivity implements OnFragmentInteractionListener,View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private FragmentTabHost tabHost;
    public static Boolean isFirst = false;   //判断是否跳转回首页的标记
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        setContentView(R.layout.activity_main);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭手势滑动
        initTab();
//        initWight();
        String  fileName = Environment.getExternalStorageDirectory() +"/icon.png"  ;
        ManagerUtil.saveImg(this,fileName);

    }

    public void initWight(){

        TextView mExit = (TextView) findViewById(R.id.exit_now);
        RelativeLayout mLayout = (RelativeLayout) findViewById(R.id.drawer_head_info_relat);


        mLayout.setOnClickListener(this);
        mExit.setOnClickListener(this);
    }

    //自定义tab
    public View setTabMenu(String name, int image) {
        View v = LayoutInflater.from(this).inflate(R.layout.tab_own_item_layout, null);
        TextView menuText = (TextView) v.findViewById(R.id.tab_menu_txt);
        ImageView menuImg = (ImageView) v.findViewById(R.id.tab_image);
        menuText.setText(name);
        menuImg.setImageResource(image);
        return v;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst) {
            tabHost.setCurrentTab(0);
            isFirst = false;
        }
///**
// * select广播
// */
//        IntentFilter filter2 = new IntentFilter();
//        // 向过滤器中添加action
//        filter2.addAction("com.ymhd.select");
//        // 注册广播
//        registerReceiver(select, filter2);
    }

    public void initTab(){
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        //设置距离顶部状态栏高度
//        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT,
//                DrawerLayout.LayoutParams.MATCH_PARENT);
//        params.setMargins(0,getStatusBarHeight(this),0,0);
//        tabHost.setLayoutParams(params);
        //使用fragment代替activity转换实现
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

//        Bundle bundle = new Bundle();
//        bundle.putString("status","1");

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(setTabMenu("资讯", R.drawable.tab_item1_selector)), FirstNewsFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(setTabMenu("智能投顾", R.drawable.tab_item2_selector)), AutoWisdomFragment.class, null);
//        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(setTabMenu("自选股", R.drawable.tab_item3_selector)), AutoStockFragment.class, null);   //暂时隐藏
//        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(setTabMenu("学堂", R.drawable.tab_item4_selector)), SchoolFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(setTabMenu("学堂", R.drawable.tab_item4_selector)), XueTangFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator(setTabMenu("我的", R.drawable.tab_item5_selector)), MyFragment.class, null);

        tabHost.getTabWidget().setDividerDrawable(null);     //去除tab之间的分割线
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                switch (s){
                    case "tab1":
                        tintManager.setStatusBarTintResource(android.R.color.transparent);
                        break;
                    case "tab2":
                        tintManager.setStatusBarTintResource(android.R.color.transparent);
                        break;
                    case "tab4":
                        tintManager.setStatusBarTintResource(R.color.write_color);
                        break;
                    case "tab5":
                        tintManager.setStatusBarTintResource(android.R.color.transparent);
                        break;
                }
            }
        });


    }



    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 800) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onFragmentInteraction() {
//        mDrawerLayout.openDrawer(Gravity.LEFT);
    }



    @Override
    protected void onStop() {
        super.onStop();
//        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
//            mDrawerLayout.closeDrawer(Gravity.LEFT);
//        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exit_now:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.drawer_head_info_relat:
                intent = new Intent(this, UserInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
