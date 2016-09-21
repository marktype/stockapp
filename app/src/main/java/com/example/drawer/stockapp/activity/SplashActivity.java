package com.example.drawer.stockapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.drawer.stockapp.MainActivity;
import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.adapter.SplashViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BascActivity {
    private ViewPager vp_plash;
    private LinearLayout ll_point_container;
    private SplashViewpagerAdapter splashViewpagerAdapter;
    private List<Integer> splashImageResourceIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintResource(android.R.color.transparent);
        setContentView(R.layout.activity_launch);
        initWight();
    }

    private void initWight() {
        vp_plash = (ViewPager) findViewById(R.id.vp_plash);
        ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
        splashImageResourceIdList = new ArrayList<>();
        final List<ImageView> imageViews = new ArrayList<>();
        splashImageResourceIdList.add(R.mipmap.splash_2);
        splashImageResourceIdList.add(R.mipmap.splash_1);
        splashImageResourceIdList.add(R.mipmap.splash_3);
        for (int i = 0; i < splashImageResourceIdList.size(); i++) {
            ImageView addView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 0, 5, 0);
            addView.setLayoutParams(layoutParams);
            if (i == 0) {
                addView.setImageResource(R.mipmap.pager_dot_selected);
            } else {
                addView.setImageResource(R.mipmap.pager_dot_normal);
            }
            ll_point_container.addView(addView);
            imageViews.add(addView);
        }
        splashViewpagerAdapter = new SplashViewpagerAdapter(this, splashImageResourceIdList);

        vp_plash.setAdapter(splashViewpagerAdapter);
        vp_plash.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (ImageView imageView : imageViews) {
                    imageView.setImageResource(R.mipmap.pager_dot_normal);
                }
                imageViews.get(position).setImageResource(R.mipmap.pager_dot_selected);
                if (position + 1 == splashImageResourceIdList.size()) {
//                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
//                    startActivity(intent);
                    initSp();
//                    finish();
                    Timer timer = new Timer();
                    timer.schedule(new MyTask(), 1000);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyTask extends TimerTask {

        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initSp() {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = getSharedPreferences("launch",
                Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）

        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putInt("first", 1);
        //提交当前数据
        editor.commit();
    }
}
