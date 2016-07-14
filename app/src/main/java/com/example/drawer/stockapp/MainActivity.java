package com.example.drawer.stockapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.activity.BascActivity;
import com.example.drawer.stockapp.activity.LoginActivity;
import com.example.drawer.stockapp.activity.UserInfoActivity;
import com.example.drawer.stockapp.fragment.AutoWisdomFragment;
import com.example.drawer.stockapp.fragment.FirstNewsFragment;
import com.example.drawer.stockapp.fragment.SchoolFragment;
import com.example.drawer.stockapp.listener.OnFragmentInteractionListener;

import java.lang.reflect.Field;

public class MainActivity extends BascActivity implements OnFragmentInteractionListener,View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭手势滑动
        initTab();
        initWight();

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


    public void initTab(){
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //设置距离顶部状态栏高度
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT,
                DrawerLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0,getStatusBarHeight(this),0,0);
        tabHost.setLayoutParams(params);
        //使用fragment代替activity转换实现
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(setTabMenu("头条", R.drawable.tab_item1_selector)), FirstNewsFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(setTabMenu("智能投顾", R.drawable.tab_item2_selector)), AutoWisdomFragment.class, null);
//        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(setTabMenu("自选股", R.drawable.tab_item3_selector)), AutoStockFragment.class, null);   //暂时隐藏
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(setTabMenu("学堂", R.drawable.tab_item4_selector)), SchoolFragment.class, null);

    }

    //通知栏高度写在dimen文件中(获取状态栏高度)
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    @Override
    public void onFragmentInteraction() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
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
